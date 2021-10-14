package com.google.android.gms.internal.measurement;

import com.bumptech.glide.load.model.UnitModelLoader;
import com.google.android.gms.internal.measurement.zzey;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* access modifiers changed from: package-private */
public final class zzgm<T> implements zzgx<T> {
    private static final int[] zzakh = new int[0];
    private static final Unsafe zzaki = zzhv.zzwv();
    private final int[] zzakj;
    private final Object[] zzakk;
    private final int zzakl;
    private final int zzakm;
    private final zzgi zzakn;
    private final boolean zzako;
    private final boolean zzakp;
    private final boolean zzakq;
    private final boolean zzakr;
    private final int[] zzaks;
    private final int zzakt;
    private final int zzaku;
    private final zzgq zzakv;
    private final zzfs zzakw;
    private final zzhp<?, ?> zzakx;
    private final zzen<?> zzaky;
    private final zzgb zzakz;

    private zzgm(int[] iArr, Object[] objArr, int i, int i2, zzgi zzgi, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzgq zzgq, zzfs zzfs, zzhp<?, ?> zzhp, zzen<?> zzen, zzgb zzgb) {
        this.zzakj = iArr;
        this.zzakk = objArr;
        this.zzakl = i;
        this.zzakm = i2;
        this.zzakp = zzgi instanceof zzey;
        this.zzakq = z;
        this.zzako = zzen != null && zzen.zze(zzgi);
        this.zzakr = false;
        this.zzaks = iArr2;
        this.zzakt = i3;
        this.zzaku = i4;
        this.zzakv = zzgq;
        this.zzakw = zzfs;
        this.zzakx = zzhp;
        this.zzaky = zzen;
        this.zzakn = zzgi;
        this.zzakz = zzgb;
    }

    private static boolean zzcc(int i) {
        return (i & 536870912) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:165:0x0381  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x03e5  */
    static <T> zzgm<T> zza(Class<T> cls, zzgg zzgg, zzgq zzgq, zzfs zzfs, zzhp<?, ?> zzhp, zzen<?> zzen, zzgb zzgb) {
        int i;
        int i2;
        char c;
        int[] iArr;
        char c2;
        char c3;
        int i3;
        char c4;
        int i4;
        int i5;
        int i6;
        String str;
        char c5;
        int i7;
        char c6;
        int i8;
        int i9;
        int i10;
        int i11;
        Class<?> cls2;
        int i12;
        Field field;
        int i13;
        char charAt;
        int i14;
        char c7;
        Field field2;
        Field field3;
        int i15;
        char charAt2;
        int i16;
        char charAt3;
        int i17;
        char charAt4;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        char charAt5;
        int i24;
        char charAt6;
        int i25;
        char charAt7;
        int i26;
        char charAt8;
        char charAt9;
        char charAt10;
        char charAt11;
        char charAt12;
        char charAt13;
        char charAt14;
        if (zzgg instanceof zzgv) {
            zzgv zzgv = (zzgv) zzgg;
            int i27 = 0;
            boolean z = zzgv.zzvr() == zzey.zzd.zzaim;
            String zzvz = zzgv.zzvz();
            int length = zzvz.length();
            int charAt15 = zzvz.charAt(0);
            if (charAt15 >= 55296) {
                int i28 = charAt15 & 8191;
                int i29 = 1;
                int i30 = 13;
                while (true) {
                    i = i29 + 1;
                    charAt14 = zzvz.charAt(i29);
                    if (charAt14 < 55296) {
                        break;
                    }
                    i28 |= (charAt14 & 8191) << i30;
                    i30 += 13;
                    i29 = i;
                }
                charAt15 = (charAt14 << i30) | i28;
            } else {
                i = 1;
            }
            int i31 = i + 1;
            int charAt16 = zzvz.charAt(i);
            if (charAt16 >= 55296) {
                int i32 = charAt16 & 8191;
                int i33 = 13;
                while (true) {
                    i2 = i31 + 1;
                    charAt13 = zzvz.charAt(i31);
                    if (charAt13 < 55296) {
                        break;
                    }
                    i32 |= (charAt13 & 8191) << i33;
                    i33 += 13;
                    i31 = i2;
                }
                charAt16 = i32 | (charAt13 << i33);
            } else {
                i2 = i31;
            }
            if (charAt16 == 0) {
                iArr = zzakh;
                i4 = 0;
                c4 = 0;
                i3 = 0;
                c3 = 0;
                c2 = 0;
                c = 0;
            } else {
                int i34 = i2 + 1;
                char charAt17 = zzvz.charAt(i2);
                if (charAt17 >= 55296) {
                    int i35 = charAt17 & 8191;
                    int i36 = 13;
                    while (true) {
                        i18 = i34 + 1;
                        charAt12 = zzvz.charAt(i34);
                        if (charAt12 < 55296) {
                            break;
                        }
                        i35 |= (charAt12 & 8191) << i36;
                        i36 += 13;
                        i34 = i18;
                    }
                    charAt17 = (charAt12 << i36) | i35;
                } else {
                    i18 = i34;
                }
                int i37 = i18 + 1;
                int charAt18 = zzvz.charAt(i18);
                if (charAt18 >= 55296) {
                    int i38 = charAt18 & 8191;
                    int i39 = 13;
                    while (true) {
                        i19 = i37 + 1;
                        charAt11 = zzvz.charAt(i37);
                        if (charAt11 < 55296) {
                            break;
                        }
                        i38 |= (charAt11 & 8191) << i39;
                        i39 += 13;
                        i37 = i19;
                    }
                    charAt18 = i38 | (charAt11 << i39);
                } else {
                    i19 = i37;
                }
                int i40 = i19 + 1;
                char charAt19 = zzvz.charAt(i19);
                if (charAt19 >= 55296) {
                    int i41 = charAt19 & 8191;
                    int i42 = 13;
                    while (true) {
                        i20 = i40 + 1;
                        charAt10 = zzvz.charAt(i40);
                        if (charAt10 < 55296) {
                            break;
                        }
                        i41 |= (charAt10 & 8191) << i42;
                        i42 += 13;
                        i40 = i20;
                    }
                    charAt19 = (charAt10 << i42) | i41;
                } else {
                    i20 = i40;
                }
                int i43 = i20 + 1;
                c3 = zzvz.charAt(i20);
                if (c3 >= 55296) {
                    int i44 = c3 & 8191;
                    int i45 = 13;
                    while (true) {
                        i21 = i43 + 1;
                        charAt9 = zzvz.charAt(i43);
                        if (charAt9 < 55296) {
                            break;
                        }
                        i44 |= (charAt9 & 8191) << i45;
                        i45 += 13;
                        i43 = i21;
                    }
                    c3 = (charAt9 << i45) | i44;
                } else {
                    i21 = i43;
                }
                int i46 = i21 + 1;
                c2 = zzvz.charAt(i21);
                if (c2 >= 55296) {
                    int i47 = c2 & 8191;
                    int i48 = 13;
                    while (true) {
                        i26 = i46 + 1;
                        charAt8 = zzvz.charAt(i46);
                        if (charAt8 < 55296) {
                            break;
                        }
                        i47 |= (charAt8 & 8191) << i48;
                        i48 += 13;
                        i46 = i26;
                    }
                    c2 = (charAt8 << i48) | i47;
                    i46 = i26;
                }
                int i49 = i46 + 1;
                i4 = zzvz.charAt(i46);
                if (i4 >= 55296) {
                    int i50 = i4 & 8191;
                    int i51 = 13;
                    while (true) {
                        i25 = i49 + 1;
                        charAt7 = zzvz.charAt(i49);
                        if (charAt7 < 55296) {
                            break;
                        }
                        i50 |= (charAt7 & 8191) << i51;
                        i51 += 13;
                        i49 = i25;
                    }
                    i4 = i50 | (charAt7 << i51);
                    i49 = i25;
                }
                int i52 = i49 + 1;
                int charAt20 = zzvz.charAt(i49);
                if (charAt20 >= 55296) {
                    int i53 = 13;
                    int i54 = charAt20 & 8191;
                    int i55 = i52;
                    while (true) {
                        i24 = i55 + 1;
                        charAt6 = zzvz.charAt(i55);
                        if (charAt6 < 55296) {
                            break;
                        }
                        i54 |= (charAt6 & 8191) << i53;
                        i53 += 13;
                        i55 = i24;
                    }
                    charAt20 = i54 | (charAt6 << i53);
                    i22 = i24;
                } else {
                    i22 = i52;
                }
                int i56 = i22 + 1;
                i27 = zzvz.charAt(i22);
                if (i27 >= 55296) {
                    int i57 = 13;
                    int i58 = i27 & 8191;
                    int i59 = i56;
                    while (true) {
                        i23 = i59 + 1;
                        charAt5 = zzvz.charAt(i59);
                        if (charAt5 < 55296) {
                            break;
                        }
                        i58 |= (charAt5 & 8191) << i57;
                        i57 += 13;
                        i59 = i23;
                    }
                    i27 = i58 | (charAt5 << i57);
                    i56 = i23;
                }
                iArr = new int[(i27 + i4 + charAt20)];
                i3 = (charAt17 << 1) + charAt18;
                c = charAt17;
                c4 = charAt19;
                i2 = i56;
            }
            Unsafe unsafe = zzaki;
            Object[] zzwa = zzgv.zzwa();
            Class<?> cls3 = zzgv.zzvt().getClass();
            int i60 = i3;
            int[] iArr2 = new int[(c2 * 3)];
            Object[] objArr = new Object[(c2 << 1)];
            int i61 = i27 + i4;
            int i62 = i27;
            int i63 = i61;
            int i64 = 0;
            int i65 = 0;
            while (i2 < length) {
                int i66 = i2 + 1;
                int charAt21 = zzvz.charAt(i2);
                char c8 = 55296;
                if (charAt21 >= 55296) {
                    int i67 = 13;
                    int i68 = charAt21 & 8191;
                    int i69 = i66;
                    while (true) {
                        i17 = i69 + 1;
                        charAt4 = zzvz.charAt(i69);
                        if (charAt4 < c8) {
                            break;
                        }
                        i68 |= (charAt4 & 8191) << i67;
                        i67 += 13;
                        i69 = i17;
                        c8 = 55296;
                    }
                    charAt21 = i68 | (charAt4 << i67);
                    i5 = i17;
                } else {
                    i5 = i66;
                }
                int i70 = i5 + 1;
                int charAt22 = zzvz.charAt(i5);
                char c9 = 55296;
                if (charAt22 >= 55296) {
                    int i71 = 13;
                    int i72 = charAt22 & 8191;
                    int i73 = i70;
                    while (true) {
                        i16 = i73 + 1;
                        charAt3 = zzvz.charAt(i73);
                        if (charAt3 < c9) {
                            break;
                        }
                        i72 |= (charAt3 & 8191) << i71;
                        i71 += 13;
                        i73 = i16;
                        c9 = 55296;
                    }
                    charAt22 = i72 | (charAt3 << i71);
                    i6 = i16;
                } else {
                    i6 = i70;
                }
                int i74 = charAt22 & 255;
                if ((charAt22 & 1024) != 0) {
                    iArr[i64] = i65;
                    i64++;
                }
                if (i74 >= 51) {
                    int i75 = i6 + 1;
                    int charAt23 = zzvz.charAt(i6);
                    char c10 = 55296;
                    if (charAt23 >= 55296) {
                        int i76 = charAt23 & 8191;
                        int i77 = 13;
                        while (true) {
                            i15 = i75 + 1;
                            charAt2 = zzvz.charAt(i75);
                            if (charAt2 < c10) {
                                break;
                            }
                            i76 |= (charAt2 & 8191) << i77;
                            i77 += 13;
                            i75 = i15;
                            c10 = 55296;
                        }
                        charAt23 = i76 | (charAt2 << i77);
                        i75 = i15;
                    }
                    int i78 = i74 - 51;
                    if (i78 == 9 || i78 == 17) {
                        i14 = 1;
                        objArr[((i65 / 3) << 1) + 1] = zzwa[i60];
                        i60++;
                    } else if (i78 == 12 && (charAt15 & 1) == 1) {
                        objArr[((i65 / 3) << 1) + 1] = zzwa[i60];
                        i60++;
                        i14 = 1;
                    } else {
                        i14 = 1;
                    }
                    int i79 = charAt23 << i14;
                    Object obj = zzwa[i79];
                    if (obj instanceof Field) {
                        field2 = (Field) obj;
                        c7 = c4;
                    } else {
                        field2 = zza(cls3, (String) obj);
                        zzwa[i79] = field2;
                        c7 = c4;
                    }
                    int objectFieldOffset = (int) unsafe.objectFieldOffset(field2);
                    int i80 = i79 + 1;
                    Object obj2 = zzwa[i80];
                    if (obj2 instanceof Field) {
                        field3 = (Field) obj2;
                    } else {
                        field3 = zza(cls3, (String) obj2);
                        zzwa[i80] = field3;
                    }
                    str = zzvz;
                    cls2 = cls3;
                    i7 = i60;
                    i9 = objectFieldOffset;
                    i10 = (int) unsafe.objectFieldOffset(field3);
                    c5 = c7;
                    c6 = c3;
                    i11 = 0;
                    i8 = charAt21;
                    i2 = i75;
                } else {
                    int i81 = i60 + 1;
                    Field zza = zza(cls3, (String) zzwa[i60]);
                    c6 = c3;
                    if (i74 == 9 || i74 == 17) {
                        c5 = c4;
                        objArr[((i65 / 3) << 1) + 1] = zza.getType();
                    } else if (i74 == 27 || i74 == 49) {
                        c5 = c4;
                        objArr[((i65 / 3) << 1) + 1] = zzwa[i81];
                        i8 = charAt21;
                        i81++;
                        i9 = (int) unsafe.objectFieldOffset(zza);
                        if ((charAt15 & 1) == 1 || i74 > 17) {
                            str = zzvz;
                            cls2 = cls3;
                            i7 = i81;
                            i12 = i6;
                            i11 = 0;
                            i10 = 0;
                        } else {
                            i12 = i6 + 1;
                            int charAt24 = zzvz.charAt(i6);
                            if (charAt24 >= 55296) {
                                int i82 = charAt24 & 8191;
                                int i83 = 13;
                                while (true) {
                                    i13 = i12 + 1;
                                    charAt = zzvz.charAt(i12);
                                    if (charAt < 55296) {
                                        break;
                                    }
                                    i82 |= (charAt & 8191) << i83;
                                    i83 += 13;
                                    i12 = i13;
                                }
                                charAt24 = i82 | (charAt << i83);
                                i12 = i13;
                            }
                            int i84 = (c << 1) + (charAt24 / 32);
                            Object obj3 = zzwa[i84];
                            str = zzvz;
                            if (obj3 instanceof Field) {
                                field = (Field) obj3;
                                cls2 = cls3;
                                i7 = i81;
                            } else {
                                field = zza(cls3, (String) obj3);
                                zzwa[i84] = field;
                                cls2 = cls3;
                                i7 = i81;
                            }
                            i10 = (int) unsafe.objectFieldOffset(field);
                            i11 = charAt24 % 32;
                        }
                        if (i74 >= 18 || i74 > 49) {
                            i2 = i12;
                        } else {
                            iArr[i63] = i9;
                            i63++;
                            i2 = i12;
                        }
                    } else if (i74 == 12 || i74 == 30 || i74 == 44) {
                        c5 = c4;
                        if ((charAt15 & 1) == 1) {
                            objArr[((i65 / 3) << 1) + 1] = zzwa[i81];
                            i8 = charAt21;
                            i81++;
                            i9 = (int) unsafe.objectFieldOffset(zza);
                            if ((charAt15 & 1) == 1) {
                            }
                            str = zzvz;
                            cls2 = cls3;
                            i7 = i81;
                            i12 = i6;
                            i11 = 0;
                            i10 = 0;
                            if (i74 >= 18) {
                            }
                            i2 = i12;
                        }
                    } else if (i74 == 50) {
                        int i85 = i62 + 1;
                        iArr[i62] = i65;
                        int i86 = (i65 / 3) << 1;
                        int i87 = i81 + 1;
                        objArr[i86] = zzwa[i81];
                        if ((charAt22 & 2048) != 0) {
                            i81 = i87 + 1;
                            objArr[i86 + 1] = zzwa[i87];
                            c5 = c4;
                            i62 = i85;
                            i8 = charAt21;
                        } else {
                            i62 = i85;
                            i81 = i87;
                            c5 = c4;
                            i8 = charAt21;
                        }
                        i9 = (int) unsafe.objectFieldOffset(zza);
                        if ((charAt15 & 1) == 1) {
                        }
                        str = zzvz;
                        cls2 = cls3;
                        i7 = i81;
                        i12 = i6;
                        i11 = 0;
                        i10 = 0;
                        if (i74 >= 18) {
                        }
                        i2 = i12;
                    } else {
                        c5 = c4;
                    }
                    i8 = charAt21;
                    i9 = (int) unsafe.objectFieldOffset(zza);
                    if ((charAt15 & 1) == 1) {
                    }
                    str = zzvz;
                    cls2 = cls3;
                    i7 = i81;
                    i12 = i6;
                    i11 = 0;
                    i10 = 0;
                    if (i74 >= 18) {
                    }
                    i2 = i12;
                }
                int i88 = i65 + 1;
                iArr2[i65] = i8;
                int i89 = i88 + 1;
                iArr2[i88] = (i74 << 20) | ((charAt22 & 256) != 0 ? 268435456 : 0) | ((charAt22 & 512) != 0 ? 536870912 : 0) | i9;
                i65 = i89 + 1;
                iArr2[i89] = (i11 << 20) | i10;
                cls3 = cls2;
                c3 = c6;
                i27 = i27;
                i60 = i7;
                length = length;
                z = z;
                c4 = c5;
                i64 = i64;
                zzvz = str;
            }
            return new zzgm<>(iArr2, objArr, c4, c3, zzgv.zzvt(), z, false, iArr, i27, i61, zzgq, zzfs, zzhp, zzen, zzgb);
        }
        ((zzhm) zzgg).zzvr();
        int i90 = zzey.zzd.zzaim;
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final T newInstance() {
        return (T) this.zzakv.newInstance(this.zzakn);
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final boolean equals(T t, T t2) {
        int length = this.zzakj.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzca = zzca(i);
                long j = (long) (zzca & 1048575);
                switch ((zzca & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzhv.zzo(t, j)) != Double.doubleToLongBits(zzhv.zzo(t2, j))) {
                            z = false;
                            break;
                        }
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzhv.zzn(t, j)) != Float.floatToIntBits(zzhv.zzn(t2, j))) {
                            z = false;
                            break;
                        }
                    case 2:
                        if (!zzc(t, t2, i) || zzhv.zzl(t, j) != zzhv.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 3:
                        if (!zzc(t, t2, i) || zzhv.zzl(t, j) != zzhv.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 4:
                        if (!zzc(t, t2, i) || zzhv.zzk(t, j) != zzhv.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 5:
                        if (!zzc(t, t2, i) || zzhv.zzl(t, j) != zzhv.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 6:
                        if (!zzc(t, t2, i) || zzhv.zzk(t, j) != zzhv.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 7:
                        if (!zzc(t, t2, i) || zzhv.zzm(t, j) != zzhv.zzm(t2, j)) {
                            z = false;
                            break;
                        }
                    case 8:
                        if (!zzc(t, t2, i) || !zzgz.zzd(zzhv.zzp(t, j), zzhv.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 9:
                        if (!zzc(t, t2, i) || !zzgz.zzd(zzhv.zzp(t, j), zzhv.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 10:
                        if (!zzc(t, t2, i) || !zzgz.zzd(zzhv.zzp(t, j), zzhv.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 11:
                        if (!zzc(t, t2, i) || zzhv.zzk(t, j) != zzhv.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 12:
                        if (!zzc(t, t2, i) || zzhv.zzk(t, j) != zzhv.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 13:
                        if (!zzc(t, t2, i) || zzhv.zzk(t, j) != zzhv.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 14:
                        if (!zzc(t, t2, i) || zzhv.zzl(t, j) != zzhv.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 15:
                        if (!zzc(t, t2, i) || zzhv.zzk(t, j) != zzhv.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 16:
                        if (!zzc(t, t2, i) || zzhv.zzl(t, j) != zzhv.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 17:
                        if (!zzc(t, t2, i) || !zzgz.zzd(zzhv.zzp(t, j), zzhv.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        z = zzgz.zzd(zzhv.zzp(t, j), zzhv.zzp(t2, j));
                        break;
                    case 50:
                        z = zzgz.zzd(zzhv.zzp(t, j), zzhv.zzp(t2, j));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                        long zzcb = (long) (zzcb(i) & 1048575);
                        if (zzhv.zzk(t, zzcb) != zzhv.zzk(t2, zzcb) || !zzgz.zzd(zzhv.zzp(t, j), zzhv.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                }
                if (!z) {
                    return false;
                }
                i += 3;
            } else if (!this.zzakx.zzx(t).equals(this.zzakx.zzx(t2))) {
                return false;
            } else {
                if (this.zzako) {
                    return this.zzaky.zzh(t).equals(this.zzaky.zzh(t2));
                }
                return true;
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final int hashCode(T t) {
        int length = this.zzakj.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzca = zzca(i2);
            int i3 = this.zzakj[i2];
            long j = (long) (1048575 & zzca);
            int i4 = 37;
            switch ((zzca & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzez.zzbx(Double.doubleToLongBits(zzhv.zzo(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzhv.zzn(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzez.zzbx(zzhv.zzl(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzez.zzbx(zzhv.zzl(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzhv.zzk(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzez.zzbx(zzhv.zzl(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzhv.zzk(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzez.zzs(zzhv.zzm(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzhv.zzp(t, j)).hashCode();
                    break;
                case 9:
                    Object zzp = zzhv.zzp(t, j);
                    if (zzp != null) {
                        i4 = zzp.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzhv.zzp(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzhv.zzk(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzhv.zzk(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzhv.zzk(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzez.zzbx(zzhv.zzl(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzhv.zzk(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzez.zzbx(zzhv.zzl(t, j));
                    break;
                case 17:
                    Object zzp2 = zzhv.zzp(t, j);
                    if (zzp2 != null) {
                        i4 = zzp2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzhv.zzp(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzhv.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzez.zzbx(Double.doubleToLongBits(zzf(t, j)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + Float.floatToIntBits(zzg(t, j));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzez.zzbx(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzez.zzbx(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzez.zzbx(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzez.zzs(zzj(t, j));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + ((String) zzhv.zzp(t, j)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzhv.zzp(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzhv.zzp(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzez.zzbx(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzez.zzbx(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza(t, i3, i2)) {
                        i = (i * 53) + zzhv.zzp(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzakx.zzx(t).hashCode();
        return this.zzako ? (hashCode * 53) + this.zzaky.zzh(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zzc(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzakj.length; i += 3) {
                int zzca = zzca(i);
                long j = (long) (1048575 & zzca);
                int i2 = this.zzakj[i];
                switch ((zzca & 267386880) >>> 20) {
                    case 0:
                        if (zza(t2, i)) {
                            zzhv.zza(t, j, zzhv.zzo(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza(t2, i)) {
                            zzhv.zza((Object) t, j, zzhv.zzn(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza(t2, i)) {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza(t2, i)) {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza(t2, i)) {
                            zzhv.zzb(t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza(t2, i)) {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza(t2, i)) {
                            zzhv.zzb(t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza(t2, i)) {
                            zzhv.zza(t, j, zzhv.zzm(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza(t2, i)) {
                            zzhv.zza(t, j, zzhv.zzp(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (zza(t2, i)) {
                            zzhv.zza(t, j, zzhv.zzp(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza(t2, i)) {
                            zzhv.zzb(t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza(t2, i)) {
                            zzhv.zzb(t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza(t2, i)) {
                            zzhv.zzb(t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza(t2, i)) {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza(t2, i)) {
                            zzhv.zzb(t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza(t2, i)) {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzakw.zza(t, t2, j);
                        break;
                    case 50:
                        zzgz.zza(this.zzakz, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (zza(t2, i2, i)) {
                            zzhv.zza(t, j, zzhv.zzp(t2, j));
                            zzb(t, i2, i);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (zza(t2, i2, i)) {
                            zzhv.zza(t, j, zzhv.zzp(t2, j));
                            zzb(t, i2, i);
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            if (!this.zzakq) {
                zzgz.zza(this.zzakx, t, t2);
                if (this.zzako) {
                    zzgz.zza(this.zzaky, t, t2);
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zza(T t, T t2, int i) {
        long zzca = (long) (zzca(i) & 1048575);
        if (zza(t2, i)) {
            Object zzp = zzhv.zzp(t, zzca);
            Object zzp2 = zzhv.zzp(t2, zzca);
            if (zzp != null && zzp2 != null) {
                zzhv.zza(t, zzca, zzez.zza(zzp, zzp2));
                zzb(t, i);
            } else if (zzp2 != null) {
                zzhv.zza(t, zzca, zzp2);
                zzb(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzca = zzca(i);
        int i2 = this.zzakj[i];
        long j = (long) (zzca & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zzhv.zzp(t, j);
            Object zzp2 = zzhv.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzhv.zza(t, j, zzez.zza(zzp, zzp2));
                zzb(t, i2, i);
            } else if (zzp2 != null) {
                zzhv.zza(t, j, zzp2);
                zzb(t, i2, i);
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final int zzt(T t) {
        int i;
        int i2;
        long j;
        int i3 = 267386880;
        int i4 = 1048575;
        int i5 = 1;
        if (this.zzakq) {
            Unsafe unsafe = zzaki;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.zzakj.length) {
                int zzca = zzca(i6);
                int i8 = (zzca & i3) >>> 20;
                int i9 = this.zzakj[i6];
                long j2 = (long) (zzca & 1048575);
                int i10 = (i8 < zzet.DOUBLE_LIST_PACKED.id() || i8 > zzet.SINT64_LIST_PACKED.id()) ? 0 : this.zzakj[i6 + 2] & 1048575;
                switch (i8) {
                    case 0:
                        if (zza(t, i6)) {
                            i7 += zzee.zzb(i9, 0.0d);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza(t, i6)) {
                            i7 += zzee.zzb(i9, 0.0f);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza(t, i6)) {
                            i7 += zzee.zzd(i9, zzhv.zzl(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza(t, i6)) {
                            i7 += zzee.zze(i9, zzhv.zzl(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza(t, i6)) {
                            i7 += zzee.zzg(i9, zzhv.zzk(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza(t, i6)) {
                            i7 += zzee.zzg(i9, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza(t, i6)) {
                            i7 += zzee.zzj(i9, 0);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza(t, i6)) {
                            i7 += zzee.zzc(i9, true);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza(t, i6)) {
                            Object zzp = zzhv.zzp(t, j2);
                            if (zzp instanceof zzdp) {
                                i7 += zzee.zzc(i9, (zzdp) zzp);
                                break;
                            } else {
                                i7 += zzee.zzc(i9, (String) zzp);
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (zza(t, i6)) {
                            i7 += zzgz.zzc(i9, zzhv.zzp(t, j2), zzbx(i6));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zza(t, i6)) {
                            i7 += zzee.zzc(i9, (zzdp) zzhv.zzp(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza(t, i6)) {
                            i7 += zzee.zzh(i9, zzhv.zzk(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza(t, i6)) {
                            i7 += zzee.zzl(i9, zzhv.zzk(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza(t, i6)) {
                            i7 += zzee.zzk(i9, 0);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza(t, i6)) {
                            i7 += zzee.zzh(i9, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza(t, i6)) {
                            i7 += zzee.zzi(i9, zzhv.zzk(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza(t, i6)) {
                            i7 += zzee.zzf(i9, zzhv.zzl(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zza(t, i6)) {
                            i7 += zzee.zzc(i9, (zzgi) zzhv.zzp(t, j2), zzbx(i6));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        i7 += zzgz.zzw(i9, zze(t, j2), false);
                        break;
                    case 19:
                        i7 += zzgz.zzv(i9, zze(t, j2), false);
                        break;
                    case 20:
                        i7 += zzgz.zzo(i9, zze(t, j2), false);
                        break;
                    case 21:
                        i7 += zzgz.zzp(i9, zze(t, j2), false);
                        break;
                    case 22:
                        i7 += zzgz.zzs(i9, zze(t, j2), false);
                        break;
                    case 23:
                        i7 += zzgz.zzw(i9, zze(t, j2), false);
                        break;
                    case 24:
                        i7 += zzgz.zzv(i9, zze(t, j2), false);
                        break;
                    case 25:
                        i7 += zzgz.zzx(i9, zze(t, j2), false);
                        break;
                    case 26:
                        i7 += zzgz.zzc(i9, zze(t, j2));
                        break;
                    case 27:
                        i7 += zzgz.zzc(i9, zze(t, j2), zzbx(i6));
                        break;
                    case 28:
                        i7 += zzgz.zzd(i9, (List<zzdp>) zze(t, j2));
                        break;
                    case 29:
                        i7 += zzgz.zzt(i9, zze(t, j2), false);
                        break;
                    case 30:
                        i7 += zzgz.zzr(i9, zze(t, j2), false);
                        break;
                    case 31:
                        i7 += zzgz.zzv(i9, zze(t, j2), false);
                        break;
                    case 32:
                        i7 += zzgz.zzw(i9, zze(t, j2), false);
                        break;
                    case 33:
                        i7 += zzgz.zzu(i9, zze(t, j2), false);
                        break;
                    case 34:
                        i7 += zzgz.zzq(i9, zze(t, j2), false);
                        break;
                    case 35:
                        int zzac = zzgz.zzac((List) unsafe.getObject(t, j2));
                        if (zzac <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzac);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzac) + zzac;
                            break;
                        }
                    case 36:
                        int zzab = zzgz.zzab((List) unsafe.getObject(t, j2));
                        if (zzab <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzab);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzab) + zzab;
                            break;
                        }
                    case 37:
                        int zzu = zzgz.zzu((List) unsafe.getObject(t, j2));
                        if (zzu <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzu);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzu) + zzu;
                            break;
                        }
                    case 38:
                        int zzv = zzgz.zzv((List) unsafe.getObject(t, j2));
                        if (zzv <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzv);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzv) + zzv;
                            break;
                        }
                    case 39:
                        int zzy = zzgz.zzy((List) unsafe.getObject(t, j2));
                        if (zzy <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzy);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzy) + zzy;
                            break;
                        }
                    case 40:
                        int zzac2 = zzgz.zzac((List) unsafe.getObject(t, j2));
                        if (zzac2 <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzac2);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzac2) + zzac2;
                            break;
                        }
                    case 41:
                        int zzab2 = zzgz.zzab((List) unsafe.getObject(t, j2));
                        if (zzab2 <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzab2);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzab2) + zzab2;
                            break;
                        }
                    case 42:
                        int zzad = zzgz.zzad((List) unsafe.getObject(t, j2));
                        if (zzad <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzad);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzad) + zzad;
                            break;
                        }
                    case 43:
                        int zzz = zzgz.zzz((List) unsafe.getObject(t, j2));
                        if (zzz <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzz);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzz) + zzz;
                            break;
                        }
                    case 44:
                        int zzx = zzgz.zzx((List) unsafe.getObject(t, j2));
                        if (zzx <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzx);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzx) + zzx;
                            break;
                        }
                    case 45:
                        int zzab3 = zzgz.zzab((List) unsafe.getObject(t, j2));
                        if (zzab3 <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzab3);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzab3) + zzab3;
                            break;
                        }
                    case 46:
                        int zzac3 = zzgz.zzac((List) unsafe.getObject(t, j2));
                        if (zzac3 <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzac3);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzac3) + zzac3;
                            break;
                        }
                    case 47:
                        int zzaa = zzgz.zzaa((List) unsafe.getObject(t, j2));
                        if (zzaa <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzaa);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzaa) + zzaa;
                            break;
                        }
                    case 48:
                        int zzw = zzgz.zzw((List) unsafe.getObject(t, j2));
                        if (zzw <= 0) {
                            break;
                        } else {
                            if (this.zzakr) {
                                unsafe.putInt(t, (long) i10, zzw);
                            }
                            i7 += zzee.zzbi(i9) + zzee.zzbk(zzw) + zzw;
                            break;
                        }
                    case 49:
                        i7 += zzgz.zzd(i9, zze(t, j2), zzbx(i6));
                        break;
                    case 50:
                        i7 += this.zzakz.zzb(i9, zzhv.zzp(t, j2), zzby(i6));
                        break;
                    case 51:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzb(i9, 0.0d);
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzb(i9, 0.0f);
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzd(i9, zzi(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zze(i9, zzi(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzg(i9, zzh(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzg(i9, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzj(i9, 0);
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzc(i9, true);
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza(t, i9, i6)) {
                            Object zzp2 = zzhv.zzp(t, j2);
                            if (zzp2 instanceof zzdp) {
                                i7 += zzee.zzc(i9, (zzdp) zzp2);
                                break;
                            } else {
                                i7 += zzee.zzc(i9, (String) zzp2);
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (zza(t, i9, i6)) {
                            i7 += zzgz.zzc(i9, zzhv.zzp(t, j2), zzbx(i6));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzc(i9, (zzdp) zzhv.zzp(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzh(i9, zzh(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzl(i9, zzh(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzk(i9, 0);
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzh(i9, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzi(i9, zzh(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzf(i9, zzi(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza(t, i9, i6)) {
                            i7 += zzee.zzc(i9, (zzgi) zzhv.zzp(t, j2), zzbx(i6));
                            break;
                        } else {
                            break;
                        }
                }
                i6 += 3;
                i3 = 267386880;
            }
            return i7 + zza((zzhp) this.zzakx, (Object) t);
        }
        Unsafe unsafe2 = zzaki;
        int i11 = 0;
        int i12 = 0;
        int i13 = -1;
        int i14 = 0;
        while (i11 < this.zzakj.length) {
            int zzca2 = zzca(i11);
            int[] iArr = this.zzakj;
            int i15 = iArr[i11];
            int i16 = (zzca2 & 267386880) >>> 20;
            if (i16 <= 17) {
                i2 = iArr[i11 + 2];
                int i17 = i2 & i4;
                i = i5 << (i2 >>> 20);
                if (i17 != i13) {
                    i14 = unsafe2.getInt(t, (long) i17);
                } else {
                    i17 = i13;
                }
                i13 = i17;
            } else if (!this.zzakr || i16 < zzet.DOUBLE_LIST_PACKED.id() || i16 > zzet.SINT64_LIST_PACKED.id()) {
                i2 = 0;
                i = 0;
            } else {
                i2 = this.zzakj[i11 + 2] & i4;
                i = 0;
            }
            long j3 = (long) (zzca2 & i4);
            switch (i16) {
                case 0:
                    j = 0;
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzb(i15, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    j = 0;
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzb(i15, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzd(i15, unsafe2.getLong(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    j = 0;
                    if ((i14 & i) != 0) {
                        i12 += zzee.zze(i15, unsafe2.getLong(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    j = 0;
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzg(i15, unsafe2.getInt(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i14 & i) != 0) {
                        j = 0;
                        i12 += zzee.zzg(i15, 0L);
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 6:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzj(i15, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 7:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzc(i15, true);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 8:
                    if ((i14 & i) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzdp) {
                            i12 += zzee.zzc(i15, (zzdp) object);
                            j = 0;
                            break;
                        } else {
                            i12 += zzee.zzc(i15, (String) object);
                            j = 0;
                            break;
                        }
                    } else {
                        j = 0;
                        break;
                    }
                case 9:
                    if ((i14 & i) != 0) {
                        i12 += zzgz.zzc(i15, unsafe2.getObject(t, j3), zzbx(i11));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 10:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzc(i15, (zzdp) unsafe2.getObject(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 11:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzh(i15, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 12:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzl(i15, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 13:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzk(i15, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 14:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzh(i15, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 15:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzi(i15, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 16:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzf(i15, unsafe2.getLong(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 17:
                    if ((i14 & i) != 0) {
                        i12 += zzee.zzc(i15, (zzgi) unsafe2.getObject(t, j3), zzbx(i11));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 18:
                    i12 += zzgz.zzw(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 19:
                    i12 += zzgz.zzv(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 20:
                    i12 += zzgz.zzo(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 21:
                    i12 += zzgz.zzp(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 22:
                    i12 += zzgz.zzs(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 23:
                    i12 += zzgz.zzw(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 24:
                    i12 += zzgz.zzv(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 25:
                    i12 += zzgz.zzx(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 26:
                    i12 += zzgz.zzc(i15, (List) unsafe2.getObject(t, j3));
                    j = 0;
                    break;
                case 27:
                    i12 += zzgz.zzc(i15, (List<?>) ((List) unsafe2.getObject(t, j3)), zzbx(i11));
                    j = 0;
                    break;
                case 28:
                    i12 += zzgz.zzd(i15, (List) unsafe2.getObject(t, j3));
                    j = 0;
                    break;
                case 29:
                    i12 += zzgz.zzt(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 30:
                    i12 += zzgz.zzr(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 31:
                    i12 += zzgz.zzv(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 32:
                    i12 += zzgz.zzw(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 33:
                    i12 += zzgz.zzu(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 34:
                    i12 += zzgz.zzq(i15, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 35:
                    int zzac4 = zzgz.zzac((List) unsafe2.getObject(t, j3));
                    if (zzac4 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzac4);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzac4) + zzac4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 36:
                    int zzab4 = zzgz.zzab((List) unsafe2.getObject(t, j3));
                    if (zzab4 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzab4);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzab4) + zzab4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 37:
                    int zzu2 = zzgz.zzu((List) unsafe2.getObject(t, j3));
                    if (zzu2 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzu2);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzu2) + zzu2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 38:
                    int zzv2 = zzgz.zzv((List) unsafe2.getObject(t, j3));
                    if (zzv2 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzv2);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzv2) + zzv2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 39:
                    int zzy2 = zzgz.zzy((List) unsafe2.getObject(t, j3));
                    if (zzy2 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzy2);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzy2) + zzy2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 40:
                    int zzac5 = zzgz.zzac((List) unsafe2.getObject(t, j3));
                    if (zzac5 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzac5);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzac5) + zzac5;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 41:
                    int zzab5 = zzgz.zzab((List) unsafe2.getObject(t, j3));
                    if (zzab5 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzab5);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzab5) + zzab5;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 42:
                    int zzad2 = zzgz.zzad((List) unsafe2.getObject(t, j3));
                    if (zzad2 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzad2);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzad2) + zzad2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 43:
                    int zzz2 = zzgz.zzz((List) unsafe2.getObject(t, j3));
                    if (zzz2 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzz2);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzz2) + zzz2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 44:
                    int zzx2 = zzgz.zzx((List) unsafe2.getObject(t, j3));
                    if (zzx2 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzx2);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzx2) + zzx2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 45:
                    int zzab6 = zzgz.zzab((List) unsafe2.getObject(t, j3));
                    if (zzab6 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzab6);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzab6) + zzab6;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 46:
                    int zzac6 = zzgz.zzac((List) unsafe2.getObject(t, j3));
                    if (zzac6 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzac6);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzac6) + zzac6;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 47:
                    int zzaa2 = zzgz.zzaa((List) unsafe2.getObject(t, j3));
                    if (zzaa2 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzaa2);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzaa2) + zzaa2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 48:
                    int zzw2 = zzgz.zzw((List) unsafe2.getObject(t, j3));
                    if (zzw2 > 0) {
                        if (this.zzakr) {
                            unsafe2.putInt(t, (long) i2, zzw2);
                        }
                        i12 += zzee.zzbi(i15) + zzee.zzbk(zzw2) + zzw2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 49:
                    i12 += zzgz.zzd(i15, (List) unsafe2.getObject(t, j3), zzbx(i11));
                    j = 0;
                    break;
                case 50:
                    i12 += this.zzakz.zzb(i15, unsafe2.getObject(t, j3), zzby(i11));
                    j = 0;
                    break;
                case 51:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzb(i15, 0.0d);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 52:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzb(i15, 0.0f);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 53:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzd(i15, zzi(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 54:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zze(i15, zzi(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 55:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzg(i15, zzh(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 56:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzg(i15, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 57:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzj(i15, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 58:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzc(i15, true);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 59:
                    if (zza(t, i15, i11)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzdp) {
                            i12 += zzee.zzc(i15, (zzdp) object2);
                            j = 0;
                            break;
                        } else {
                            i12 += zzee.zzc(i15, (String) object2);
                            j = 0;
                            break;
                        }
                    } else {
                        j = 0;
                        break;
                    }
                case 60:
                    if (zza(t, i15, i11)) {
                        i12 += zzgz.zzc(i15, unsafe2.getObject(t, j3), zzbx(i11));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 61:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzc(i15, (zzdp) unsafe2.getObject(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 62:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzh(i15, zzh(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 63:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzl(i15, zzh(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 64:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzk(i15, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 65:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzh(i15, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 66:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzi(i15, zzh(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 67:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzf(i15, zzi(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 68:
                    if (zza(t, i15, i11)) {
                        i12 += zzee.zzc(i15, (zzgi) unsafe2.getObject(t, j3), zzbx(i11));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                default:
                    j = 0;
                    break;
            }
            i11 += 3;
            i4 = 1048575;
            i5 = 1;
        }
        int zza = i12 + zza((zzhp) this.zzakx, (Object) t);
        if (!this.zzako) {
            return zza;
        }
        zzeo<?> zzh = this.zzaky.zzh(t);
        int i18 = 0;
        for (int i19 = 0; i19 < zzh.zzaex.zzwh(); i19++) {
            Map.Entry<FieldDescriptorType, Object> zzcf = zzh.zzaex.zzcf(i19);
            i18 += zzeo.zzb((zzeq<?>) zzcf.getKey(), zzcf.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : zzh.zzaex.zzwi()) {
            i18 += zzeo.zzb((zzeq<?>) entry.getKey(), entry.getValue());
        }
        return zza + i18;
    }

    private static <UT, UB> int zza(zzhp<UT, UB> zzhp, T t) {
        return zzhp.zzt(zzhp.zzx(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzhv.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0513  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0553  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a2b  */
    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zza(T t, zzim zzim) throws IOException {
        Map.Entry<?, Object> entry;
        Iterator<Map.Entry<?, Object>> it;
        int length;
        Map.Entry<?, Object> entry2;
        int i;
        Map.Entry<?, Object> entry3;
        Iterator<Map.Entry<?, Object>> it2;
        int length2;
        if (zzim.zztk() == zzey.zzd.zzaip) {
            zza(this.zzakx, t, zzim);
            if (this.zzako) {
                zzeo<?> zzh = this.zzaky.zzh(t);
                if (!zzh.zzaex.isEmpty()) {
                    it2 = zzh.descendingIterator();
                    entry3 = it2.next();
                    for (length2 = this.zzakj.length - 3; length2 >= 0; length2 -= 3) {
                        int zzca = zzca(length2);
                        int i2 = this.zzakj[length2];
                        while (entry3 != null && this.zzaky.zza(entry3) > i2) {
                            this.zzaky.zza(zzim, entry3);
                            entry3 = it2.hasNext() ? it2.next() : null;
                        }
                        switch ((zzca & 267386880) >>> 20) {
                            case 0:
                                if (zza(t, length2)) {
                                    zzim.zza(i2, zzhv.zzo(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 1:
                                if (zza(t, length2)) {
                                    zzim.zza(i2, zzhv.zzn(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 2:
                                if (zza(t, length2)) {
                                    zzim.zzi(i2, zzhv.zzl(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                if (zza(t, length2)) {
                                    zzim.zza(i2, zzhv.zzl(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 4:
                                if (zza(t, length2)) {
                                    zzim.zzc(i2, zzhv.zzk(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 5:
                                if (zza(t, length2)) {
                                    zzim.zzc(i2, zzhv.zzl(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 6:
                                if (zza(t, length2)) {
                                    zzim.zzf(i2, zzhv.zzk(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 7:
                                if (zza(t, length2)) {
                                    zzim.zzb(i2, zzhv.zzm(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 8:
                                if (zza(t, length2)) {
                                    zza(i2, zzhv.zzp(t, (long) (zzca & 1048575)), zzim);
                                    break;
                                } else {
                                    break;
                                }
                            case 9:
                                if (zza(t, length2)) {
                                    zzim.zza(i2, zzhv.zzp(t, (long) (zzca & 1048575)), zzbx(length2));
                                    break;
                                } else {
                                    break;
                                }
                            case 10:
                                if (zza(t, length2)) {
                                    zzim.zza(i2, (zzdp) zzhv.zzp(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 11:
                                if (zza(t, length2)) {
                                    zzim.zzd(i2, zzhv.zzk(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 12:
                                if (zza(t, length2)) {
                                    zzim.zzn(i2, zzhv.zzk(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 13:
                                if (zza(t, length2)) {
                                    zzim.zzm(i2, zzhv.zzk(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 14:
                                if (zza(t, length2)) {
                                    zzim.zzj(i2, zzhv.zzl(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 15:
                                if (zza(t, length2)) {
                                    zzim.zze(i2, zzhv.zzk(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 16:
                                if (zza(t, length2)) {
                                    zzim.zzb(i2, zzhv.zzl(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 17:
                                if (zza(t, length2)) {
                                    zzim.zzb(i2, zzhv.zzp(t, (long) (zzca & 1048575)), zzbx(length2));
                                    break;
                                } else {
                                    break;
                                }
                            case 18:
                                zzgz.zza(this.zzakj[length2], (List<Double>) ((List) zzhv.zzp(t, (long) (zzca & 1048575))), zzim, false);
                                break;
                            case 19:
                                zzgz.zzb(this.zzakj[length2], (List<Float>) ((List) zzhv.zzp(t, (long) (zzca & 1048575))), zzim, false);
                                break;
                            case 20:
                                zzgz.zzc(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 21:
                                zzgz.zzd(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 22:
                                zzgz.zzh(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 23:
                                zzgz.zzf(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 24:
                                zzgz.zzk(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 25:
                                zzgz.zzn(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 26:
                                zzgz.zza(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim);
                                break;
                            case 27:
                                zzgz.zza(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, zzbx(length2));
                                break;
                            case 28:
                                zzgz.zzb(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim);
                                break;
                            case 29:
                                zzgz.zzi(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 30:
                                zzgz.zzm(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 31:
                                zzgz.zzl(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 32:
                                zzgz.zzg(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 33:
                                zzgz.zzj(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 34:
                                zzgz.zze(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, false);
                                break;
                            case 35:
                                zzgz.zza(this.zzakj[length2], (List<Double>) ((List) zzhv.zzp(t, (long) (zzca & 1048575))), zzim, true);
                                break;
                            case 36:
                                zzgz.zzb(this.zzakj[length2], (List<Float>) ((List) zzhv.zzp(t, (long) (zzca & 1048575))), zzim, true);
                                break;
                            case 37:
                                zzgz.zzc(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 38:
                                zzgz.zzd(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 39:
                                zzgz.zzh(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 40:
                                zzgz.zzf(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 41:
                                zzgz.zzk(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 42:
                                zzgz.zzn(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 43:
                                zzgz.zzi(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 44:
                                zzgz.zzm(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 45:
                                zzgz.zzl(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 46:
                                zzgz.zzg(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 47:
                                zzgz.zzj(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 48:
                                zzgz.zze(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, true);
                                break;
                            case 49:
                                zzgz.zzb(this.zzakj[length2], (List) zzhv.zzp(t, (long) (zzca & 1048575)), zzim, zzbx(length2));
                                break;
                            case 50:
                                zza(zzim, i2, zzhv.zzp(t, (long) (zzca & 1048575)), length2);
                                break;
                            case 51:
                                if (zza(t, i2, length2)) {
                                    zzim.zza(i2, zzf(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 52:
                                if (zza(t, i2, length2)) {
                                    zzim.zza(i2, zzg(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                if (zza(t, i2, length2)) {
                                    zzim.zzi(i2, zzi(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                if (zza(t, i2, length2)) {
                                    zzim.zza(i2, zzi(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 55:
                                if (zza(t, i2, length2)) {
                                    zzim.zzc(i2, zzh(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 56:
                                if (zza(t, i2, length2)) {
                                    zzim.zzc(i2, zzi(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 57:
                                if (zza(t, i2, length2)) {
                                    zzim.zzf(i2, zzh(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 58:
                                if (zza(t, i2, length2)) {
                                    zzim.zzb(i2, zzj(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 59:
                                if (zza(t, i2, length2)) {
                                    zza(i2, zzhv.zzp(t, (long) (zzca & 1048575)), zzim);
                                    break;
                                } else {
                                    break;
                                }
                            case 60:
                                if (zza(t, i2, length2)) {
                                    zzim.zza(i2, zzhv.zzp(t, (long) (zzca & 1048575)), zzbx(length2));
                                    break;
                                } else {
                                    break;
                                }
                            case 61:
                                if (zza(t, i2, length2)) {
                                    zzim.zza(i2, (zzdp) zzhv.zzp(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 62:
                                if (zza(t, i2, length2)) {
                                    zzim.zzd(i2, zzh(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 63:
                                if (zza(t, i2, length2)) {
                                    zzim.zzn(i2, zzh(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 64:
                                if (zza(t, i2, length2)) {
                                    zzim.zzm(i2, zzh(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 65:
                                if (zza(t, i2, length2)) {
                                    zzim.zzj(i2, zzi(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 66:
                                if (zza(t, i2, length2)) {
                                    zzim.zze(i2, zzh(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 67:
                                if (zza(t, i2, length2)) {
                                    zzim.zzb(i2, zzi(t, (long) (zzca & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 68:
                                if (zza(t, i2, length2)) {
                                    zzim.zzb(i2, zzhv.zzp(t, (long) (zzca & 1048575)), zzbx(length2));
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                    while (entry3 != null) {
                        this.zzaky.zza(zzim, entry3);
                        entry3 = it2.hasNext() ? it2.next() : null;
                    }
                }
            }
            it2 = null;
            entry3 = null;
            while (length2 >= 0) {
            }
            while (entry3 != null) {
            }
        } else if (this.zzakq) {
            if (this.zzako) {
                zzeo<?> zzh2 = this.zzaky.zzh(t);
                if (!zzh2.zzaex.isEmpty()) {
                    it = zzh2.iterator();
                    entry = it.next();
                    length = this.zzakj.length;
                    entry2 = entry;
                    for (i = 0; i < length; i += 3) {
                        int zzca2 = zzca(i);
                        int i3 = this.zzakj[i];
                        while (entry2 != null && this.zzaky.zza(entry2) <= i3) {
                            this.zzaky.zza(zzim, entry2);
                            entry2 = it.hasNext() ? it.next() : null;
                        }
                        switch ((zzca2 & 267386880) >>> 20) {
                            case 0:
                                if (zza(t, i)) {
                                    zzim.zza(i3, zzhv.zzo(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 1:
                                if (zza(t, i)) {
                                    zzim.zza(i3, zzhv.zzn(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 2:
                                if (zza(t, i)) {
                                    zzim.zzi(i3, zzhv.zzl(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                if (zza(t, i)) {
                                    zzim.zza(i3, zzhv.zzl(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 4:
                                if (zza(t, i)) {
                                    zzim.zzc(i3, zzhv.zzk(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 5:
                                if (zza(t, i)) {
                                    zzim.zzc(i3, zzhv.zzl(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 6:
                                if (zza(t, i)) {
                                    zzim.zzf(i3, zzhv.zzk(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 7:
                                if (zza(t, i)) {
                                    zzim.zzb(i3, zzhv.zzm(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 8:
                                if (zza(t, i)) {
                                    zza(i3, zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim);
                                    break;
                                } else {
                                    break;
                                }
                            case 9:
                                if (zza(t, i)) {
                                    zzim.zza(i3, zzhv.zzp(t, (long) (zzca2 & 1048575)), zzbx(i));
                                    break;
                                } else {
                                    break;
                                }
                            case 10:
                                if (zza(t, i)) {
                                    zzim.zza(i3, (zzdp) zzhv.zzp(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 11:
                                if (zza(t, i)) {
                                    zzim.zzd(i3, zzhv.zzk(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 12:
                                if (zza(t, i)) {
                                    zzim.zzn(i3, zzhv.zzk(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 13:
                                if (zza(t, i)) {
                                    zzim.zzm(i3, zzhv.zzk(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 14:
                                if (zza(t, i)) {
                                    zzim.zzj(i3, zzhv.zzl(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 15:
                                if (zza(t, i)) {
                                    zzim.zze(i3, zzhv.zzk(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 16:
                                if (zza(t, i)) {
                                    zzim.zzb(i3, zzhv.zzl(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 17:
                                if (zza(t, i)) {
                                    zzim.zzb(i3, zzhv.zzp(t, (long) (zzca2 & 1048575)), zzbx(i));
                                    break;
                                } else {
                                    break;
                                }
                            case 18:
                                zzgz.zza(this.zzakj[i], (List<Double>) ((List) zzhv.zzp(t, (long) (zzca2 & 1048575))), zzim, false);
                                break;
                            case 19:
                                zzgz.zzb(this.zzakj[i], (List<Float>) ((List) zzhv.zzp(t, (long) (zzca2 & 1048575))), zzim, false);
                                break;
                            case 20:
                                zzgz.zzc(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 21:
                                zzgz.zzd(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 22:
                                zzgz.zzh(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 23:
                                zzgz.zzf(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 24:
                                zzgz.zzk(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 25:
                                zzgz.zzn(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 26:
                                zzgz.zza(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim);
                                break;
                            case 27:
                                zzgz.zza(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, zzbx(i));
                                break;
                            case 28:
                                zzgz.zzb(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim);
                                break;
                            case 29:
                                zzgz.zzi(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 30:
                                zzgz.zzm(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 31:
                                zzgz.zzl(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 32:
                                zzgz.zzg(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 33:
                                zzgz.zzj(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 34:
                                zzgz.zze(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, false);
                                break;
                            case 35:
                                zzgz.zza(this.zzakj[i], (List<Double>) ((List) zzhv.zzp(t, (long) (zzca2 & 1048575))), zzim, true);
                                break;
                            case 36:
                                zzgz.zzb(this.zzakj[i], (List<Float>) ((List) zzhv.zzp(t, (long) (zzca2 & 1048575))), zzim, true);
                                break;
                            case 37:
                                zzgz.zzc(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 38:
                                zzgz.zzd(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 39:
                                zzgz.zzh(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 40:
                                zzgz.zzf(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 41:
                                zzgz.zzk(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 42:
                                zzgz.zzn(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 43:
                                zzgz.zzi(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 44:
                                zzgz.zzm(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 45:
                                zzgz.zzl(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 46:
                                zzgz.zzg(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 47:
                                zzgz.zzj(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 48:
                                zzgz.zze(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, true);
                                break;
                            case 49:
                                zzgz.zzb(this.zzakj[i], (List) zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim, zzbx(i));
                                break;
                            case 50:
                                zza(zzim, i3, zzhv.zzp(t, (long) (zzca2 & 1048575)), i);
                                break;
                            case 51:
                                if (zza(t, i3, i)) {
                                    zzim.zza(i3, zzf(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 52:
                                if (zza(t, i3, i)) {
                                    zzim.zza(i3, zzg(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                if (zza(t, i3, i)) {
                                    zzim.zzi(i3, zzi(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                if (zza(t, i3, i)) {
                                    zzim.zza(i3, zzi(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 55:
                                if (zza(t, i3, i)) {
                                    zzim.zzc(i3, zzh(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 56:
                                if (zza(t, i3, i)) {
                                    zzim.zzc(i3, zzi(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 57:
                                if (zza(t, i3, i)) {
                                    zzim.zzf(i3, zzh(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 58:
                                if (zza(t, i3, i)) {
                                    zzim.zzb(i3, zzj(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 59:
                                if (zza(t, i3, i)) {
                                    zza(i3, zzhv.zzp(t, (long) (zzca2 & 1048575)), zzim);
                                    break;
                                } else {
                                    break;
                                }
                            case 60:
                                if (zza(t, i3, i)) {
                                    zzim.zza(i3, zzhv.zzp(t, (long) (zzca2 & 1048575)), zzbx(i));
                                    break;
                                } else {
                                    break;
                                }
                            case 61:
                                if (zza(t, i3, i)) {
                                    zzim.zza(i3, (zzdp) zzhv.zzp(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 62:
                                if (zza(t, i3, i)) {
                                    zzim.zzd(i3, zzh(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 63:
                                if (zza(t, i3, i)) {
                                    zzim.zzn(i3, zzh(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 64:
                                if (zza(t, i3, i)) {
                                    zzim.zzm(i3, zzh(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 65:
                                if (zza(t, i3, i)) {
                                    zzim.zzj(i3, zzi(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 66:
                                if (zza(t, i3, i)) {
                                    zzim.zze(i3, zzh(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 67:
                                if (zza(t, i3, i)) {
                                    zzim.zzb(i3, zzi(t, (long) (zzca2 & 1048575)));
                                    break;
                                } else {
                                    break;
                                }
                            case 68:
                                if (zza(t, i3, i)) {
                                    zzim.zzb(i3, zzhv.zzp(t, (long) (zzca2 & 1048575)), zzbx(i));
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                    while (entry2 != null) {
                        this.zzaky.zza(zzim, entry2);
                        entry2 = it.hasNext() ? it.next() : null;
                    }
                    zza(this.zzakx, t, zzim);
                }
            }
            it = null;
            entry = null;
            length = this.zzakj.length;
            entry2 = entry;
            while (i < length) {
            }
            while (entry2 != null) {
            }
            zza(this.zzakx, t, zzim);
        } else {
            zzb(t, zzim);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:190:0x051e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    private final void zzb(T t, zzim zzim) throws IOException {
        Map.Entry<?, Object> entry;
        Iterator<Map.Entry<?, Object>> it;
        int length;
        int i;
        Map.Entry<?, Object> entry2;
        int i2;
        Map.Entry<?, ?> entry3;
        if (this.zzako) {
            zzeo<?> zzh = this.zzaky.zzh(t);
            if (!zzh.zzaex.isEmpty()) {
                it = zzh.iterator();
                entry = it.next();
                int i3 = -1;
                length = this.zzakj.length;
                Unsafe unsafe = zzaki;
                Map.Entry<?, ?> entry4 = entry;
                int i4 = 0;
                for (i = 0; i < length; i += 3) {
                    int zzca = zzca(i);
                    int[] iArr = this.zzakj;
                    int i5 = iArr[i];
                    int i6 = (267386880 & zzca) >>> 20;
                    if (this.zzakq || i6 > 17) {
                        entry4 = entry4;
                        i2 = 0;
                    } else {
                        int i7 = iArr[i + 2];
                        int i8 = i7 & 1048575;
                        if (i8 != i3) {
                            entry3 = entry4;
                            i4 = unsafe.getInt(t, (long) i8);
                        } else {
                            entry3 = entry4;
                            i8 = i3;
                        }
                        i2 = 1 << (i7 >>> 20);
                        i3 = i8;
                        entry4 = entry3;
                    }
                    while (entry4 != null && this.zzaky.zza(entry4) <= i5) {
                        this.zzaky.zza(zzim, entry4);
                        entry4 = it.hasNext() ? it.next() : null;
                    }
                    long j = (long) (zzca & 1048575);
                    switch (i6) {
                        case 0:
                            if ((i4 & i2) != 0) {
                                zzim.zza(i5, zzhv.zzo(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if ((i4 & i2) != 0) {
                                zzim.zza(i5, zzhv.zzn(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if ((i4 & i2) != 0) {
                                zzim.zzi(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if ((i4 & i2) != 0) {
                                zzim.zza(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if ((i4 & i2) != 0) {
                                zzim.zzc(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if ((i4 & i2) != 0) {
                                zzim.zzc(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if ((i4 & i2) != 0) {
                                zzim.zzf(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if ((i4 & i2) != 0) {
                                zzim.zzb(i5, zzhv.zzm(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if ((i4 & i2) != 0) {
                                zza(i5, unsafe.getObject(t, j), zzim);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if ((i4 & i2) != 0) {
                                zzim.zza(i5, unsafe.getObject(t, j), zzbx(i));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if ((i4 & i2) != 0) {
                                zzim.zza(i5, (zzdp) unsafe.getObject(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if ((i4 & i2) != 0) {
                                zzim.zzd(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if ((i4 & i2) != 0) {
                                zzim.zzn(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if ((i4 & i2) != 0) {
                                zzim.zzm(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if ((i4 & i2) != 0) {
                                zzim.zzj(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if ((i4 & i2) != 0) {
                                zzim.zze(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if ((i4 & i2) != 0) {
                                zzim.zzb(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if ((i4 & i2) != 0) {
                                zzim.zzb(i5, unsafe.getObject(t, j), zzbx(i));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            zzgz.zza(this.zzakj[i], (List<Double>) ((List) unsafe.getObject(t, j)), zzim, false);
                            break;
                        case 19:
                            zzgz.zzb(this.zzakj[i], (List<Float>) ((List) unsafe.getObject(t, j)), zzim, false);
                            break;
                        case 20:
                            zzgz.zzc(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 21:
                            zzgz.zzd(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 22:
                            zzgz.zzh(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 23:
                            zzgz.zzf(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 24:
                            zzgz.zzk(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 25:
                            zzgz.zzn(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 26:
                            zzgz.zza(this.zzakj[i], (List) unsafe.getObject(t, j), zzim);
                            break;
                        case 27:
                            zzgz.zza(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, zzbx(i));
                            break;
                        case 28:
                            zzgz.zzb(this.zzakj[i], (List) unsafe.getObject(t, j), zzim);
                            break;
                        case 29:
                            zzgz.zzi(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 30:
                            zzgz.zzm(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 31:
                            zzgz.zzl(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 32:
                            zzgz.zzg(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 33:
                            zzgz.zzj(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 34:
                            zzgz.zze(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, false);
                            break;
                        case 35:
                            zzgz.zza(this.zzakj[i], (List<Double>) ((List) unsafe.getObject(t, j)), zzim, true);
                            break;
                        case 36:
                            zzgz.zzb(this.zzakj[i], (List<Float>) ((List) unsafe.getObject(t, j)), zzim, true);
                            break;
                        case 37:
                            zzgz.zzc(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 38:
                            zzgz.zzd(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 39:
                            zzgz.zzh(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 40:
                            zzgz.zzf(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 41:
                            zzgz.zzk(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 42:
                            zzgz.zzn(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 43:
                            zzgz.zzi(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 44:
                            zzgz.zzm(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 45:
                            zzgz.zzl(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 46:
                            zzgz.zzg(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 47:
                            zzgz.zzj(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 48:
                            zzgz.zze(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, true);
                            break;
                        case 49:
                            zzgz.zzb(this.zzakj[i], (List) unsafe.getObject(t, j), zzim, zzbx(i));
                            break;
                        case 50:
                            zza(zzim, i5, unsafe.getObject(t, j), i);
                            break;
                        case 51:
                            if (zza(t, i5, i)) {
                                zzim.zza(i5, zzf(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (zza(t, i5, i)) {
                                zzim.zza(i5, zzg(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (zza(t, i5, i)) {
                                zzim.zzi(i5, zzi(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (zza(t, i5, i)) {
                                zzim.zza(i5, zzi(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (zza(t, i5, i)) {
                                zzim.zzc(i5, zzh(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (zza(t, i5, i)) {
                                zzim.zzc(i5, zzi(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (zza(t, i5, i)) {
                                zzim.zzf(i5, zzh(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (zza(t, i5, i)) {
                                zzim.zzb(i5, zzj(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (zza(t, i5, i)) {
                                zza(i5, unsafe.getObject(t, j), zzim);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (zza(t, i5, i)) {
                                zzim.zza(i5, unsafe.getObject(t, j), zzbx(i));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (zza(t, i5, i)) {
                                zzim.zza(i5, (zzdp) unsafe.getObject(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (zza(t, i5, i)) {
                                zzim.zzd(i5, zzh(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (zza(t, i5, i)) {
                                zzim.zzn(i5, zzh(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (zza(t, i5, i)) {
                                zzim.zzm(i5, zzh(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (zza(t, i5, i)) {
                                zzim.zzj(i5, zzi(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (zza(t, i5, i)) {
                                zzim.zze(i5, zzh(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (zza(t, i5, i)) {
                                zzim.zzb(i5, zzi(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (zza(t, i5, i)) {
                                zzim.zzb(i5, unsafe.getObject(t, j), zzbx(i));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                entry2 = entry4;
                while (entry2 != null) {
                    this.zzaky.zza(zzim, entry2);
                    entry2 = it.hasNext() ? it.next() : null;
                }
                zza(this.zzakx, t, zzim);
            }
        }
        it = null;
        entry = null;
        int i32 = -1;
        length = this.zzakj.length;
        Unsafe unsafe2 = zzaki;
        Map.Entry<?, ?> entry42 = entry;
        int i42 = 0;
        while (i < length) {
        }
        entry2 = entry42;
        while (entry2 != null) {
        }
        zza(this.zzakx, t, zzim);
    }

    private final <K, V> void zza(zzim zzim, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzim.zza(i, this.zzakz.zzr(zzby(i2)), this.zzakz.zzn(obj));
        }
    }

    private static <UT, UB> void zza(zzhp<UT, UB> zzhp, T t, zzim zzim) throws IOException {
        zzhp.zza(zzhp.zzx(t), zzim);
    }

    /*  JADX ERROR: StackOverflowError in pass: MarkFinallyVisitor
        java.lang.StackOverflowError
        	at jadx.core.dex.nodes.InsnNode.isSame(InsnNode.java:303)
        	at jadx.core.dex.instructions.IfNode.isSame(IfNode.java:122)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.sameInsns(MarkFinallyVisitor.java:451)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.compareBlocks(MarkFinallyVisitor.java:436)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:408)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:411)
        */
    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zza(T r13, com.google.android.gms.internal.measurement.zzgy r14, com.google.android.gms.internal.measurement.zzel r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1652
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzgy, com.google.android.gms.internal.measurement.zzel):void");
    }

    private static zzhs zzu(Object obj) {
        zzey zzey = (zzey) obj;
        zzhs zzhs = zzey.zzahz;
        if (zzhs != zzhs.zzwq()) {
            return zzhs;
        }
        zzhs zzwr = zzhs.zzwr();
        zzey.zzahz = zzwr;
        return zzwr;
    }

    private static int zza(byte[] bArr, int i, int i2, zzig zzig, Class<?> cls, zzdk zzdk) throws IOException {
        switch (zzgl.zzaee[zzig.ordinal()]) {
            case 1:
                int zzb = zzdl.zzb(bArr, i, zzdk);
                zzdk.zzadc = Boolean.valueOf(zzdk.zzadb != 0);
                return zzb;
            case 2:
                return zzdl.zze(bArr, i, zzdk);
            case 3:
                zzdk.zzadc = Double.valueOf(zzdl.zzc(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzdk.zzadc = Integer.valueOf(zzdl.zza(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzdk.zzadc = Long.valueOf(zzdl.zzb(bArr, i));
                return i + 8;
            case 8:
                zzdk.zzadc = Float.valueOf(zzdl.zzd(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zza = zzdl.zza(bArr, i, zzdk);
                zzdk.zzadc = Integer.valueOf(zzdk.zzada);
                return zza;
            case 12:
            case 13:
                int zzb2 = zzdl.zzb(bArr, i, zzdk);
                zzdk.zzadc = Long.valueOf(zzdk.zzadb);
                return zzb2;
            case 14:
                return zzdl.zza(zzgt.zzvy().zzf(cls), bArr, i, i2, zzdk);
            case 15:
                int zza2 = zzdl.zza(bArr, i, zzdk);
                zzdk.zzadc = Integer.valueOf(zzeb.zzaz(zzdk.zzada));
                return zza2;
            case 16:
                int zzb3 = zzdl.zzb(bArr, i, zzdk);
                zzdk.zzadc = Long.valueOf(zzeb.zzbm(zzdk.zzadb));
                return zzb3;
            case 17:
                return zzdl.zzd(bArr, i, zzdk);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzdk zzdk) throws IOException {
        int i8;
        zzff zzff = (zzff) zzaki.getObject(t, j2);
        if (!zzff.zzrx()) {
            int size = zzff.size();
            zzff = zzff.zzap(size == 0 ? 10 : size << 1);
            zzaki.putObject(t, j2, zzff);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzeh zzeh = (zzeh) zzff;
                    int zza = zzdl.zza(bArr, i, zzdk);
                    int i9 = zzdk.zzada + zza;
                    while (zza < i9) {
                        zzeh.zzf(zzdl.zzc(bArr, zza));
                        zza += 8;
                    }
                    if (zza == i9) {
                        return zza;
                    }
                    throw zzfi.zzut();
                } else if (i5 == 1) {
                    zzeh zzeh2 = (zzeh) zzff;
                    zzeh2.zzf(zzdl.zzc(bArr, i));
                    int i10 = i + 8;
                    while (i10 < i2) {
                        int zza2 = zzdl.zza(bArr, i10, zzdk);
                        if (i3 != zzdk.zzada) {
                            return i10;
                        }
                        zzeh2.zzf(zzdl.zzc(bArr, zza2));
                        i10 = zza2 + 8;
                    }
                    return i10;
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzeu zzeu = (zzeu) zzff;
                    int zza3 = zzdl.zza(bArr, i, zzdk);
                    int i11 = zzdk.zzada + zza3;
                    while (zza3 < i11) {
                        zzeu.zzc(zzdl.zzd(bArr, zza3));
                        zza3 += 4;
                    }
                    if (zza3 == i11) {
                        return zza3;
                    }
                    throw zzfi.zzut();
                } else if (i5 == 5) {
                    zzeu zzeu2 = (zzeu) zzff;
                    zzeu2.zzc(zzdl.zzd(bArr, i));
                    int i12 = i + 4;
                    while (i12 < i2) {
                        int zza4 = zzdl.zza(bArr, i12, zzdk);
                        if (i3 != zzdk.zzada) {
                            return i12;
                        }
                        zzeu2.zzc(zzdl.zzd(bArr, zza4));
                        i12 = zza4 + 4;
                    }
                    return i12;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzfw zzfw = (zzfw) zzff;
                    int zza5 = zzdl.zza(bArr, i, zzdk);
                    int i13 = zzdk.zzada + zza5;
                    while (zza5 < i13) {
                        zza5 = zzdl.zzb(bArr, zza5, zzdk);
                        zzfw.zzby(zzdk.zzadb);
                    }
                    if (zza5 == i13) {
                        return zza5;
                    }
                    throw zzfi.zzut();
                } else if (i5 == 0) {
                    zzfw zzfw2 = (zzfw) zzff;
                    int zzb = zzdl.zzb(bArr, i, zzdk);
                    zzfw2.zzby(zzdk.zzadb);
                    while (zzb < i2) {
                        int zza6 = zzdl.zza(bArr, zzb, zzdk);
                        if (i3 != zzdk.zzada) {
                            return zzb;
                        }
                        zzb = zzdl.zzb(bArr, zza6, zzdk);
                        zzfw2.zzby(zzdk.zzadb);
                    }
                    return zzb;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzdl.zza(bArr, i, zzff, zzdk);
                }
                if (i5 == 0) {
                    return zzdl.zza(i3, bArr, i, i2, zzff, zzdk);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzfw zzfw3 = (zzfw) zzff;
                    int zza7 = zzdl.zza(bArr, i, zzdk);
                    int i14 = zzdk.zzada + zza7;
                    while (zza7 < i14) {
                        zzfw3.zzby(zzdl.zzb(bArr, zza7));
                        zza7 += 8;
                    }
                    if (zza7 == i14) {
                        return zza7;
                    }
                    throw zzfi.zzut();
                } else if (i5 == 1) {
                    zzfw zzfw4 = (zzfw) zzff;
                    zzfw4.zzby(zzdl.zzb(bArr, i));
                    int i15 = i + 8;
                    while (i15 < i2) {
                        int zza8 = zzdl.zza(bArr, i15, zzdk);
                        if (i3 != zzdk.zzada) {
                            return i15;
                        }
                        zzfw4.zzby(zzdl.zzb(bArr, zza8));
                        i15 = zza8 + 8;
                    }
                    return i15;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzfa zzfa = (zzfa) zzff;
                    int zza9 = zzdl.zza(bArr, i, zzdk);
                    int i16 = zzdk.zzada + zza9;
                    while (zza9 < i16) {
                        zzfa.zzbu(zzdl.zza(bArr, zza9));
                        zza9 += 4;
                    }
                    if (zza9 == i16) {
                        return zza9;
                    }
                    throw zzfi.zzut();
                } else if (i5 == 5) {
                    zzfa zzfa2 = (zzfa) zzff;
                    zzfa2.zzbu(zzdl.zza(bArr, i));
                    int i17 = i + 4;
                    while (i17 < i2) {
                        int zza10 = zzdl.zza(bArr, i17, zzdk);
                        if (i3 != zzdk.zzada) {
                            return i17;
                        }
                        zzfa2.zzbu(zzdl.zza(bArr, zza10));
                        i17 = zza10 + 4;
                    }
                    return i17;
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzdn zzdn = (zzdn) zzff;
                    int zza11 = zzdl.zza(bArr, i, zzdk);
                    int i18 = zzdk.zzada + zza11;
                    while (zza11 < i18) {
                        zza11 = zzdl.zzb(bArr, zza11, zzdk);
                        zzdn.addBoolean(zzdk.zzadb != 0);
                    }
                    if (zza11 == i18) {
                        return zza11;
                    }
                    throw zzfi.zzut();
                } else if (i5 == 0) {
                    zzdn zzdn2 = (zzdn) zzff;
                    int zzb2 = zzdl.zzb(bArr, i, zzdk);
                    zzdn2.addBoolean(zzdk.zzadb != 0);
                    while (zzb2 < i2) {
                        int zza12 = zzdl.zza(bArr, zzb2, zzdk);
                        if (i3 != zzdk.zzada) {
                            return zzb2;
                        }
                        zzb2 = zzdl.zzb(bArr, zza12, zzdk);
                        zzdn2.addBoolean(zzdk.zzadb != 0);
                    }
                    return zzb2;
                }
                break;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int zza13 = zzdl.zza(bArr, i, zzdk);
                        int i19 = zzdk.zzada;
                        if (i19 >= 0) {
                            if (i19 == 0) {
                                zzff.add("");
                            } else {
                                zzff.add(new String(bArr, zza13, i19, zzez.UTF_8));
                                zza13 += i19;
                            }
                            while (zza13 < i2) {
                                int zza14 = zzdl.zza(bArr, zza13, zzdk);
                                if (i3 != zzdk.zzada) {
                                    return zza13;
                                }
                                zza13 = zzdl.zza(bArr, zza14, zzdk);
                                int i20 = zzdk.zzada;
                                if (i20 < 0) {
                                    throw zzfi.zzuu();
                                } else if (i20 == 0) {
                                    zzff.add("");
                                } else {
                                    zzff.add(new String(bArr, zza13, i20, zzez.UTF_8));
                                    zza13 += i20;
                                }
                            }
                            return zza13;
                        }
                        throw zzfi.zzuu();
                    }
                    int zza15 = zzdl.zza(bArr, i, zzdk);
                    int i21 = zzdk.zzada;
                    if (i21 >= 0) {
                        if (i21 == 0) {
                            zzff.add("");
                        } else {
                            int i22 = zza15 + i21;
                            if (zzhy.zzf(bArr, zza15, i22)) {
                                zzff.add(new String(bArr, zza15, i21, zzez.UTF_8));
                                zza15 = i22;
                            } else {
                                throw zzfi.zzvb();
                            }
                        }
                        while (zza15 < i2) {
                            int zza16 = zzdl.zza(bArr, zza15, zzdk);
                            if (i3 != zzdk.zzada) {
                                return zza15;
                            }
                            zza15 = zzdl.zza(bArr, zza16, zzdk);
                            int i23 = zzdk.zzada;
                            if (i23 < 0) {
                                throw zzfi.zzuu();
                            } else if (i23 == 0) {
                                zzff.add("");
                            } else {
                                int i24 = zza15 + i23;
                                if (zzhy.zzf(bArr, zza15, i24)) {
                                    zzff.add(new String(bArr, zza15, i23, zzez.UTF_8));
                                    zza15 = i24;
                                } else {
                                    throw zzfi.zzvb();
                                }
                            }
                        }
                        return zza15;
                    }
                    throw zzfi.zzuu();
                }
                break;
            case 27:
                if (i5 == 2) {
                    return zzdl.zza(zzbx(i6), i3, bArr, i, i2, zzff, zzdk);
                }
                break;
            case 28:
                if (i5 == 2) {
                    int zza17 = zzdl.zza(bArr, i, zzdk);
                    int i25 = zzdk.zzada;
                    if (i25 < 0) {
                        throw zzfi.zzuu();
                    } else if (i25 <= bArr.length - zza17) {
                        if (i25 == 0) {
                            zzff.add(zzdp.zzadh);
                        } else {
                            zzff.add(zzdp.zzb(bArr, zza17, i25));
                            zza17 += i25;
                        }
                        while (zza17 < i2) {
                            int zza18 = zzdl.zza(bArr, zza17, zzdk);
                            if (i3 != zzdk.zzada) {
                                return zza17;
                            }
                            zza17 = zzdl.zza(bArr, zza18, zzdk);
                            int i26 = zzdk.zzada;
                            if (i26 < 0) {
                                throw zzfi.zzuu();
                            } else if (i26 > bArr.length - zza17) {
                                throw zzfi.zzut();
                            } else if (i26 == 0) {
                                zzff.add(zzdp.zzadh);
                            } else {
                                zzff.add(zzdp.zzb(bArr, zza17, i26));
                                zza17 += i26;
                            }
                        }
                        return zza17;
                    } else {
                        throw zzfi.zzut();
                    }
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    i8 = zzdl.zza(bArr, i, zzff, zzdk);
                } else if (i5 == 0) {
                    i8 = zzdl.zza(i3, bArr, i, i2, zzff, zzdk);
                }
                T t2 = t;
                zzhs zzhs = t2.zzahz;
                if (zzhs == zzhs.zzwq()) {
                    zzhs = null;
                }
                zzhs zzhs2 = (zzhs) zzgz.zza(i4, zzff, zzbz(i6), zzhs, this.zzakx);
                if (zzhs2 != null) {
                    t2.zzahz = zzhs2;
                }
                return i8;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzfa zzfa3 = (zzfa) zzff;
                    int zza19 = zzdl.zza(bArr, i, zzdk);
                    int i27 = zzdk.zzada + zza19;
                    while (zza19 < i27) {
                        zza19 = zzdl.zza(bArr, zza19, zzdk);
                        zzfa3.zzbu(zzeb.zzaz(zzdk.zzada));
                    }
                    if (zza19 == i27) {
                        return zza19;
                    }
                    throw zzfi.zzut();
                } else if (i5 == 0) {
                    zzfa zzfa4 = (zzfa) zzff;
                    int zza20 = zzdl.zza(bArr, i, zzdk);
                    zzfa4.zzbu(zzeb.zzaz(zzdk.zzada));
                    while (zza20 < i2) {
                        int zza21 = zzdl.zza(bArr, zza20, zzdk);
                        if (i3 != zzdk.zzada) {
                            return zza20;
                        }
                        zza20 = zzdl.zza(bArr, zza21, zzdk);
                        zzfa4.zzbu(zzeb.zzaz(zzdk.zzada));
                    }
                    return zza20;
                }
                break;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzfw zzfw5 = (zzfw) zzff;
                    int zza22 = zzdl.zza(bArr, i, zzdk);
                    int i28 = zzdk.zzada + zza22;
                    while (zza22 < i28) {
                        zza22 = zzdl.zzb(bArr, zza22, zzdk);
                        zzfw5.zzby(zzeb.zzbm(zzdk.zzadb));
                    }
                    if (zza22 == i28) {
                        return zza22;
                    }
                    throw zzfi.zzut();
                } else if (i5 == 0) {
                    zzfw zzfw6 = (zzfw) zzff;
                    int zzb3 = zzdl.zzb(bArr, i, zzdk);
                    zzfw6.zzby(zzeb.zzbm(zzdk.zzadb));
                    while (zzb3 < i2) {
                        int zza23 = zzdl.zza(bArr, zzb3, zzdk);
                        if (i3 != zzdk.zzada) {
                            return zzb3;
                        }
                        zzb3 = zzdl.zzb(bArr, zza23, zzdk);
                        zzfw6.zzby(zzeb.zzbm(zzdk.zzadb));
                    }
                    return zzb3;
                }
                break;
            case 49:
                if (i5 == 3) {
                    zzgx zzbx = zzbx(i6);
                    int i29 = (i3 & -8) | 4;
                    int zza24 = zzdl.zza(zzbx, bArr, i, i2, i29, zzdk);
                    zzff.add(zzdk.zzadc);
                    while (zza24 < i2) {
                        int zza25 = zzdl.zza(bArr, zza24, zzdk);
                        if (i3 != zzdk.zzada) {
                            return zza24;
                        }
                        zza24 = zzdl.zza(zzbx, bArr, zza25, i2, i29, zzdk);
                        zzff.add(zzdk.zzadc);
                    }
                    return zza24;
                }
                break;
        }
        return i;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:29:0x003e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:33:0x003e */
    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zzdk zzdk) throws IOException {
        int i4;
        int i5;
        Unsafe unsafe = zzaki;
        Object zzby = zzby(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzakz.zzo(object)) {
            Object zzq = this.zzakz.zzq(zzby);
            this.zzakz.zzb(zzq, object);
            unsafe.putObject(t, j, zzq);
            object = zzq;
        }
        zzfz<?, ?> zzr = this.zzakz.zzr(zzby);
        Map<?, ?> zzm = this.zzakz.zzm(object);
        int zza = zzdl.zza(bArr, i, zzdk);
        int i6 = zzdk.zzada;
        if (i6 < 0 || i6 > i2 - zza) {
            throw zzfi.zzut();
        }
        int i7 = i6 + zza;
        UnitModelLoader unitModelLoader = (K) zzr.zzakc;
        UnitModelLoader unitModelLoader2 = (V) zzr.zzaba;
        while (zza < i7) {
            int i8 = zza + 1;
            byte b = bArr[zza];
            if (b < 0) {
                i5 = zzdl.zza(b, bArr, i8, zzdk);
                i4 = zzdk.zzada;
            } else {
                i5 = i8;
                i4 = b;
            }
            int i9 = (i4 == 1 ? 1 : 0) >>> 3;
            int i10 = (i4 == 1 ? 1 : 0) & 7;
            switch (i9) {
                case 1:
                    if (i10 == zzr.zzakb.zzxa()) {
                        zza = zza(bArr, i5, i2, zzr.zzakb, (Class<?>) null, zzdk);
                        unitModelLoader = (K) zzdk.zzadc;
                        continue;
                    }
                    break;
                case 2:
                    if (i10 == zzr.zzakd.zzxa()) {
                        zza = zza(bArr, i5, i2, zzr.zzakd, zzr.zzaba.getClass(), zzdk);
                        unitModelLoader2 = (V) zzdk.zzadc;
                        continue;
                    }
                    break;
            }
            zza = zzdl.zza(i4, bArr, i5, i2, zzdk);
        }
        if (zza == i7) {
            zzm.put(unitModelLoader, unitModelLoader2);
            return i7;
        }
        throw zzfi.zzva();
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzdk zzdk) throws IOException {
        int i9;
        Unsafe unsafe = zzaki;
        long j2 = (long) (this.zzakj[i8 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzdl.zzc(bArr, i)));
                    i9 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzdl.zzd(bArr, i)));
                    i9 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    i9 = zzdl.zzb(bArr, i, zzdk);
                    unsafe.putObject(t, j, Long.valueOf(zzdk.zzadb));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    i9 = zzdl.zza(bArr, i, zzdk);
                    unsafe.putObject(t, j, Integer.valueOf(zzdk.zzada));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzdl.zzb(bArr, i)));
                    i9 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzdl.zza(bArr, i)));
                    i9 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    i9 = zzdl.zzb(bArr, i, zzdk);
                    unsafe.putObject(t, j, Boolean.valueOf(zzdk.zzadb != 0));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    int zza = zzdl.zza(bArr, i, zzdk);
                    int i10 = zzdk.zzada;
                    if (i10 == 0) {
                        unsafe.putObject(t, j, "");
                    } else if ((i6 & 536870912) == 0 || zzhy.zzf(bArr, zza, zza + i10)) {
                        unsafe.putObject(t, j, new String(bArr, zza, i10, zzez.UTF_8));
                        zza += i10;
                    } else {
                        throw zzfi.zzvb();
                    }
                    unsafe.putInt(t, j2, i4);
                    return zza;
                }
                return i;
            case 60:
                if (i5 == 2) {
                    int zza2 = zzdl.zza(zzbx(i8), bArr, i, i2, zzdk);
                    Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, zzdk.zzadc);
                    } else {
                        unsafe.putObject(t, j, zzez.zza(object, zzdk.zzadc));
                    }
                    unsafe.putInt(t, j2, i4);
                    return zza2;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    i9 = zzdl.zze(bArr, i, zzdk);
                    unsafe.putObject(t, j, zzdk.zzadc);
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int zza3 = zzdl.zza(bArr, i, zzdk);
                    int i11 = zzdk.zzada;
                    zzfe zzbz = zzbz(i8);
                    if (zzbz == null || zzbz.zzg(i11)) {
                        unsafe.putObject(t, j, Integer.valueOf(i11));
                        i9 = zza3;
                        unsafe.putInt(t, j2, i4);
                        return i9;
                    }
                    zzu(t).zzb(i3, Long.valueOf((long) i11));
                    return zza3;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    i9 = zzdl.zza(bArr, i, zzdk);
                    unsafe.putObject(t, j, Integer.valueOf(zzeb.zzaz(zzdk.zzada)));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    i9 = zzdl.zzb(bArr, i, zzdk);
                    unsafe.putObject(t, j, Long.valueOf(zzeb.zzbm(zzdk.zzadb)));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    i9 = zzdl.zza(zzbx(i8), bArr, i, i2, (i3 & -8) | 4, zzdk);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, zzdk.zzadc);
                    } else {
                        unsafe.putObject(t, j, zzez.zza(object2, zzdk.zzadc));
                    }
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            default:
                return i;
        }
    }

    private final zzgx zzbx(int i) {
        int i2 = (i / 3) << 1;
        zzgx zzgx = (zzgx) this.zzakk[i2];
        if (zzgx != null) {
            return zzgx;
        }
        zzgx<T> zzf = zzgt.zzvy().zzf((Class) this.zzakk[i2 + 1]);
        this.zzakk[i2] = zzf;
        return zzf;
    }

    private final Object zzby(int i) {
        return this.zzakk[(i / 3) << 1];
    }

    private final zzfe zzbz(int i) {
        return (zzfe) this.zzakk[((i / 3) << 1) + 1];
    }

    /* access modifiers changed from: package-private */
    public final int zza(T t, byte[] bArr, int i, int i2, int i3, zzdk zzdk) throws IOException {
        Unsafe unsafe;
        int i4;
        T t2;
        zzgm<T> zzgm;
        int i5;
        int i6;
        int i7;
        int i8;
        byte b;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        zzdk zzdk2;
        zzgm<T> zzgm2 = this;
        T t3 = t;
        byte[] bArr2 = bArr;
        int i25 = i2;
        int i26 = i3;
        zzdk zzdk3 = zzdk;
        Unsafe unsafe2 = zzaki;
        int i27 = i;
        int i28 = -1;
        int i29 = 0;
        int i30 = 0;
        int i31 = 0;
        int i32 = -1;
        while (true) {
            if (i27 < i25) {
                int i33 = i27 + 1;
                byte b2 = bArr2[i27];
                if (b2 < 0) {
                    i9 = zzdl.zza(b2, bArr2, i33, zzdk3);
                    b = zzdk3.zzada;
                } else {
                    b = b2;
                    i9 = i33;
                }
                int i34 = b >>> 3;
                int i35 = b & 7;
                if (i34 > i28) {
                    i10 = zzgm2.zzp(i34, i29 / 3);
                    i11 = -1;
                } else {
                    i10 = zzgm2.zzcd(i34);
                    i11 = -1;
                }
                if (i10 == i11) {
                    i12 = i34;
                    i5 = i9;
                    i13 = i31;
                    i15 = i32;
                    unsafe = unsafe2;
                    i4 = i26;
                    i14 = 0;
                    i16 = b;
                } else {
                    int[] iArr = zzgm2.zzakj;
                    int i36 = iArr[i10 + 1];
                    int i37 = (i36 & 267386880) >>> 20;
                    long j = (long) (i36 & 1048575);
                    if (i37 <= 17) {
                        int i38 = iArr[i10 + 2];
                        int i39 = 1 << (i38 >>> 20);
                        int i40 = i38 & 1048575;
                        if (i40 != i32) {
                            if (i32 != -1) {
                                unsafe2.putInt(t3, (long) i32, i31);
                            }
                            i31 = unsafe2.getInt(t3, (long) i40);
                            i32 = i40;
                        }
                        switch (i37) {
                            case 0:
                                i22 = i10;
                                i21 = i34;
                                i20 = i32;
                                i23 = b;
                                bArr2 = bArr;
                                i24 = i9;
                                if (i35 == 1) {
                                    zzhv.zza(t3, j, zzdl.zzc(bArr2, i24));
                                    i27 = i24 + 8;
                                    i31 |= i39;
                                    i32 = i20;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                }
                                i15 = i20;
                                i13 = i31;
                                i5 = i24;
                                i16 = i23;
                                i14 = i22;
                                unsafe = unsafe2;
                                i12 = i21;
                                i4 = i3;
                                break;
                            case 1:
                                i22 = i10;
                                i21 = i34;
                                i20 = i32;
                                i23 = b;
                                bArr2 = bArr;
                                i24 = i9;
                                if (i35 == 5) {
                                    zzhv.zza((Object) t3, j, zzdl.zzd(bArr2, i24));
                                    i27 = i24 + 4;
                                    i31 |= i39;
                                    i32 = i20;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                }
                                i15 = i20;
                                i13 = i31;
                                i5 = i24;
                                i16 = i23;
                                i14 = i22;
                                unsafe = unsafe2;
                                i12 = i21;
                                i4 = i3;
                                break;
                            case 2:
                            case 3:
                                i22 = i10;
                                i21 = i34;
                                i20 = i32;
                                i23 = b;
                                bArr2 = bArr;
                                i24 = i9;
                                if (i35 == 0) {
                                    int zzb = zzdl.zzb(bArr2, i24, zzdk);
                                    unsafe2.putLong(t, j, zzdk.zzadb);
                                    i31 |= i39;
                                    i27 = zzb;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i32 = i20;
                                    i25 = i2;
                                    break;
                                }
                                i15 = i20;
                                i13 = i31;
                                i5 = i24;
                                i16 = i23;
                                i14 = i22;
                                unsafe = unsafe2;
                                i12 = i21;
                                i4 = i3;
                                break;
                            case 4:
                            case 11:
                                i22 = i10;
                                i21 = i34;
                                i20 = i32;
                                i23 = b;
                                bArr2 = bArr;
                                i24 = i9;
                                if (i35 == 0) {
                                    i27 = zzdl.zza(bArr2, i24, zzdk);
                                    unsafe2.putInt(t3, j, zzdk.zzada);
                                    i31 |= i39;
                                    i32 = i20;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                }
                                i15 = i20;
                                i13 = i31;
                                i5 = i24;
                                i16 = i23;
                                i14 = i22;
                                unsafe = unsafe2;
                                i12 = i21;
                                i4 = i3;
                                break;
                            case 5:
                            case 14:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                bArr2 = bArr;
                                if (i35 == 1) {
                                    unsafe2.putLong(t, j, zzdl.zzb(bArr2, i9));
                                    i27 = i9 + 8;
                                    i31 |= i39;
                                    i32 = i32;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            case 6:
                            case 13:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                bArr2 = bArr;
                                if (i35 == 5) {
                                    unsafe2.putInt(t3, j, zzdl.zza(bArr2, i9));
                                    i27 = i9 + 4;
                                    i31 |= i39;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            case 7:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                bArr2 = bArr;
                                if (i35 == 0) {
                                    int zzb2 = zzdl.zzb(bArr2, i9, zzdk);
                                    zzhv.zza(t3, j, zzdk.zzadb != 0);
                                    i31 |= i39;
                                    i27 = zzb2;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            case 8:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                bArr2 = bArr;
                                if (i35 == 2) {
                                    if ((i36 & 536870912) == 0) {
                                        i27 = zzdl.zzc(bArr2, i9, zzdk);
                                    } else {
                                        i27 = zzdl.zzd(bArr2, i9, zzdk);
                                    }
                                    unsafe2.putObject(t3, j, zzdk.zzadc);
                                    i31 |= i39;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            case 9:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                bArr2 = bArr;
                                if (i35 == 2) {
                                    i27 = zzdl.zza(zzgm2.zzbx(i22), bArr2, i9, i2, zzdk);
                                    if ((i31 & i39) == 0) {
                                        unsafe2.putObject(t3, j, zzdk.zzadc);
                                    } else {
                                        unsafe2.putObject(t3, j, zzez.zza(unsafe2.getObject(t3, j), zzdk.zzadc));
                                    }
                                    i31 |= i39;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            case 10:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                bArr2 = bArr;
                                if (i35 == 2) {
                                    i27 = zzdl.zze(bArr2, i9, zzdk);
                                    unsafe2.putObject(t3, j, zzdk.zzadc);
                                    i31 |= i39;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            case 12:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                bArr2 = bArr;
                                if (i35 == 0) {
                                    i27 = zzdl.zza(bArr2, i9, zzdk);
                                    int i41 = zzdk.zzada;
                                    zzfe zzbz = zzgm2.zzbz(i22);
                                    if (zzbz != null && !zzbz.zzg(i41)) {
                                        zzu(t).zzb(i23, Long.valueOf((long) i41));
                                        i30 = i23;
                                        i29 = i22;
                                        i28 = i21;
                                        zzdk3 = zzdk;
                                        i26 = i3;
                                        i25 = i2;
                                        break;
                                    } else {
                                        unsafe2.putInt(t3, j, i41);
                                        i31 |= i39;
                                        i30 = i23;
                                        i29 = i22;
                                        i28 = i21;
                                        zzdk3 = zzdk;
                                        i26 = i3;
                                        i25 = i2;
                                        break;
                                    }
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                                break;
                            case 15:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                bArr2 = bArr;
                                if (i35 == 0) {
                                    i27 = zzdl.zza(bArr2, i9, zzdk);
                                    unsafe2.putInt(t3, j, zzeb.zzaz(zzdk.zzada));
                                    i31 |= i39;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            case 16:
                                i22 = i10;
                                i21 = i34;
                                i23 = b;
                                if (i35 == 0) {
                                    bArr2 = bArr;
                                    int zzb3 = zzdl.zzb(bArr2, i9, zzdk);
                                    unsafe2.putLong(t, j, zzeb.zzbm(zzdk.zzadb));
                                    i31 |= i39;
                                    i27 = zzb3;
                                    i30 = i23;
                                    i29 = i22;
                                    i28 = i21;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    break;
                                } else {
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            case 17:
                                if (i35 == 3) {
                                    i27 = zzdl.zza(zzgm2.zzbx(i10), bArr, i9, i2, (i34 << 3) | 4, zzdk);
                                    if ((i31 & i39) == 0) {
                                        zzdk2 = zzdk;
                                        unsafe2.putObject(t3, j, zzdk2.zzadc);
                                    } else {
                                        zzdk2 = zzdk;
                                        unsafe2.putObject(t3, j, zzez.zza(unsafe2.getObject(t3, j), zzdk2.zzadc));
                                    }
                                    i31 |= i39;
                                    i30 = b;
                                    i29 = i10;
                                    i28 = i34;
                                    i26 = i3;
                                    bArr2 = bArr;
                                    i25 = i2;
                                    zzdk3 = zzdk2;
                                    break;
                                } else {
                                    i22 = i10;
                                    i21 = i34;
                                    i23 = b;
                                    i20 = i32;
                                    i24 = i9;
                                    i15 = i20;
                                    i13 = i31;
                                    i5 = i24;
                                    i16 = i23;
                                    i14 = i22;
                                    unsafe = unsafe2;
                                    i12 = i21;
                                    i4 = i3;
                                    break;
                                }
                            default:
                                i22 = i10;
                                i21 = i34;
                                i20 = i32;
                                i23 = b;
                                i24 = i9;
                                i15 = i20;
                                i13 = i31;
                                i5 = i24;
                                i16 = i23;
                                i14 = i22;
                                unsafe = unsafe2;
                                i12 = i21;
                                i4 = i3;
                                break;
                        }
                    } else {
                        i15 = i32;
                        bArr2 = bArr;
                        if (i37 != 27) {
                            i13 = i31;
                            if (i37 <= 49) {
                                i12 = i34;
                                i14 = i10;
                                unsafe = unsafe2;
                                i27 = zza(t, bArr, i9, i2, b, i34, i35, i10, (long) i36, i37, j, zzdk);
                                if (i27 == i9) {
                                    i5 = i27;
                                    i16 = b;
                                    i4 = i3;
                                } else {
                                    bArr2 = bArr;
                                    i32 = i15;
                                    i29 = i14;
                                    i31 = i13;
                                    i28 = i12;
                                    i30 = b;
                                    unsafe2 = unsafe;
                                    zzdk3 = zzdk;
                                    i26 = i3;
                                    i25 = i2;
                                    t3 = t;
                                    zzgm2 = this;
                                }
                            } else {
                                i12 = i34;
                                i19 = i9;
                                i18 = b;
                                i14 = i10;
                                unsafe = unsafe2;
                                if (i37 != 50) {
                                    i27 = zza(t, bArr, i19, i2, i18, i12, i35, i36, i37, j, i14, zzdk);
                                    if (i27 == i19) {
                                        i5 = i27;
                                        i16 = i18;
                                        i4 = i3;
                                    } else {
                                        bArr2 = bArr;
                                        i30 = i18;
                                        i28 = i12;
                                        i32 = i15;
                                        i29 = i14;
                                        i31 = i13;
                                        unsafe2 = unsafe;
                                        zzdk3 = zzdk;
                                        i26 = i3;
                                        i25 = i2;
                                        t3 = t;
                                        zzgm2 = this;
                                    }
                                } else if (i35 == 2) {
                                    i27 = zza(t, bArr, i19, i2, i14, j, zzdk);
                                    if (i27 == i19) {
                                        i5 = i27;
                                        i16 = i18;
                                        i4 = i3;
                                    } else {
                                        bArr2 = bArr;
                                        i32 = i15;
                                        i29 = i14;
                                        i31 = i13;
                                        i28 = i12;
                                        i30 = i18;
                                        unsafe2 = unsafe;
                                        zzdk3 = zzdk;
                                        i26 = i3;
                                        i25 = i2;
                                        t3 = t;
                                        zzgm2 = this;
                                    }
                                }
                            }
                        } else if (i35 == 2) {
                            zzff zzff = (zzff) unsafe2.getObject(t3, j);
                            if (!zzff.zzrx()) {
                                int size = zzff.size();
                                zzff = zzff.zzap(size == 0 ? 10 : size << 1);
                                unsafe2.putObject(t3, j, zzff);
                            }
                            i27 = zzdl.zza(zzgm2.zzbx(i10), b, bArr, i9, i2, zzff, zzdk);
                            i28 = i34;
                            i30 = b;
                            i29 = i10;
                            zzdk3 = zzdk3;
                            i32 = i15;
                            i31 = i31;
                            i26 = i3;
                            i25 = i2;
                        } else {
                            i13 = i31;
                            i12 = i34;
                            i19 = i9;
                            i18 = b;
                            i14 = i10;
                            unsafe = unsafe2;
                        }
                        i5 = i19;
                        i16 = i18;
                        i4 = i3;
                    }
                }
                if (i16 != i4 || i4 == 0) {
                    if (this.zzako) {
                        zzdk3 = zzdk;
                        if (zzdk3.zzadd == zzel.zztp()) {
                            i17 = i12;
                        } else if (zzdk3.zzadd.zza(this.zzakn, i12) == null) {
                            i27 = zzdl.zza(i16, bArr, i5, i2, zzu(t), zzdk);
                            bArr2 = bArr;
                            i26 = i4;
                            i30 = i16;
                            zzgm2 = this;
                            i28 = i12;
                            i32 = i15;
                            i29 = i14;
                            i31 = i13;
                            unsafe2 = unsafe;
                            i25 = i2;
                            t3 = t;
                        } else {
                            T t4 = t;
                            t4.zzuq();
                            zzeo<Object> zzeo = t4.zzaic;
                            throw new NoSuchMethodError();
                        }
                    } else {
                        i17 = i12;
                        zzdk3 = zzdk;
                    }
                    i27 = zzdl.zza(i16, bArr, i5, i2, zzu(t), zzdk);
                    bArr2 = bArr;
                    i30 = i16;
                    zzgm2 = this;
                    i28 = i17;
                    t3 = t;
                    i32 = i15;
                    i29 = i14;
                    unsafe2 = unsafe;
                    i25 = i2;
                    i26 = i4;
                    i31 = i13;
                } else {
                    i30 = i16;
                    i6 = i15;
                    i7 = i13;
                    i8 = -1;
                    zzgm = this;
                    t2 = t;
                }
            } else {
                unsafe = unsafe2;
                i4 = i26;
                t2 = t3;
                zzgm = zzgm2;
                i5 = i27;
                i6 = i32;
                i7 = i31;
                i8 = -1;
            }
        }
        if (i6 != i8) {
            unsafe.putInt(t2, (long) i6, i7);
        }
        zzhs zzhs = null;
        for (int i42 = zzgm.zzakt; i42 < zzgm.zzaku; i42++) {
            zzhs = (zzhs) zzgm.zza(t2, zzgm.zzaks[i42], zzhs, (zzhp<UT, UB>) zzgm.zzakx);
        }
        if (zzhs != null) {
            zzgm.zzakx.zzf(t2, zzhs);
        }
        if (i4 == 0) {
            if (i5 != i2) {
                throw zzfi.zzva();
            }
        } else if (i5 > i2 || i30 != i4) {
            throw zzfi.zzva();
        }
        return i5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zza(T t, byte[] bArr, int i, int i2, zzdk zzdk) throws IOException {
        byte b;
        int i3;
        int i4;
        int i5;
        int i6;
        Unsafe unsafe;
        int i7;
        int i8;
        zzgm<T> zzgm = this;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i9 = i2;
        zzdk zzdk2 = zzdk;
        if (zzgm.zzakq) {
            Unsafe unsafe2 = zzaki;
            int i10 = -1;
            int i11 = i;
            int i12 = -1;
            int i13 = 0;
            while (i11 < i9) {
                int i14 = i11 + 1;
                byte b2 = bArr2[i11];
                if (b2 < 0) {
                    i3 = zzdl.zza(b2, bArr2, i14, zzdk2);
                    b = zzdk2.zzada;
                } else {
                    b = b2;
                    i3 = i14;
                }
                int i15 = b >>> 3;
                int i16 = b & 7;
                if (i15 > i12) {
                    i4 = zzgm.zzp(i15, i13 / 3);
                } else {
                    i4 = zzgm.zzcd(i15);
                }
                if (i4 == i10) {
                    i5 = i15;
                    i7 = i3;
                    unsafe = unsafe2;
                    i6 = 0;
                } else {
                    int i17 = zzgm.zzakj[i4 + 1];
                    int i18 = (267386880 & i17) >>> 20;
                    long j = (long) (1048575 & i17);
                    if (i18 <= 17) {
                        boolean z = true;
                        switch (i18) {
                            case 0:
                                if (i16 != 1) {
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    i6 = i4;
                                    break;
                                } else {
                                    zzhv.zza(t2, j, zzdl.zzc(bArr2, i3));
                                    i11 = i3 + 8;
                                    i12 = i15;
                                    i13 = i4;
                                    i10 = -1;
                                    continue;
                                }
                            case 1:
                                if (i16 != 5) {
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    i6 = i4;
                                    break;
                                } else {
                                    zzhv.zza((Object) t2, j, zzdl.zzd(bArr2, i3));
                                    i11 = i3 + 4;
                                    i12 = i15;
                                    i13 = i4;
                                    i10 = -1;
                                    continue;
                                }
                            case 2:
                            case 3:
                                if (i16 != 0) {
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    i6 = i4;
                                    break;
                                } else {
                                    int zzb = zzdl.zzb(bArr2, i3, zzdk2);
                                    unsafe2.putLong(t, j, zzdk2.zzadb);
                                    i11 = zzb;
                                    i12 = i15;
                                    i13 = i4;
                                    i10 = -1;
                                    continue;
                                }
                            case 4:
                            case 11:
                                if (i16 != 0) {
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    i6 = i4;
                                    break;
                                } else {
                                    i11 = zzdl.zza(bArr2, i3, zzdk2);
                                    unsafe2.putInt(t2, j, zzdk2.zzada);
                                    i12 = i15;
                                    i13 = i4;
                                    i10 = -1;
                                    continue;
                                }
                            case 5:
                            case 14:
                                if (i16 != 1) {
                                    i6 = i4;
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    break;
                                } else {
                                    unsafe2.putLong(t, j, zzdl.zzb(bArr2, i3));
                                    i11 = i3 + 8;
                                    i12 = i15;
                                    i13 = i4;
                                    i10 = -1;
                                    continue;
                                }
                            case 6:
                            case 13:
                                if (i16 != 5) {
                                    i6 = i4;
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    break;
                                } else {
                                    unsafe2.putInt(t2, j, zzdl.zza(bArr2, i3));
                                    i11 = i3 + 4;
                                    i13 = i4;
                                    i12 = i15;
                                    i10 = -1;
                                    continue;
                                }
                            case 7:
                                if (i16 != 0) {
                                    i6 = i4;
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    break;
                                } else {
                                    int zzb2 = zzdl.zzb(bArr2, i3, zzdk2);
                                    if (zzdk2.zzadb == 0) {
                                        z = false;
                                    }
                                    zzhv.zza(t2, j, z);
                                    i11 = zzb2;
                                    i13 = i4;
                                    i12 = i15;
                                    i10 = -1;
                                    continue;
                                }
                            case 8:
                                if (i16 != 2) {
                                    i6 = i4;
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    break;
                                } else {
                                    if ((536870912 & i17) == 0) {
                                        i11 = zzdl.zzc(bArr2, i3, zzdk2);
                                    } else {
                                        i11 = zzdl.zzd(bArr2, i3, zzdk2);
                                    }
                                    unsafe2.putObject(t2, j, zzdk2.zzadc);
                                    i13 = i4;
                                    i12 = i15;
                                    i10 = -1;
                                    continue;
                                }
                            case 9:
                                if (i16 != 2) {
                                    i6 = i4;
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    break;
                                } else {
                                    i11 = zzdl.zza(zzgm.zzbx(i4), bArr2, i3, i9, zzdk2);
                                    Object object = unsafe2.getObject(t2, j);
                                    if (object == null) {
                                        unsafe2.putObject(t2, j, zzdk2.zzadc);
                                    } else {
                                        unsafe2.putObject(t2, j, zzez.zza(object, zzdk2.zzadc));
                                    }
                                    i13 = i4;
                                    i12 = i15;
                                    i10 = -1;
                                    continue;
                                }
                            case 10:
                                if (i16 != 2) {
                                    i6 = i4;
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    break;
                                } else {
                                    i11 = zzdl.zze(bArr2, i3, zzdk2);
                                    unsafe2.putObject(t2, j, zzdk2.zzadc);
                                    i13 = i4;
                                    i12 = i15;
                                    i10 = -1;
                                    continue;
                                }
                            case 12:
                                if (i16 != 0) {
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    i6 = i4;
                                    break;
                                } else {
                                    i11 = zzdl.zza(bArr2, i3, zzdk2);
                                    unsafe2.putInt(t2, j, zzdk2.zzada);
                                    i12 = i15;
                                    i13 = i4;
                                    i10 = -1;
                                    continue;
                                }
                            case 15:
                                if (i16 != 0) {
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    i6 = i4;
                                    break;
                                } else {
                                    i11 = zzdl.zza(bArr2, i3, zzdk2);
                                    unsafe2.putInt(t2, j, zzeb.zzaz(zzdk2.zzada));
                                    i12 = i15;
                                    i13 = i4;
                                    i10 = -1;
                                    continue;
                                }
                            case 16:
                                if (i16 != 0) {
                                    i6 = i4;
                                    i5 = i15;
                                    i8 = i3;
                                    unsafe = unsafe2;
                                    break;
                                } else {
                                    int zzb3 = zzdl.zzb(bArr2, i3, zzdk2);
                                    unsafe2.putLong(t, j, zzeb.zzbm(zzdk2.zzadb));
                                    i11 = zzb3;
                                    i12 = i15;
                                    i13 = i4;
                                    i10 = -1;
                                    continue;
                                }
                            default:
                                i6 = i4;
                                i5 = i15;
                                i8 = i3;
                                unsafe = unsafe2;
                                break;
                        }
                    } else if (i18 != 27) {
                        i6 = i4;
                        if (i18 <= 49) {
                            i5 = i15;
                            unsafe = unsafe2;
                            i11 = zza(t, bArr, i3, i2, b, i15, i16, i6, (long) i17, i18, j, zzdk);
                            if (i11 == i3) {
                                i7 = i11;
                            } else {
                                t2 = t;
                                bArr2 = bArr;
                                zzdk2 = zzdk;
                                unsafe2 = unsafe;
                                i13 = i6;
                                i12 = i5;
                                i10 = -1;
                                i9 = i2;
                                zzgm = this;
                            }
                        } else {
                            i5 = i15;
                            i8 = i3;
                            unsafe = unsafe2;
                            if (i18 != 50) {
                                i11 = zza(t, bArr, i8, i2, b, i5, i16, i17, i18, j, i6, zzdk);
                                if (i11 == i8) {
                                    i7 = i11;
                                } else {
                                    t2 = t;
                                    bArr2 = bArr;
                                    zzdk2 = zzdk;
                                    unsafe2 = unsafe;
                                    i13 = i6;
                                    i12 = i5;
                                    i10 = -1;
                                    i9 = i2;
                                    zzgm = this;
                                }
                            } else if (i16 == 2) {
                                i11 = zza(t, bArr, i8, i2, i6, j, zzdk);
                                if (i11 == i8) {
                                    i7 = i11;
                                } else {
                                    t2 = t;
                                    bArr2 = bArr;
                                    zzdk2 = zzdk;
                                    unsafe2 = unsafe;
                                    i13 = i6;
                                    i12 = i5;
                                    i10 = -1;
                                    i9 = i2;
                                    zzgm = this;
                                }
                            }
                        }
                    } else if (i16 == 2) {
                        zzff zzff = (zzff) unsafe2.getObject(t2, j);
                        if (!zzff.zzrx()) {
                            int size = zzff.size();
                            zzff = zzff.zzap(size == 0 ? 10 : size << 1);
                            unsafe2.putObject(t2, j, zzff);
                        }
                        i11 = zzdl.zza(zzgm.zzbx(i4), b, bArr, i3, i2, zzff, zzdk);
                        i12 = i15;
                        i13 = i4;
                        i10 = -1;
                    } else {
                        i6 = i4;
                        i5 = i15;
                        i8 = i3;
                        unsafe = unsafe2;
                    }
                    i7 = i8;
                }
                i11 = zzdl.zza(b, bArr, i7, i2, zzu(t), zzdk);
                t2 = t;
                bArr2 = bArr;
                zzdk2 = zzdk;
                unsafe2 = unsafe;
                i13 = i6;
                i12 = i5;
                i10 = -1;
                i9 = i2;
                zzgm = this;
            }
            if (i11 != i9) {
                throw zzfi.zzva();
            }
            return;
        }
        zza(t, bArr, i, i2, 0, zzdk);
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zzj(T t) {
        int i;
        int i2 = this.zzakt;
        while (true) {
            i = this.zzaku;
            if (i2 >= i) {
                break;
            }
            long zzca = (long) (zzca(this.zzaks[i2]) & 1048575);
            Object zzp = zzhv.zzp(t, zzca);
            if (zzp != null) {
                zzhv.zza(t, zzca, this.zzakz.zzp(zzp));
            }
            i2++;
        }
        int length = this.zzaks.length;
        while (i < length) {
            this.zzakw.zzb(t, (long) this.zzaks[i]);
            i++;
        }
        this.zzakx.zzj(t);
        if (this.zzako) {
            this.zzaky.zzj(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzhp<UT, UB> zzhp) {
        zzfe zzbz;
        int i2 = this.zzakj[i];
        Object zzp = zzhv.zzp(obj, (long) (zzca(i) & 1048575));
        return (zzp == null || (zzbz = zzbz(i)) == null) ? ub : (UB) zza(i, i2, (Map<K, V>) this.zzakz.zzm(zzp), zzbz, ub, zzhp);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzfe zzfe, UB ub, zzhp<UT, UB> zzhp) {
        zzfz<?, ?> zzr = this.zzakz.zzr(zzby(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzfe.zzg(next.getValue().intValue())) {
                if (ub == null) {
                    ub = zzhp.zzwp();
                }
                zzdx zzas = zzdp.zzas(zzga.zza(zzr, next.getKey(), next.getValue()));
                try {
                    zzga.zza(zzas.zzsf(), zzr, next.getKey(), next.getValue());
                    zzhp.zza(ub, i2, zzas.zzse());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.measurement.zzgx] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.measurement.zzgx] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0104, code lost:
        continue;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // com.google.android.gms.internal.measurement.zzgx
    public final boolean zzv(T t) {
        int i;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.zzakt) {
                return !this.zzako || this.zzaky.zzh(t).isInitialized();
            }
            int i5 = this.zzaks[i2];
            int i6 = this.zzakj[i5];
            int zzca = zzca(i5);
            if (!this.zzakq) {
                int i7 = this.zzakj[i5 + 2];
                int i8 = i7 & 1048575;
                i = 1 << (i7 >>> 20);
                if (i8 != i3) {
                    i4 = zzaki.getInt(t, (long) i8);
                    i3 = i8;
                }
            } else {
                i = 0;
            }
            if (((268435456 & zzca) != 0) && !zza(t, i5, i4, i)) {
                return false;
            }
            int i9 = (267386880 & zzca) >>> 20;
            if (i9 != 9 && i9 != 17) {
                if (i9 != 27) {
                    if (i9 != 60 && i9 != 68) {
                        switch (i9) {
                            case 50:
                                Map<?, ?> zzn = this.zzakz.zzn(zzhv.zzp(t, (long) (zzca & 1048575)));
                                if (!zzn.isEmpty()) {
                                    if (this.zzakz.zzr(zzby(i5)).zzakd.zzwz() == zzij.MESSAGE) {
                                        zzgx<T> zzgx = 0;
                                        Iterator<?> it = zzn.values().iterator();
                                        while (true) {
                                            if (it.hasNext()) {
                                                Object next = it.next();
                                                if (zzgx == null) {
                                                    zzgx = zzgt.zzvy().zzf(next.getClass());
                                                }
                                                boolean zzv = zzgx.zzv(next);
                                                zzgx = zzgx;
                                                if (!zzv) {
                                                    z = false;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (z) {
                                    break;
                                } else {
                                    return false;
                                }
                        }
                    } else if (zza(t, i6, i5) && !zza(t, zzca, zzbx(i5))) {
                        return false;
                    }
                }
                List list = (List) zzhv.zzp(t, (long) (zzca & 1048575));
                if (!list.isEmpty()) {
                    ?? zzbx = zzbx(i5);
                    int i10 = 0;
                    while (true) {
                        if (i10 < list.size()) {
                            if (!zzbx.zzv(list.get(i10))) {
                                z = false;
                            } else {
                                i10++;
                            }
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza(t, i5, i4, i) && !zza(t, zzca, zzbx(i5))) {
                return false;
            }
            i2++;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.android.gms.internal.measurement.zzgx */
    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzgx zzgx) {
        return zzgx.zzv(zzhv.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzim zzim) throws IOException {
        if (obj instanceof String) {
            zzim.zzb(i, (String) obj);
        } else {
            zzim.zza(i, (zzdp) obj);
        }
    }

    private final void zza(Object obj, int i, zzgy zzgy) throws IOException {
        if (zzcc(i)) {
            zzhv.zza(obj, (long) (i & 1048575), zzgy.zzsn());
        } else if (this.zzakp) {
            zzhv.zza(obj, (long) (i & 1048575), zzgy.readString());
        } else {
            zzhv.zza(obj, (long) (i & 1048575), zzgy.zzso());
        }
    }

    private final int zzca(int i) {
        return this.zzakj[i + 1];
    }

    private final int zzcb(int i) {
        return this.zzakj[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzhv.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzhv.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzhv.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzhv.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzhv.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzakq) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzakq) {
            int zzca = zzca(i);
            long j = (long) (zzca & 1048575);
            switch ((zzca & 267386880) >>> 20) {
                case 0:
                    return zzhv.zzo(t, j) != 0.0d;
                case 1:
                    return zzhv.zzn(t, j) != 0.0f;
                case 2:
                    return zzhv.zzl(t, j) != 0;
                case 3:
                    return zzhv.zzl(t, j) != 0;
                case 4:
                    return zzhv.zzk(t, j) != 0;
                case 5:
                    return zzhv.zzl(t, j) != 0;
                case 6:
                    return zzhv.zzk(t, j) != 0;
                case 7:
                    return zzhv.zzm(t, j);
                case 8:
                    Object zzp = zzhv.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzdp) {
                        return !zzdp.zzadh.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzhv.zzp(t, j) != null;
                case 10:
                    return !zzdp.zzadh.equals(zzhv.zzp(t, j));
                case 11:
                    return zzhv.zzk(t, j) != 0;
                case 12:
                    return zzhv.zzk(t, j) != 0;
                case 13:
                    return zzhv.zzk(t, j) != 0;
                case 14:
                    return zzhv.zzl(t, j) != 0;
                case 15:
                    return zzhv.zzk(t, j) != 0;
                case 16:
                    return zzhv.zzl(t, j) != 0;
                case 17:
                    return zzhv.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzcb = zzcb(i);
            return (zzhv.zzk(t, (long) (zzcb & 1048575)) & (1 << (zzcb >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        if (!this.zzakq) {
            int zzcb = zzcb(i);
            long j = (long) (zzcb & 1048575);
            zzhv.zzb(t, j, zzhv.zzk(t, j) | (1 << (zzcb >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzhv.zzk(t, (long) (zzcb(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzhv.zzb(t, (long) (zzcb(i2) & 1048575), i);
    }

    private final int zzcd(int i) {
        if (i < this.zzakl || i > this.zzakm) {
            return -1;
        }
        return zzq(i, 0);
    }

    private final int zzp(int i, int i2) {
        if (i < this.zzakl || i > this.zzakm) {
            return -1;
        }
        return zzq(i, i2);
    }

    private final int zzq(int i, int i2) {
        int length = (this.zzakj.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzakj[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
