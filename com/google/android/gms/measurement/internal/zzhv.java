package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
public final class zzhv extends zzg {
    private final zzin zzre;
    private zzdx zzrf;
    private volatile Boolean zzrg;
    private final zzaa zzrh;
    private final zzjd zzri;
    private final List<Runnable> zzrj = new ArrayList();
    private final zzaa zzrk;

    protected zzhv(zzfj zzfj) {
        super(zzfj);
        this.zzri = new zzjd(zzfj.zzx());
        this.zzre = new zzin(this);
        this.zzrh = new zzhu(this, zzfj);
        this.zzrk = new zzif(this, zzfj);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzbk() {
        return false;
    }

    @WorkerThread
    public final boolean isConnected() {
        zzo();
        zzbi();
        return this.zzrf != null;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzip() {
        zzo();
        zzbi();
        zzd(new zzie(this, zzi(true)));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void zza(zzdx zzdx, AbstractSafeParcelable abstractSafeParcelable, zzn zzn) {
        int i;
        List<AbstractSafeParcelable> zzc;
        zzo();
        zzm();
        zzbi();
        boolean zziq = zziq();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            if (!zziq || (zzc = zzu().zzc(100)) == null) {
                i = 0;
            } else {
                arrayList.addAll(zzc);
                i = zzc.size();
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList2.get(i4);
                i4++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzai) {
                    try {
                        zzdx.zza((zzai) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e) {
                        zzab().zzgk().zza("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzjn) {
                    try {
                        zzdx.zza((zzjn) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e2) {
                        zzab().zzgk().zza("Failed to send attribute to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzq) {
                    try {
                        zzdx.zza((zzq) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e3) {
                        zzab().zzgk().zza("Failed to send conditional property to the service", e3);
                    }
                } else {
                    zzab().zzgk().zzao("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzc(zzai zzai, String str) {
        Preconditions.checkNotNull(zzai);
        zzo();
        zzbi();
        boolean zziq = zziq();
        zzd(new zzih(this, zziq, zziq && zzu().zza(zzai), zzai, zzi(true), str));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzd(zzq zzq) {
        Preconditions.checkNotNull(zzq);
        zzo();
        zzbi();
        zzae();
        zzd(new zzig(this, true, zzu().zzc(zzq), new zzq(zzq), zzi(true), zzq));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzq>> atomicReference, String str, String str2, String str3) {
        zzo();
        zzbi();
        zzd(new zzij(this, atomicReference, str, str2, str3, zzi(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzp zzp, String str, String str2) {
        zzo();
        zzbi();
        zzd(new zzii(this, str, str2, zzi(false), zzp));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzjn>> atomicReference, String str, String str2, String str3, boolean z) {
        zzo();
        zzbi();
        zzd(new zzil(this, atomicReference, str, str2, str3, z, zzi(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzp zzp, String str, String str2, boolean z) {
        zzo();
        zzbi();
        zzd(new zzik(this, str, str2, z, zzi(false), zzp));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzjn zzjn) {
        zzo();
        zzbi();
        zzd(new zzhx(this, zziq() && zzu().zza(zzjn), zzjn, zzi(true)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzjn>> atomicReference, boolean z) {
        zzo();
        zzbi();
        zzd(new zzhw(this, atomicReference, zzi(false), z));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void resetAnalyticsData() {
        zzo();
        zzm();
        zzbi();
        zzn zzi = zzi(false);
        if (zziq()) {
            zzu().resetAnalyticsData();
        }
        zzd(new zzhz(this, zzi));
    }

    private final boolean zziq() {
        zzae();
        return true;
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzo();
        zzbi();
        zzd(new zzhy(this, atomicReference, zzi(false)));
    }

    @WorkerThread
    public final void getAppInstanceId(zzp zzp) {
        zzo();
        zzbi();
        zzd(new zzib(this, zzi(false), zzp));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzim() {
        zzo();
        zzbi();
        zzn zzi = zzi(true);
        boolean zza = zzad().zza(zzak.zzjd);
        if (zza) {
            zzu().zzgh();
        }
        zzd(new zzia(this, zzi, zza));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzhr zzhr) {
        zzo();
        zzbi();
        zzd(new zzid(this, zzhr));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzir() {
        zzo();
        this.zzri.start();
        this.zzrh.zzv(zzak.zzhl.get(null).longValue());
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzis() {
        boolean z;
        boolean z2;
        zzo();
        zzbi();
        if (!isConnected()) {
            boolean z3 = false;
            if (this.zzrg == null) {
                zzo();
                zzbi();
                Boolean zzhe = zzac().zzhe();
                if (zzhe == null || !zzhe.booleanValue()) {
                    zzae();
                    if (zzr().zzgg() == 1) {
                        z = true;
                        z2 = true;
                    } else {
                        zzab().zzgs().zzao("Checking service availability");
                        int zzd = zzz().zzd(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                        if (zzd == 9) {
                            zzab().zzgn().zzao("Service invalid");
                            z = false;
                            z2 = false;
                        } else if (zzd != 18) {
                            switch (zzd) {
                                case 0:
                                    zzab().zzgs().zzao("Service available");
                                    z = true;
                                    z2 = true;
                                    break;
                                case 1:
                                    zzab().zzgs().zzao("Service missing");
                                    z = false;
                                    z2 = true;
                                    break;
                                case 2:
                                    zzab().zzgr().zzao("Service container out of date");
                                    if (zzz().zzjx() >= 15300) {
                                        Boolean zzhe2 = zzac().zzhe();
                                        z = zzhe2 == null || zzhe2.booleanValue();
                                        z2 = false;
                                        break;
                                    } else {
                                        z = false;
                                        z2 = true;
                                        break;
                                    }
                                    break;
                                case 3:
                                    zzab().zzgn().zzao("Service disabled");
                                    z = false;
                                    z2 = false;
                                    break;
                                default:
                                    zzab().zzgn().zza("Unexpected service status", Integer.valueOf(zzd));
                                    z = false;
                                    z2 = false;
                                    break;
                            }
                        } else {
                            zzab().zzgn().zzao("Service updating");
                            z = true;
                            z2 = true;
                        }
                    }
                    if (!z && zzad().zzbw()) {
                        zzab().zzgk().zzao("No way to upload. Consider using the full version of Analytics");
                        z2 = false;
                    }
                    if (z2) {
                        zzac().zzd(z);
                    }
                } else {
                    z = true;
                }
                this.zzrg = Boolean.valueOf(z);
            }
            if (this.zzrg.booleanValue()) {
                this.zzre.zzix();
            } else if (!zzad().zzbw()) {
                zzae();
                List<ResolveInfo> queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (queryIntentServices != null && queryIntentServices.size() > 0) {
                    z3 = true;
                }
                if (z3) {
                    Intent intent = new Intent("com.google.android.gms.measurement.START");
                    Context context = getContext();
                    zzae();
                    intent.setComponent(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                    this.zzre.zzb(intent);
                    return;
                }
                zzab().zzgk().zzao("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzit() {
        return this.zzrg;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    @WorkerThread
    public final void zza(zzdx zzdx) {
        zzo();
        Preconditions.checkNotNull(zzdx);
        this.zzrf = zzdx;
        zzir();
        zziv();
    }

    @WorkerThread
    public final void disconnect() {
        zzo();
        zzbi();
        this.zzre.zziw();
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzre);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzrf = null;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void onServiceDisconnected(ComponentName componentName) {
        zzo();
        if (this.zzrf != null) {
            this.zzrf = null;
            zzab().zzgs().zza("Disconnected from device MeasurementService", componentName);
            zzo();
            zzis();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zziu() {
        zzo();
        if (isConnected()) {
            zzab().zzgs().zzao("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    private final void zzd(Runnable runnable) throws IllegalStateException {
        zzo();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzrj.size()) >= 1000) {
            zzab().zzgk().zzao("Discarding data. Max runnable queue size reached");
        } else {
            this.zzrj.add(runnable);
            this.zzrk.zzv(60000);
            zzis();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zziv() {
        zzo();
        zzab().zzgs().zza("Processing queued up service tasks", Integer.valueOf(this.zzrj.size()));
        for (Runnable runnable : this.zzrj) {
            try {
                runnable.run();
            } catch (Exception e) {
                zzab().zzgk().zza("Task exception while flushing queue", e);
            }
        }
        this.zzrj.clear();
        this.zzrk.cancel();
    }

    @Nullable
    @WorkerThread
    private final zzn zzi(boolean z) {
        zzae();
        return zzr().zzai(z ? zzab().zzgu() : null);
    }

    @WorkerThread
    public final void zza(zzp zzp, zzai zzai, String str) {
        zzo();
        zzbi();
        if (zzz().zzd(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) != 0) {
            zzab().zzgn().zzao("Not bundling data. Service unavailable or out of date");
            zzz().zza(zzp, new byte[0]);
            return;
        }
        zzd(new zzic(this, zzai, str, zzp));
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzgp zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzdy zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhv zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhq zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeb zzu() {
        return super.zzu();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziw zzv() {
        return super.zzv();
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
