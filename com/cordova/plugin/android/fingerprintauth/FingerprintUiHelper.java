package com.cordova.plugin.android.fingerprintauth;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(23)
public class FingerprintUiHelper extends FingerprintManager.AuthenticationCallback {
    static final long ERROR_TIMEOUT_MILLIS = 1600;
    static final long SUCCESS_DELAY_MILLIS = 1300;
    private final Callback mCallback;
    private CancellationSignal mCancellationSignal;
    private final Context mContext;
    private final TextView mErrorTextView;
    private final FingerprintManager mFingerprintManager;
    private final ImageView mIcon;
    Runnable mResetErrorTextRunnable;
    boolean mSelfCancelled;

    public interface Callback {
        void onAuthenticated();

        void onError();
    }

    public static class FingerprintUiHelperBuilder {
        private final Context mContext;
        private final FingerprintManager mFingerPrintManager;

        public FingerprintUiHelperBuilder(Context context, FingerprintManager fingerprintManager) {
            this.mFingerPrintManager = fingerprintManager;
            this.mContext = context;
        }

        public FingerprintUiHelper build(ImageView imageView, TextView textView, Callback callback) {
            return new FingerprintUiHelper(this.mContext, this.mFingerPrintManager, imageView, textView, callback);
        }
    }

    private FingerprintUiHelper(Context context, FingerprintManager fingerprintManager, ImageView imageView, TextView textView, Callback callback) {
        this.mResetErrorTextRunnable = new Runnable() {
            /* class com.cordova.plugin.android.fingerprintauth.FingerprintUiHelper.AnonymousClass3 */

            public void run() {
                FingerprintUiHelper.this.mErrorTextView.setTextColor(FingerprintUiHelper.this.mErrorTextView.getResources().getColor(FingerprintUiHelper.this.mContext.getResources().getIdentifier("kc_hint_color", "color", FingerprintAuth.packageName), null));
                FingerprintUiHelper.this.mErrorTextView.setText(FingerprintUiHelper.this.mErrorTextView.getResources().getString(FingerprintUiHelper.this.mContext.getResources().getIdentifier("fingerprint_hint", "string", FingerprintAuth.packageName)));
                FingerprintUiHelper.this.mIcon.setImageResource(FingerprintUiHelper.this.mContext.getResources().getIdentifier("ic_fp_40px", "drawable", FingerprintAuth.packageName));
            }
        };
        this.mFingerprintManager = fingerprintManager;
        this.mIcon = imageView;
        this.mErrorTextView = textView;
        this.mCallback = callback;
        this.mContext = context;
    }

    public boolean isFingerprintAuthAvailable() {
        return this.mFingerprintManager.isHardwareDetected() && this.mFingerprintManager.hasEnrolledFingerprints();
    }

    public void startListening(FingerprintManager.CryptoObject cryptoObject) {
        if (isFingerprintAuthAvailable()) {
            this.mCancellationSignal = new CancellationSignal();
            this.mSelfCancelled = false;
            this.mFingerprintManager.authenticate(cryptoObject, this.mCancellationSignal, 0, this, null);
            this.mIcon.setImageResource(this.mContext.getResources().getIdentifier("ic_fp_40px", "drawable", FingerprintAuth.packageName));
        }
    }

    public void stopListening() {
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal != null) {
            this.mSelfCancelled = true;
            cancellationSignal.cancel();
            this.mCancellationSignal = null;
        }
    }

    public void onAuthenticationError(int i, CharSequence charSequence) {
        if (!this.mSelfCancelled) {
            showError(charSequence);
            this.mIcon.postDelayed(new Runnable() {
                /* class com.cordova.plugin.android.fingerprintauth.FingerprintUiHelper.AnonymousClass1 */

                public void run() {
                    FingerprintUiHelper.this.mCallback.onError();
                }
            }, ERROR_TIMEOUT_MILLIS);
        }
    }

    public void onAuthenticationHelp(int i, CharSequence charSequence) {
        showError(charSequence);
    }

    public void onAuthenticationFailed() {
        showError(this.mIcon.getResources().getString(this.mContext.getResources().getIdentifier("fingerprint_not_recognized", "string", FingerprintAuth.packageName)));
    }

    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
        this.mErrorTextView.removeCallbacks(this.mResetErrorTextRunnable);
        this.mIcon.setImageResource(this.mContext.getResources().getIdentifier("ic_fingerprint_success", "drawable", FingerprintAuth.packageName));
        int identifier = this.mContext.getResources().getIdentifier("kc_success_color", "color", FingerprintAuth.packageName);
        TextView textView = this.mErrorTextView;
        textView.setTextColor(textView.getResources().getColor(identifier, null));
        int identifier2 = this.mContext.getResources().getIdentifier("fingerprint_success", "string", FingerprintAuth.packageName);
        TextView textView2 = this.mErrorTextView;
        textView2.setText(textView2.getResources().getString(identifier2));
        this.mIcon.postDelayed(new Runnable() {
            /* class com.cordova.plugin.android.fingerprintauth.FingerprintUiHelper.AnonymousClass2 */

            public void run() {
                FingerprintUiHelper.this.mCallback.onAuthenticated();
            }
        }, SUCCESS_DELAY_MILLIS);
    }

    private void showError(CharSequence charSequence) {
        this.mIcon.setImageResource(this.mContext.getResources().getIdentifier("ic_fingerprint_error", "drawable", FingerprintAuth.packageName));
        this.mErrorTextView.setText(charSequence);
        int identifier = this.mContext.getResources().getIdentifier("kc_warning_color", "color", FingerprintAuth.packageName);
        TextView textView = this.mErrorTextView;
        textView.setTextColor(textView.getResources().getColor(identifier, null));
        this.mErrorTextView.removeCallbacks(this.mResetErrorTextRunnable);
        this.mErrorTextView.postDelayed(this.mResetErrorTextRunnable, ERROR_TIMEOUT_MILLIS);
    }
}
