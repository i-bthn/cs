package org.apache.cordova;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.cordova.PluginResult;

public class NativeToJsMessageQueue {
    static final boolean DISABLE_EXEC_CHAINING = false;
    private static final boolean FORCE_ENCODE_USING_EVAL = false;
    private static final String LOG_TAG = "JsMessageQueue";
    private static int MAX_PAYLOAD_SIZE = 524288000;
    private BridgeMode activeBridgeMode;
    private ArrayList<BridgeMode> bridgeModes = new ArrayList<>();
    private boolean paused;
    private final LinkedList<JsMessage> queue = new LinkedList<>();

    public static abstract class BridgeMode {
        public void notifyOfFlush(NativeToJsMessageQueue nativeToJsMessageQueue, boolean z) {
        }

        public abstract void onNativeToJsMessageAvailable(NativeToJsMessageQueue nativeToJsMessageQueue);

        public void reset() {
        }
    }

    public static class NoOpBridgeMode extends BridgeMode {
        @Override // org.apache.cordova.NativeToJsMessageQueue.BridgeMode
        public void onNativeToJsMessageAvailable(NativeToJsMessageQueue nativeToJsMessageQueue) {
        }
    }

    public void addBridgeMode(BridgeMode bridgeMode) {
        this.bridgeModes.add(bridgeMode);
    }

    public boolean isBridgeEnabled() {
        return this.activeBridgeMode != null;
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void setBridgeMode(int i) {
        BridgeMode bridgeMode;
        if (i < -1 || i >= this.bridgeModes.size()) {
            LOG.d(LOG_TAG, "Invalid NativeToJsBridgeMode: " + i);
            return;
        }
        if (i < 0) {
            bridgeMode = null;
        } else {
            bridgeMode = this.bridgeModes.get(i);
        }
        if (bridgeMode != this.activeBridgeMode) {
            StringBuilder sb = new StringBuilder();
            sb.append("Set native->JS mode to ");
            sb.append(bridgeMode == null ? "null" : bridgeMode.getClass().getSimpleName());
            LOG.d(LOG_TAG, sb.toString());
            synchronized (this) {
                this.activeBridgeMode = bridgeMode;
                if (bridgeMode != null) {
                    bridgeMode.reset();
                    if (!this.paused && !this.queue.isEmpty()) {
                        bridgeMode.onNativeToJsMessageAvailable(this);
                    }
                }
            }
        }
    }

    public void reset() {
        synchronized (this) {
            this.queue.clear();
            setBridgeMode(-1);
        }
    }

    private int calculatePackedMessageLength(JsMessage jsMessage) {
        int calculateEncodedLength = jsMessage.calculateEncodedLength();
        return String.valueOf(calculateEncodedLength).length() + calculateEncodedLength + 1;
    }

    private void packMessage(JsMessage jsMessage, StringBuilder sb) {
        sb.append(jsMessage.calculateEncodedLength());
        sb.append(' ');
        jsMessage.encodeAsMessage(sb);
    }

    public String popAndEncode(boolean z) {
        synchronized (this) {
            if (this.activeBridgeMode == null) {
                return null;
            }
            this.activeBridgeMode.notifyOfFlush(this, z);
            if (this.queue.isEmpty()) {
                return null;
            }
            Iterator<JsMessage> it = this.queue.iterator();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int calculatePackedMessageLength = calculatePackedMessageLength(it.next());
                if (i > 0 && i2 + calculatePackedMessageLength > MAX_PAYLOAD_SIZE && MAX_PAYLOAD_SIZE > 0) {
                    break;
                }
                i2 += calculatePackedMessageLength;
                i++;
            }
            StringBuilder sb = new StringBuilder(i2);
            for (int i3 = 0; i3 < i; i3++) {
                packMessage(this.queue.removeFirst(), sb);
            }
            if (!this.queue.isEmpty()) {
                sb.append('*');
            }
            return sb.toString();
        }
    }

    public String popAndEncodeAsJs() {
        synchronized (this) {
            if (this.queue.size() == 0) {
                return null;
            }
            Iterator<JsMessage> it = this.queue.iterator();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int calculateEncodedLength = it.next().calculateEncodedLength() + 50;
                if (i > 0 && i2 + calculateEncodedLength > MAX_PAYLOAD_SIZE && MAX_PAYLOAD_SIZE > 0) {
                    break;
                }
                i2 += calculateEncodedLength;
                i++;
            }
            int i3 = i == this.queue.size() ? 1 : 0;
            StringBuilder sb = new StringBuilder(i2 + (i3 != 0 ? 0 : 100));
            for (int i4 = 0; i4 < i; i4++) {
                JsMessage removeFirst = this.queue.removeFirst();
                if (i3 == 0 || i4 + 1 != i) {
                    sb.append("try{");
                    removeFirst.encodeAsJsMessage(sb);
                    sb.append("}finally{");
                } else {
                    removeFirst.encodeAsJsMessage(sb);
                }
            }
            if (i3 == 0) {
                sb.append("window.setTimeout(function(){cordova.require('cordova/plugin/android/polling').pollOnce();},0);");
            }
            while (i3 < i) {
                sb.append('}');
                i3++;
            }
            return sb.toString();
        }
    }

    public void addJavaScript(String str) {
        enqueueMessage(new JsMessage(str));
    }

    public void addPluginResult(PluginResult pluginResult, String str) {
        if (str == null) {
            LOG.e(LOG_TAG, "Got plugin result with no callbackId", new Throwable());
            return;
        }
        boolean z = pluginResult.getStatus() == PluginResult.Status.NO_RESULT.ordinal();
        boolean keepCallback = pluginResult.getKeepCallback();
        if (!z || !keepCallback) {
            enqueueMessage(new JsMessage(pluginResult, str));
        }
    }

    private void enqueueMessage(JsMessage jsMessage) {
        synchronized (this) {
            if (this.activeBridgeMode == null) {
                LOG.d(LOG_TAG, "Dropping Native->JS message due to disabled bridge");
                return;
            }
            this.queue.add(jsMessage);
            if (!this.paused) {
                this.activeBridgeMode.onNativeToJsMessageAvailable(this);
            }
        }
    }

    public void setPaused(boolean z) {
        if (this.paused && z) {
            LOG.e(LOG_TAG, "nested call to setPaused detected.", new Throwable());
        }
        this.paused = z;
        if (!z) {
            synchronized (this) {
                if (!this.queue.isEmpty() && this.activeBridgeMode != null) {
                    this.activeBridgeMode.onNativeToJsMessageAvailable(this);
                }
            }
        }
    }

    public static class LoadUrlBridgeMode extends BridgeMode {
        private final CordovaInterface cordova;
        private final CordovaWebViewEngine engine;

        public LoadUrlBridgeMode(CordovaWebViewEngine cordovaWebViewEngine, CordovaInterface cordovaInterface) {
            this.engine = cordovaWebViewEngine;
            this.cordova = cordovaInterface;
        }

        @Override // org.apache.cordova.NativeToJsMessageQueue.BridgeMode
        public void onNativeToJsMessageAvailable(final NativeToJsMessageQueue nativeToJsMessageQueue) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                /* class org.apache.cordova.NativeToJsMessageQueue.LoadUrlBridgeMode.AnonymousClass1 */

                public void run() {
                    String popAndEncodeAsJs = nativeToJsMessageQueue.popAndEncodeAsJs();
                    if (popAndEncodeAsJs != null) {
                        CordovaWebViewEngine cordovaWebViewEngine = LoadUrlBridgeMode.this.engine;
                        cordovaWebViewEngine.loadUrl("javascript:" + popAndEncodeAsJs, false);
                    }
                }
            });
        }
    }

    public static class OnlineEventsBridgeMode extends BridgeMode {
        private final OnlineEventsBridgeModeDelegate delegate;
        private boolean ignoreNextFlush;
        private boolean online;

        public interface OnlineEventsBridgeModeDelegate {
            void runOnUiThread(Runnable runnable);

            void setNetworkAvailable(boolean z);
        }

        public OnlineEventsBridgeMode(OnlineEventsBridgeModeDelegate onlineEventsBridgeModeDelegate) {
            this.delegate = onlineEventsBridgeModeDelegate;
        }

        @Override // org.apache.cordova.NativeToJsMessageQueue.BridgeMode
        public void reset() {
            this.delegate.runOnUiThread(new Runnable() {
                /* class org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode.AnonymousClass1 */

                public void run() {
                    OnlineEventsBridgeMode.this.online = false;
                    OnlineEventsBridgeMode.this.ignoreNextFlush = true;
                    OnlineEventsBridgeMode.this.delegate.setNetworkAvailable(true);
                }
            });
        }

        @Override // org.apache.cordova.NativeToJsMessageQueue.BridgeMode
        public void onNativeToJsMessageAvailable(final NativeToJsMessageQueue nativeToJsMessageQueue) {
            this.delegate.runOnUiThread(new Runnable() {
                /* class org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode.AnonymousClass2 */

                public void run() {
                    if (!nativeToJsMessageQueue.isEmpty()) {
                        OnlineEventsBridgeMode.this.ignoreNextFlush = false;
                        OnlineEventsBridgeMode.this.delegate.setNetworkAvailable(OnlineEventsBridgeMode.this.online);
                    }
                }
            });
        }

        @Override // org.apache.cordova.NativeToJsMessageQueue.BridgeMode
        public void notifyOfFlush(NativeToJsMessageQueue nativeToJsMessageQueue, boolean z) {
            if (z && !this.ignoreNextFlush) {
                this.online = !this.online;
            }
        }
    }

    public static class EvalBridgeMode extends BridgeMode {
        private final CordovaInterface cordova;
        private final CordovaWebViewEngine engine;

        public EvalBridgeMode(CordovaWebViewEngine cordovaWebViewEngine, CordovaInterface cordovaInterface) {
            this.engine = cordovaWebViewEngine;
            this.cordova = cordovaInterface;
        }

        @Override // org.apache.cordova.NativeToJsMessageQueue.BridgeMode
        public void onNativeToJsMessageAvailable(final NativeToJsMessageQueue nativeToJsMessageQueue) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                /* class org.apache.cordova.NativeToJsMessageQueue.EvalBridgeMode.AnonymousClass1 */

                public void run() {
                    String popAndEncodeAsJs = nativeToJsMessageQueue.popAndEncodeAsJs();
                    if (popAndEncodeAsJs != null) {
                        EvalBridgeMode.this.engine.evaluateJavascript(popAndEncodeAsJs, null);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static class JsMessage {
        final String jsPayloadOrCallbackId;
        final PluginResult pluginResult;

        JsMessage(String str) {
            if (str != null) {
                this.jsPayloadOrCallbackId = str;
                this.pluginResult = null;
                return;
            }
            throw new NullPointerException();
        }

        JsMessage(PluginResult pluginResult2, String str) {
            if (str == null || pluginResult2 == null) {
                throw new NullPointerException();
            }
            this.jsPayloadOrCallbackId = str;
            this.pluginResult = pluginResult2;
        }

        static int calculateEncodedLengthHelper(PluginResult pluginResult2) {
            int messageType = pluginResult2.getMessageType();
            if (messageType == 1) {
                return pluginResult2.getStrMessage().length() + 1;
            }
            switch (messageType) {
                case 3:
                    return pluginResult2.getMessage().length() + 1;
                case 4:
                case 5:
                    return 1;
                case 6:
                    return pluginResult2.getMessage().length() + 1;
                case 7:
                    return pluginResult2.getMessage().length() + 1;
                case 8:
                    int i = 1;
                    for (int i2 = 0; i2 < pluginResult2.getMultipartMessagesSize(); i2++) {
                        int calculateEncodedLengthHelper = calculateEncodedLengthHelper(pluginResult2.getMultipartMessage(i2));
                        i += String.valueOf(calculateEncodedLengthHelper).length() + 1 + calculateEncodedLengthHelper;
                    }
                    return i;
                default:
                    return pluginResult2.getMessage().length();
            }
        }

        /* access modifiers changed from: package-private */
        public int calculateEncodedLength() {
            PluginResult pluginResult2 = this.pluginResult;
            if (pluginResult2 == null) {
                return this.jsPayloadOrCallbackId.length() + 1;
            }
            return String.valueOf(pluginResult2.getStatus()).length() + 2 + 1 + this.jsPayloadOrCallbackId.length() + 1 + calculateEncodedLengthHelper(this.pluginResult);
        }

        static void encodeAsMessageHelper(StringBuilder sb, PluginResult pluginResult2) {
            int messageType = pluginResult2.getMessageType();
            if (messageType != 1) {
                switch (messageType) {
                    case 3:
                        sb.append('n');
                        sb.append(pluginResult2.getMessage());
                        return;
                    case 4:
                        sb.append(pluginResult2.getMessage().charAt(0));
                        return;
                    case 5:
                        sb.append('N');
                        return;
                    case 6:
                        sb.append('A');
                        sb.append(pluginResult2.getMessage());
                        return;
                    case 7:
                        sb.append('S');
                        sb.append(pluginResult2.getMessage());
                        return;
                    case 8:
                        sb.append('M');
                        for (int i = 0; i < pluginResult2.getMultipartMessagesSize(); i++) {
                            PluginResult multipartMessage = pluginResult2.getMultipartMessage(i);
                            sb.append(String.valueOf(calculateEncodedLengthHelper(multipartMessage)));
                            sb.append(' ');
                            encodeAsMessageHelper(sb, multipartMessage);
                        }
                        return;
                    default:
                        sb.append(pluginResult2.getMessage());
                        return;
                }
            } else {
                sb.append('s');
                sb.append(pluginResult2.getStrMessage());
            }
        }

        /* access modifiers changed from: package-private */
        public void encodeAsMessage(StringBuilder sb) {
            PluginResult pluginResult2 = this.pluginResult;
            if (pluginResult2 == null) {
                sb.append('J');
                sb.append(this.jsPayloadOrCallbackId);
                return;
            }
            int status = pluginResult2.getStatus();
            boolean z = true;
            boolean z2 = status == PluginResult.Status.NO_RESULT.ordinal();
            if (status != PluginResult.Status.OK.ordinal()) {
                z = false;
            }
            boolean keepCallback = this.pluginResult.getKeepCallback();
            sb.append((z2 || z) ? 'S' : 'F');
            sb.append(keepCallback ? '1' : '0');
            sb.append(status);
            sb.append(' ');
            sb.append(this.jsPayloadOrCallbackId);
            sb.append(' ');
            encodeAsMessageHelper(sb, this.pluginResult);
        }

        /* access modifiers changed from: package-private */
        public void buildJsMessage(StringBuilder sb) {
            switch (this.pluginResult.getMessageType()) {
                case 5:
                    sb.append("null");
                    return;
                case 6:
                    sb.append("cordova.require('cordova/base64').toArrayBuffer('");
                    sb.append(this.pluginResult.getMessage());
                    sb.append("')");
                    return;
                case 7:
                    sb.append("atob('");
                    sb.append(this.pluginResult.getMessage());
                    sb.append("')");
                    return;
                case 8:
                    int multipartMessagesSize = this.pluginResult.getMultipartMessagesSize();
                    for (int i = 0; i < multipartMessagesSize; i++) {
                        new JsMessage(this.pluginResult.getMultipartMessage(i), this.jsPayloadOrCallbackId).buildJsMessage(sb);
                        if (i < multipartMessagesSize - 1) {
                            sb.append(",");
                        }
                    }
                    return;
                default:
                    sb.append(this.pluginResult.getMessage());
                    return;
            }
        }

        /* access modifiers changed from: package-private */
        public void encodeAsJsMessage(StringBuilder sb) {
            PluginResult pluginResult2 = this.pluginResult;
            if (pluginResult2 == null) {
                sb.append(this.jsPayloadOrCallbackId);
                return;
            }
            int status = pluginResult2.getStatus();
            boolean z = status == PluginResult.Status.OK.ordinal() || status == PluginResult.Status.NO_RESULT.ordinal();
            sb.append("cordova.callbackFromNative('");
            sb.append(this.jsPayloadOrCallbackId);
            sb.append("',");
            sb.append(z);
            sb.append(",");
            sb.append(status);
            sb.append(",[");
            buildJsMessage(sb);
            sb.append("],");
            sb.append(this.pluginResult.getKeepCallback());
            sb.append(");");
        }
    }
}
