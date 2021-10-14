package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzec implements zzgy {
    private int tag;
    private final zzeb zzadu;
    private int zzadv;
    private int zzadw = 0;

    public static zzec zza(zzeb zzeb) {
        if (zzeb.zzads != null) {
            return zzeb.zzads;
        }
        return new zzec(zzeb);
    }

    private zzec(zzeb zzeb) {
        this.zzadu = (zzeb) zzez.zza((Object) zzeb, "input");
        this.zzadu.zzads = this;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzsy() throws IOException {
        int i = this.zzadw;
        if (i != 0) {
            this.tag = i;
            this.zzadw = 0;
        } else {
            this.tag = this.zzadu.zzsg();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzadv) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int getTag() {
        return this.tag;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final boolean zzsz() throws IOException {
        int i;
        if (this.zzadu.zzsw() || (i = this.tag) == this.zzadv) {
            return false;
        }
        return this.zzadu.zzau(i);
    }

    private final void zzba(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzfi.zzuy();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final double readDouble() throws IOException {
        zzba(1);
        return this.zzadu.readDouble();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final float readFloat() throws IOException {
        zzba(5);
        return this.zzadu.readFloat();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzsh() throws IOException {
        zzba(0);
        return this.zzadu.zzsh();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzsi() throws IOException {
        zzba(0);
        return this.zzadu.zzsi();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzsj() throws IOException {
        zzba(0);
        return this.zzadu.zzsj();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzsk() throws IOException {
        zzba(1);
        return this.zzadu.zzsk();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzsl() throws IOException {
        zzba(5);
        return this.zzadu.zzsl();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final boolean zzsm() throws IOException {
        zzba(0);
        return this.zzadu.zzsm();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final String readString() throws IOException {
        zzba(2);
        return this.zzadu.readString();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final String zzsn() throws IOException {
        zzba(2);
        return this.zzadu.zzsn();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final <T> T zza(zzgx<T> zzgx, zzel zzel) throws IOException {
        zzba(2);
        return (T) zzc(zzgx, zzel);
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final <T> T zzb(zzgx<T> zzgx, zzel zzel) throws IOException {
        zzba(3);
        return (T) zzd(zzgx, zzel);
    }

    private final <T> T zzc(zzgx<T> zzgx, zzel zzel) throws IOException {
        int zzsp = this.zzadu.zzsp();
        if (this.zzadu.zzadp < this.zzadu.zzadq) {
            int zzaw = this.zzadu.zzaw(zzsp);
            T newInstance = zzgx.newInstance();
            this.zzadu.zzadp++;
            zzgx.zza(newInstance, this, zzel);
            zzgx.zzj(newInstance);
            this.zzadu.zzat(0);
            zzeb zzeb = this.zzadu;
            zzeb.zzadp--;
            this.zzadu.zzax(zzaw);
            return newInstance;
        }
        throw zzfi.zzuz();
    }

    private final <T> T zzd(zzgx<T> zzgx, zzel zzel) throws IOException {
        int i = this.zzadv;
        this.zzadv = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzgx.newInstance();
            zzgx.zza(newInstance, this, zzel);
            zzgx.zzj(newInstance);
            if (this.tag == this.zzadv) {
                return newInstance;
            }
            throw zzfi.zzva();
        } finally {
            this.zzadv = i;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final zzdp zzso() throws IOException {
        zzba(2);
        return this.zzadu.zzso();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzsp() throws IOException {
        zzba(0);
        return this.zzadu.zzsp();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzsq() throws IOException {
        zzba(0);
        return this.zzadu.zzsq();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzsr() throws IOException {
        zzba(5);
        return this.zzadu.zzsr();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzss() throws IOException {
        zzba(1);
        return this.zzadu.zzss();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzst() throws IOException {
        zzba(0);
        return this.zzadu.zzst();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzsu() throws IOException {
        zzba(0);
        return this.zzadu.zzsu();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zze(List<Double> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzeh) {
            zzeh zzeh = (zzeh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzsp = this.zzadu.zzsp();
                    zzbb(zzsp);
                    int zzsx = this.zzadu.zzsx() + zzsp;
                    do {
                        zzeh.zzf(this.zzadu.readDouble());
                    } while (this.zzadu.zzsx() < zzsx);
                    return;
                default:
                    throw zzfi.zzuy();
            }
            do {
                zzeh.zzf(this.zzadu.readDouble());
                if (!this.zzadu.zzsw()) {
                    zzsg2 = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg2 == this.tag);
            this.zzadw = zzsg2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzsp2 = this.zzadu.zzsp();
                zzbb(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Double.valueOf(this.zzadu.readDouble()));
                } while (this.zzadu.zzsx() < zzsx2);
                return;
            default:
                throw zzfi.zzuy();
        }
        do {
            list.add(Double.valueOf(this.zzadu.readDouble()));
            if (!this.zzadu.zzsw()) {
                zzsg = this.zzadu.zzsg();
            } else {
                return;
            }
        } while (zzsg == this.tag);
        this.zzadw = zzsg;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzf(List<Float> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzeu) {
            zzeu zzeu = (zzeu) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzsp = this.zzadu.zzsp();
                zzbc(zzsp);
                int zzsx = this.zzadu.zzsx() + zzsp;
                do {
                    zzeu.zzc(this.zzadu.readFloat());
                } while (this.zzadu.zzsx() < zzsx);
            } else if (i == 5) {
                do {
                    zzeu.zzc(this.zzadu.readFloat());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzsp2 = this.zzadu.zzsp();
                zzbc(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Float.valueOf(this.zzadu.readFloat()));
                } while (this.zzadu.zzsx() < zzsx2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzadu.readFloat()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzg(List<Long> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfw.zzby(this.zzadu.zzsh());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfw.zzby(this.zzadu.zzsh());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzadu.zzsh()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Long.valueOf(this.zzadu.zzsh()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzh(List<Long> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfw.zzby(this.zzadu.zzsi());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfw.zzby(this.zzadu.zzsi());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzadu.zzsi()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Long.valueOf(this.zzadu.zzsi()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzi(List<Integer> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfa.zzbu(this.zzadu.zzsj());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfa.zzbu(this.zzadu.zzsj());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsj()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsj()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzj(List<Long> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzsp = this.zzadu.zzsp();
                    zzbb(zzsp);
                    int zzsx = this.zzadu.zzsx() + zzsp;
                    do {
                        zzfw.zzby(this.zzadu.zzsk());
                    } while (this.zzadu.zzsx() < zzsx);
                    return;
                default:
                    throw zzfi.zzuy();
            }
            do {
                zzfw.zzby(this.zzadu.zzsk());
                if (!this.zzadu.zzsw()) {
                    zzsg2 = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg2 == this.tag);
            this.zzadw = zzsg2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzsp2 = this.zzadu.zzsp();
                zzbb(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Long.valueOf(this.zzadu.zzsk()));
                } while (this.zzadu.zzsx() < zzsx2);
                return;
            default:
                throw zzfi.zzuy();
        }
        do {
            list.add(Long.valueOf(this.zzadu.zzsk()));
            if (!this.zzadu.zzsw()) {
                zzsg = this.zzadu.zzsg();
            } else {
                return;
            }
        } while (zzsg == this.tag);
        this.zzadw = zzsg;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzk(List<Integer> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzsp = this.zzadu.zzsp();
                zzbc(zzsp);
                int zzsx = this.zzadu.zzsx() + zzsp;
                do {
                    zzfa.zzbu(this.zzadu.zzsl());
                } while (this.zzadu.zzsx() < zzsx);
            } else if (i == 5) {
                do {
                    zzfa.zzbu(this.zzadu.zzsl());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzsp2 = this.zzadu.zzsp();
                zzbc(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsl()));
                } while (this.zzadu.zzsx() < zzsx2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsl()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzl(List<Boolean> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzdn) {
            zzdn zzdn = (zzdn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdn.addBoolean(this.zzadu.zzsm());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzdn.addBoolean(this.zzadu.zzsm());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzadu.zzsm()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Boolean.valueOf(this.zzadu.zzsm()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzm(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzsg;
        int zzsg2;
        if ((this.tag & 7) != 2) {
            throw zzfi.zzuy();
        } else if (!(list instanceof zzfp) || z) {
            do {
                list.add(z ? zzsn() : readString());
                if (!this.zzadu.zzsw()) {
                    zzsg = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg == this.tag);
            this.zzadw = zzsg;
        } else {
            zzfp zzfp = (zzfp) list;
            do {
                zzfp.zzc(zzso());
                if (!this.zzadu.zzsw()) {
                    zzsg2 = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg2 == this.tag);
            this.zzadw = zzsg2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.List<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzgy
    public final <T> void zza(List<T> list, zzgx<T> zzgx, zzel zzel) throws IOException {
        int zzsg;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzc(zzgx, zzel));
                if (!this.zzadu.zzsw() && this.zzadw == 0) {
                    zzsg = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg == i);
            this.zzadw = zzsg;
            return;
        }
        throw zzfi.zzuy();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.List<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzgy
    public final <T> void zzb(List<T> list, zzgx<T> zzgx, zzel zzel) throws IOException {
        int zzsg;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzgx, zzel));
                if (!this.zzadu.zzsw() && this.zzadw == 0) {
                    zzsg = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg == i);
            this.zzadw = zzsg;
            return;
        }
        throw zzfi.zzuy();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzn(List<zzdp> list) throws IOException {
        int zzsg;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzso());
                if (!this.zzadu.zzsw()) {
                    zzsg = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg == this.tag);
            this.zzadw = zzsg;
            return;
        }
        throw zzfi.zzuy();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzo(List<Integer> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfa.zzbu(this.zzadu.zzsp());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfa.zzbu(this.zzadu.zzsp());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsp()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsp()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzp(List<Integer> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfa.zzbu(this.zzadu.zzsq());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfa.zzbu(this.zzadu.zzsq());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsq()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsq()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzq(List<Integer> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzsp = this.zzadu.zzsp();
                zzbc(zzsp);
                int zzsx = this.zzadu.zzsx() + zzsp;
                do {
                    zzfa.zzbu(this.zzadu.zzsr());
                } while (this.zzadu.zzsx() < zzsx);
            } else if (i == 5) {
                do {
                    zzfa.zzbu(this.zzadu.zzsr());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzsp2 = this.zzadu.zzsp();
                zzbc(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsr()));
                } while (this.zzadu.zzsx() < zzsx2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsr()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzr(List<Long> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzsp = this.zzadu.zzsp();
                    zzbb(zzsp);
                    int zzsx = this.zzadu.zzsx() + zzsp;
                    do {
                        zzfw.zzby(this.zzadu.zzss());
                    } while (this.zzadu.zzsx() < zzsx);
                    return;
                default:
                    throw zzfi.zzuy();
            }
            do {
                zzfw.zzby(this.zzadu.zzss());
                if (!this.zzadu.zzsw()) {
                    zzsg2 = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg2 == this.tag);
            this.zzadw = zzsg2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzsp2 = this.zzadu.zzsp();
                zzbb(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Long.valueOf(this.zzadu.zzss()));
                } while (this.zzadu.zzsx() < zzsx2);
                return;
            default:
                throw zzfi.zzuy();
        }
        do {
            list.add(Long.valueOf(this.zzadu.zzss()));
            if (!this.zzadu.zzsw()) {
                zzsg = this.zzadu.zzsg();
            } else {
                return;
            }
        } while (zzsg == this.tag);
        this.zzadw = zzsg;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzs(List<Integer> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfa.zzbu(this.zzadu.zzst());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfa.zzbu(this.zzadu.zzst());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzst()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Integer.valueOf(this.zzadu.zzst()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzt(List<Long> list) throws IOException {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfw.zzby(this.zzadu.zzsu());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfw.zzby(this.zzadu.zzsu());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.zzuy();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzadu.zzsu()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Long.valueOf(this.zzadu.zzsu()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.zzuy();
            }
        }
    }

    private static void zzbb(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzfi.zzva();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.util.Map<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzgy
    public final <K, V> void zza(Map<K, V> map, zzfz<K, V> zzfz, zzel zzel) throws IOException {
        zzba(2);
        int zzaw = this.zzadu.zzaw(this.zzadu.zzsp());
        Object obj = zzfz.zzakc;
        Object obj2 = zzfz.zzaba;
        while (true) {
            try {
                int zzsy = zzsy();
                if (zzsy == Integer.MAX_VALUE || this.zzadu.zzsw()) {
                    map.put(obj, obj2);
                } else {
                    switch (zzsy) {
                        case 1:
                            obj = zza(zzfz.zzakb, (Class<?>) null, (zzel) null);
                            break;
                        case 2:
                            obj2 = zza(zzfz.zzakd, zzfz.zzaba.getClass(), zzel);
                            break;
                        default:
                            try {
                                if (zzsz()) {
                                    break;
                                } else {
                                    throw new zzfi("Unable to parse map entry.");
                                }
                            } catch (zzfh unused) {
                                if (zzsz()) {
                                    break;
                                } else {
                                    throw new zzfi("Unable to parse map entry.");
                                }
                            }
                    }
                }
            } finally {
                this.zzadu.zzax(zzaw);
            }
        }
        map.put(obj, obj2);
    }

    private final Object zza(zzig zzig, Class<?> cls, zzel zzel) throws IOException {
        switch (zzig) {
            case BOOL:
                return Boolean.valueOf(zzsm());
            case BYTES:
                return zzso();
            case DOUBLE:
                return Double.valueOf(readDouble());
            case ENUM:
                return Integer.valueOf(zzsq());
            case FIXED32:
                return Integer.valueOf(zzsl());
            case FIXED64:
                return Long.valueOf(zzsk());
            case FLOAT:
                return Float.valueOf(readFloat());
            case INT32:
                return Integer.valueOf(zzsj());
            case INT64:
                return Long.valueOf(zzsi());
            case MESSAGE:
                zzba(2);
                return zzc(zzgt.zzvy().zzf(cls), zzel);
            case SFIXED32:
                return Integer.valueOf(zzsr());
            case SFIXED64:
                return Long.valueOf(zzss());
            case SINT32:
                return Integer.valueOf(zzst());
            case SINT64:
                return Long.valueOf(zzsu());
            case STRING:
                return zzsn();
            case UINT32:
                return Integer.valueOf(zzsp());
            case UINT64:
                return Long.valueOf(zzsh());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzbc(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzfi.zzva();
        }
    }

    private final void zzbd(int i) throws IOException {
        if (this.zzadu.zzsx() != i) {
            throw zzfi.zzut();
        }
    }
}
