package androidx.core.location;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.os.HandlerExecutor;
import androidx.core.util.Preconditions;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class LocationManagerCompat {
    private static final long PRE_N_LOOPER_TIMEOUT_S = 4;
    private static Field sContextField;
    @GuardedBy("sGnssStatusListeners")
    private static final SimpleArrayMap<Object, Object> sGnssStatusListeners = new SimpleArrayMap<>();

    public static boolean isLocationEnabled(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return locationManager.isLocationEnabled();
        }
        if (Build.VERSION.SDK_INT <= 19) {
            try {
                if (sContextField == null) {
                    sContextField = LocationManager.class.getDeclaredField("mContext");
                }
                sContextField.setAccessible(true);
                Context context = (Context) sContextField.get(locationManager);
                if (Build.VERSION.SDK_INT != 19) {
                    return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"));
                }
                if (Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0) {
                    return true;
                }
                return false;
            } catch (ClassCastException | IllegalAccessException | NoSuchFieldException | SecurityException unused) {
            }
        }
        if (locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps")) {
            return true;
        }
        return false;
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback, @NonNull Handler handler) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, new HandlerExecutor(handler), callback);
        }
        return registerGnssStatusCallback(locationManager, new InlineHandlerExecutor(handler), callback);
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, null, executor, callback);
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            myLooper = Looper.getMainLooper();
        }
        return registerGnssStatusCallback(locationManager, new Handler(myLooper), executor, callback);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:76:0x00e4 */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0116 A[Catch:{ ExecutionException -> 0x010c, TimeoutException -> 0x00f3, all -> 0x00f0, all -> 0x0132 }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x012b A[Catch:{ ExecutionException -> 0x010c, TimeoutException -> 0x00f3, all -> 0x00f0, all -> 0x0132 }] */
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    private static boolean registerGnssStatusCallback(final LocationManager locationManager, Handler handler, Executor executor, GnssStatusCompat.Callback callback) {
        Throwable th;
        ExecutionException e;
        TimeoutException e2;
        long nanos;
        boolean z;
        boolean z2 = true;
        if (Build.VERSION.SDK_INT >= 30) {
            synchronized (sGnssStatusListeners) {
                GnssStatus.Callback callback2 = (GnssStatusTransport) sGnssStatusListeners.get(callback);
                if (callback2 == null) {
                    callback2 = new GnssStatusTransport(callback);
                }
                if (!locationManager.registerGnssStatusCallback(executor, callback2)) {
                    return false;
                }
                sGnssStatusListeners.put(callback, callback2);
                return true;
            }
        } else if (Build.VERSION.SDK_INT >= 24) {
            Preconditions.checkArgument(handler != null);
            synchronized (sGnssStatusListeners) {
                PreRGnssStatusTransport preRGnssStatusTransport = (PreRGnssStatusTransport) sGnssStatusListeners.get(callback);
                if (preRGnssStatusTransport == null) {
                    preRGnssStatusTransport = new PreRGnssStatusTransport(callback);
                } else {
                    preRGnssStatusTransport.unregister();
                }
                preRGnssStatusTransport.register(executor);
                if (!locationManager.registerGnssStatusCallback(preRGnssStatusTransport, handler)) {
                    return false;
                }
                sGnssStatusListeners.put(callback, preRGnssStatusTransport);
                return true;
            }
        } else {
            Preconditions.checkArgument(handler != null);
            synchronized (sGnssStatusListeners) {
                final GpsStatusTransport gpsStatusTransport = (GpsStatusTransport) sGnssStatusListeners.get(callback);
                if (gpsStatusTransport == null) {
                    gpsStatusTransport = new GpsStatusTransport(locationManager, callback);
                } else {
                    gpsStatusTransport.unregister();
                }
                gpsStatusTransport.register(executor);
                FutureTask futureTask = new FutureTask(new Callable<Boolean>() {
                    /* class androidx.core.location.LocationManagerCompat.AnonymousClass1 */

                    @Override // java.util.concurrent.Callable
                    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
                    public Boolean call() {
                        return Boolean.valueOf(locationManager.addGpsStatusListener(gpsStatusTransport));
                    }
                });
                if (Looper.myLooper() == handler.getLooper()) {
                    futureTask.run();
                } else if (!handler.post(futureTask)) {
                    throw new IllegalStateException(handler + " is shutting down");
                }
                try {
                    nanos = TimeUnit.SECONDS.toNanos(4);
                    z = false;
                    while (true) {
                        try {
                            break;
                        } catch (InterruptedException unknown) {
                            try {
                                nanos = (System.nanoTime() + nanos) - System.nanoTime();
                                z = true;
                            } catch (ExecutionException e3) {
                                e = e3;
                                if (e.getCause() instanceof RuntimeException) {
                                }
                            } catch (TimeoutException e4) {
                                e2 = e4;
                                throw new IllegalStateException(handler + " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread", e2);
                            }
                        } catch (ExecutionException e5) {
                            e = e5;
                            if (e.getCause() instanceof RuntimeException) {
                            }
                        } catch (TimeoutException e6) {
                            e2 = e6;
                            z2 = z;
                            throw new IllegalStateException(handler + " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread", e2);
                        } catch (Throwable th2) {
                            th = th2;
                            z2 = z;
                            if (z2) {
                            }
                            throw th;
                        }
                        nanos = (System.nanoTime() + nanos) - System.nanoTime();
                        z = true;
                    }
                    if (((Boolean) futureTask.get(nanos, TimeUnit.NANOSECONDS)).booleanValue()) {
                        sGnssStatusListeners.put(callback, gpsStatusTransport);
                        if (z) {
                            Thread.currentThread().interrupt();
                        }
                        return true;
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    return false;
                } catch (ExecutionException e7) {
                    e = e7;
                    if (e.getCause() instanceof RuntimeException) {
                        throw ((RuntimeException) e.getCause());
                    } else if (e.getCause() instanceof Error) {
                        throw ((Error) e.getCause());
                    } else {
                        throw new IllegalStateException(e);
                    }
                } catch (TimeoutException e8) {
                    e2 = e8;
                    z2 = false;
                    throw new IllegalStateException(handler + " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread", e2);
                } catch (Throwable th3) {
                    th = th3;
                    if (z2) {
                    }
                    throw th;
                }
            }
        }
    }

    public static void unregisterGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            synchronized (sGnssStatusListeners) {
                GnssStatus.Callback callback2 = (GnssStatusTransport) sGnssStatusListeners.remove(callback);
                if (callback2 != null) {
                    locationManager.unregisterGnssStatusCallback(callback2);
                }
            }
        } else if (Build.VERSION.SDK_INT >= 24) {
            synchronized (sGnssStatusListeners) {
                PreRGnssStatusTransport preRGnssStatusTransport = (PreRGnssStatusTransport) sGnssStatusListeners.remove(callback);
                if (preRGnssStatusTransport != null) {
                    preRGnssStatusTransport.unregister();
                    locationManager.unregisterGnssStatusCallback(preRGnssStatusTransport);
                }
            }
        } else {
            synchronized (sGnssStatusListeners) {
                GpsStatusTransport gpsStatusTransport = (GpsStatusTransport) sGnssStatusListeners.remove(callback);
                if (gpsStatusTransport != null) {
                    gpsStatusTransport.unregister();
                    locationManager.removeGpsStatusListener(gpsStatusTransport);
                }
            }
        }
    }

    private LocationManagerCompat() {
    }

    /* access modifiers changed from: private */
    @RequiresApi(30)
    public static class GnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;

        GnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void onStarted() {
            this.mCallback.onStarted();
        }

        public void onStopped() {
            this.mCallback.onStopped();
        }

        public void onFirstFix(int i) {
            this.mCallback.onFirstFix(i);
        }

        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(gnssStatus));
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(24)
    public static class PreRGnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;
        @Nullable
        volatile Executor mExecutor;

        PreRGnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            boolean z = true;
            Preconditions.checkArgument(executor != null, "invalid null executor");
            if (this.mExecutor != null) {
                z = false;
            }
            Preconditions.checkState(z);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        public void onStarted() {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    /* class androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.AnonymousClass1 */

                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onStarted();
                        }
                    }
                });
            }
        }

        public void onStopped() {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    /* class androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.AnonymousClass2 */

                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onStopped();
                        }
                    }
                });
            }
        }

        public void onFirstFix(final int i) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    /* class androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.AnonymousClass3 */

                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onFirstFix(i);
                        }
                    }
                });
            }
        }

        public void onSatelliteStatusChanged(final GnssStatus gnssStatus) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    /* class androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.AnonymousClass4 */

                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(gnssStatus));
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static class GpsStatusTransport implements GpsStatus.Listener {
        final GnssStatusCompat.Callback mCallback;
        @Nullable
        volatile Executor mExecutor;
        private final LocationManager mLocationManager;

        GpsStatusTransport(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mLocationManager = locationManager;
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            Preconditions.checkState(this.mExecutor == null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public void onGpsStatusChanged(int i) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                switch (i) {
                    case 1:
                        executor.execute(new Runnable() {
                            /* class androidx.core.location.LocationManagerCompat.GpsStatusTransport.AnonymousClass1 */

                            public void run() {
                                if (GpsStatusTransport.this.mExecutor == executor) {
                                    GpsStatusTransport.this.mCallback.onStarted();
                                }
                            }
                        });
                        return;
                    case 2:
                        executor.execute(new Runnable() {
                            /* class androidx.core.location.LocationManagerCompat.GpsStatusTransport.AnonymousClass2 */

                            public void run() {
                                if (GpsStatusTransport.this.mExecutor == executor) {
                                    GpsStatusTransport.this.mCallback.onStopped();
                                }
                            }
                        });
                        return;
                    case 3:
                        GpsStatus gpsStatus = this.mLocationManager.getGpsStatus(null);
                        if (gpsStatus != null) {
                            final int timeToFirstFix = gpsStatus.getTimeToFirstFix();
                            executor.execute(new Runnable() {
                                /* class androidx.core.location.LocationManagerCompat.GpsStatusTransport.AnonymousClass3 */

                                public void run() {
                                    if (GpsStatusTransport.this.mExecutor == executor) {
                                        GpsStatusTransport.this.mCallback.onFirstFix(timeToFirstFix);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    case 4:
                        GpsStatus gpsStatus2 = this.mLocationManager.getGpsStatus(null);
                        if (gpsStatus2 != null) {
                            final GnssStatusCompat wrap = GnssStatusCompat.wrap(gpsStatus2);
                            executor.execute(new Runnable() {
                                /* class androidx.core.location.LocationManagerCompat.GpsStatusTransport.AnonymousClass4 */

                                public void run() {
                                    if (GpsStatusTransport.this.mExecutor == executor) {
                                        GpsStatusTransport.this.mCallback.onSatelliteStatusChanged(wrap);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static class InlineHandlerExecutor implements Executor {
        private final Handler mHandler;

        InlineHandlerExecutor(@NonNull Handler handler) {
            this.mHandler = (Handler) Preconditions.checkNotNull(handler);
        }

        public void execute(@NonNull Runnable runnable) {
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                runnable.run();
            } else if (!this.mHandler.post((Runnable) Preconditions.checkNotNull(runnable))) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }
}
