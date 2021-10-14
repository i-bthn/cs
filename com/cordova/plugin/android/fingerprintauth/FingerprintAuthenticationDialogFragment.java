package com.cordova.plugin.android.fingerprintauth;

import android.app.DialogFragment;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.cordova.plugin.android.fingerprintauth.FingerprintUiHelper;

public class FingerprintAuthenticationDialogFragment extends DialogFragment implements FingerprintUiHelper.Callback {
    private static final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 1;
    private static final String TAG = "FingerprintAuthDialog";
    private Button mCancelButton;
    private FingerprintManager.CryptoObject mCryptoObject;
    private FingerprintAuth mFingerPrintAuth;
    private View mFingerprintContent;
    private FingerprintUiHelper mFingerprintUiHelper;
    FingerprintUiHelper.FingerprintUiHelperBuilder mFingerprintUiHelperBuilder;
    private KeyguardManager mKeyguardManager;
    private Stage mStage = Stage.FINGERPRINT;

    public enum Stage {
        FINGERPRINT,
        NEW_FINGERPRINT_ENROLLED,
        BACKUP
    }

    @Override // com.cordova.plugin.android.fingerprintauth.FingerprintUiHelper.Callback
    public void onError() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        setStyle(0, 16974393);
        this.mKeyguardManager = (KeyguardManager) getContext().getSystemService("keyguard");
        this.mFingerprintUiHelperBuilder = new FingerprintUiHelper.FingerprintUiHelperBuilder(getContext(), (FingerprintManager) getContext().getSystemService(FingerprintManager.class));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        int i = arguments.getInt("dialogMode");
        String string = arguments.getString("dialogMessage");
        Log.d(TAG, "dialogMode: " + i);
        getDialog().setTitle(getString(getResources().getIdentifier("fingerprint_auth_dialog_title", "string", FingerprintAuth.packageName)));
        View inflate = layoutInflater.inflate(getResources().getIdentifier("fingerprint_dialog_container", "layout", FingerprintAuth.packageName), viewGroup, false);
        int identifier = getResources().getIdentifier("cancel_button", "id", FingerprintAuth.packageName);
        ((TextView) inflate.findViewById(getResources().getIdentifier("fingerprint_description", "id", FingerprintAuth.packageName))).setText(string);
        this.mCancelButton = (Button) inflate.findViewById(identifier);
        this.mCancelButton.setOnClickListener(new View.OnClickListener() {
            /* class com.cordova.plugin.android.fingerprintauth.FingerprintAuthenticationDialogFragment.AnonymousClass1 */

            public void onClick(View view) {
                FingerprintAuth.onCancelled();
                FingerprintAuthenticationDialogFragment.this.dismiss();
            }
        });
        this.mFingerprintContent = inflate.findViewById(getResources().getIdentifier("fingerprint_container", "id", FingerprintAuth.packageName));
        getResources().getIdentifier("new_fingerprint_enrolled_description", "id", FingerprintAuth.packageName);
        this.mFingerprintUiHelper = this.mFingerprintUiHelperBuilder.build((ImageView) inflate.findViewById(getResources().getIdentifier("fingerprint_icon", "id", FingerprintAuth.packageName)), (TextView) inflate.findViewById(getResources().getIdentifier("fingerprint_status", "id", FingerprintAuth.packageName)), this);
        updateStage();
        return inflate;
    }

    public void onResume() {
        super.onResume();
        if (this.mStage == Stage.FINGERPRINT) {
            this.mFingerprintUiHelper.startListening(this.mCryptoObject);
        }
    }

    public void setStage(Stage stage) {
        this.mStage = stage;
    }

    public void onPause() {
        super.onPause();
        this.mFingerprintUiHelper.stopListening();
    }

    public void setCryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        this.mCryptoObject = cryptoObject;
    }

    private void updateStage() {
        int identifier = getResources().getIdentifier("cancel", "string", FingerprintAuth.packageName);
        if (AnonymousClass2.$SwitchMap$com$cordova$plugin$android$fingerprintauth$FingerprintAuthenticationDialogFragment$Stage[this.mStage.ordinal()] == 1) {
            this.mCancelButton.setText(identifier);
            this.mFingerprintContent.setVisibility(0);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.cordova.plugin.android.fingerprintauth.FingerprintAuthenticationDialogFragment$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$cordova$plugin$android$fingerprintauth$FingerprintAuthenticationDialogFragment$Stage = new int[Stage.values().length];

        static {
            try {
                $SwitchMap$com$cordova$plugin$android$fingerprintauth$FingerprintAuthenticationDialogFragment$Stage[Stage.FINGERPRINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private void showAuthenticationScreen() {
        Intent createConfirmDeviceCredentialIntent = this.mKeyguardManager.createConfirmDeviceCredentialIntent(null, null);
        if (createConfirmDeviceCredentialIntent != null) {
            startActivityForResult(createConfirmDeviceCredentialIntent, 1);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            getActivity();
            if (i2 == -1) {
                this.mFingerPrintAuth.onAuthenticated(false);
            } else {
                FingerprintAuth.onCancelled();
            }
            dismiss();
        }
    }

    @Override // com.cordova.plugin.android.fingerprintauth.FingerprintUiHelper.Callback
    public void onAuthenticated() {
        this.mFingerPrintAuth.onAuthenticated(true);
        dismiss();
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        FingerprintAuth.onCancelled();
    }

    public FingerprintAuth getmFingerPrintAuth() {
        return this.mFingerPrintAuth;
    }

    public void setmFingerPrintAuth(FingerprintAuth fingerprintAuth) {
        this.mFingerPrintAuth = fingerprintAuth;
    }
}
