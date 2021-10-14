package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class zzac extends zzge {
    private long zzey;
    private String zzez;
    private Boolean zzfa;
    private AccountManager zzfb;
    private Boolean zzfc;
    private long zzfd;

    zzac(zzfj zzfj) {
        super(zzfj);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzge
    public final boolean zzbk() {
        Calendar instance = Calendar.getInstance();
        this.zzey = TimeUnit.MINUTES.convert((long) (instance.get(15) + instance.get(16)), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String lowerCase = locale.getLanguage().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = locale.getCountry().toLowerCase(Locale.ENGLISH);
        StringBuilder sb = new StringBuilder(String.valueOf(lowerCase).length() + 1 + String.valueOf(lowerCase2).length());
        sb.append(lowerCase);
        sb.append("-");
        sb.append(lowerCase2);
        this.zzez = sb.toString();
        return false;
    }

    public final long zzcq() {
        zzbi();
        return this.zzey;
    }

    public final String zzcr() {
        zzbi();
        return this.zzez;
    }

    public final boolean zzj(Context context) {
        if (this.zzfa == null) {
            zzae();
            this.zzfa = false;
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    packageManager.getPackageInfo("com.google.android.gms", 128);
                    this.zzfa = true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return this.zzfa.booleanValue();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final long zzcs() {
        zzo();
        return this.zzfd;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzct() {
        zzo();
        this.zzfc = null;
        this.zzfd = 0;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzcu() {
        zzo();
        long currentTimeMillis = zzx().currentTimeMillis();
        if (currentTimeMillis - this.zzfd > 86400000) {
            this.zzfc = null;
        }
        Boolean bool = this.zzfc;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.GET_ACCOUNTS") != 0) {
            zzab().zzgo().zzao("Permission error checking for dasher/unicorn accounts");
            this.zzfd = currentTimeMillis;
            this.zzfc = false;
            return false;
        }
        if (this.zzfb == null) {
            this.zzfb = AccountManager.get(getContext());
        }
        try {
            Account[] result = this.zzfb.getAccountsByTypeAndFeatures(AccountType.GOOGLE, new String[]{"service_HOSTED"}, null, null).getResult();
            if (result == null || result.length <= 0) {
                Account[] result2 = this.zzfb.getAccountsByTypeAndFeatures(AccountType.GOOGLE, new String[]{"service_uca"}, null, null).getResult();
                if (result2 != null && result2.length > 0) {
                    this.zzfc = true;
                    this.zzfd = currentTimeMillis;
                    return true;
                }
                this.zzfd = currentTimeMillis;
                this.zzfc = false;
                return false;
            }
            this.zzfc = true;
            this.zzfd = currentTimeMillis;
            return true;
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            zzab().zzgl().zza("Exception checking account types", e);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }
}
