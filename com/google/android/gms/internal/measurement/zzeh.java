package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzeh extends zzdj<Double> implements zzff<Double>, zzgu, RandomAccess {
    private static final zzeh zzaeo;
    private int size;
    private double[] zzaep;

    zzeh() {
        this(new double[10], 0);
    }

    private zzeh(double[] dArr, int i) {
        this.zzaep = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzrz();
        if (i2 >= i) {
            double[] dArr = this.zzaep;
            System.arraycopy(dArr, i2, dArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeh)) {
            return super.equals(obj);
        }
        zzeh zzeh = (zzeh) obj;
        if (this.size != zzeh.size) {
            return false;
        }
        double[] dArr = zzeh.zzaep;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzaep[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzez.zzbx(Double.doubleToLongBits(this.zzaep[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzf(double d) {
        zzc(this.size, d);
    }

    private final void zzc(int i, double d) {
        int i2;
        zzrz();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzao(i));
        }
        double[] dArr = this.zzaep;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzaep, i, dArr2, i + 1, this.size - i);
            this.zzaep = dArr2;
        }
        this.zzaep[i] = d;
        this.size++;
        this.modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.android.gms.internal.measurement.zzdj
    public final boolean addAll(Collection<? extends Double> collection) {
        zzrz();
        zzez.checkNotNull(collection);
        if (!(collection instanceof zzeh)) {
            return super.addAll(collection);
        }
        zzeh zzeh = (zzeh) collection;
        int i = zzeh.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzaep;
            if (i3 > dArr.length) {
                this.zzaep = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzeh.zzaep, 0, this.zzaep, this.size, zzeh.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // java.util.List, com.google.android.gms.internal.measurement.zzdj
    public final boolean remove(Object obj) {
        zzrz();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzaep[i]))) {
                double[] dArr = this.zzaep;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzan(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzao(i));
        }
    }

    private final String zzao(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, java.util.AbstractList, com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ Double set(int i, Double d) {
        double doubleValue = d.doubleValue();
        zzrz();
        zzan(i);
        double[] dArr = this.zzaep;
        double d2 = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.List, java.util.AbstractList, com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ Double remove(int i) {
        zzrz();
        zzan(i);
        double[] dArr = this.zzaep;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, java.util.AbstractList, com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ void add(int i, Double d) {
        zzc(i, d.doubleValue());
    }

    /* Return type fixed from 'com.google.android.gms.internal.measurement.zzff' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzff
    public final /* synthetic */ zzff<Double> zzap(int i) {
        if (i >= this.size) {
            return new zzeh(Arrays.copyOf(this.zzaep, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.List, java.util.AbstractList
    public final /* synthetic */ Object get(int i) {
        zzan(i);
        return Double.valueOf(this.zzaep[i]);
    }

    static {
        zzeh zzeh = new zzeh(new double[0], 0);
        zzaeo = zzeh;
        zzeh.zzry();
    }
}
