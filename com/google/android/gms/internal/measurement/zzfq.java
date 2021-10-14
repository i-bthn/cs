package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzfq extends zzdj<String> implements zzfp, RandomAccess {
    private static final zzfq zzajq;
    private static final zzfp zzajr = zzajq;
    private final List<Object> zzajs;

    public zzfq() {
        this(10);
    }

    public zzfq(int i) {
        this(new ArrayList(i));
    }

    private zzfq(ArrayList<Object> arrayList) {
        this.zzajs = arrayList;
    }

    public final int size() {
        return this.zzajs.size();
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.android.gms.internal.measurement.zzdj
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // java.util.List, java.util.AbstractList, com.google.android.gms.internal.measurement.zzdj
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzrz();
        if (collection instanceof zzfp) {
            collection = ((zzfp) collection).zzvf();
        }
        boolean addAll = this.zzajs.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final void clear() {
        zzrz();
        this.zzajs.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzfp
    public final void zzc(zzdp zzdp) {
        zzrz();
        this.zzajs.add(zzdp);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzfp
    public final Object zzbw(int i) {
        return this.zzajs.get(i);
    }

    private static String zzl(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzdp) {
            return ((zzdp) obj).zzsa();
        }
        return zzez.zzi((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzfp
    public final List<?> zzvf() {
        return Collections.unmodifiableList(this.zzajs);
    }

    @Override // com.google.android.gms.internal.measurement.zzfp
    public final zzfp zzvg() {
        return zzrx() ? new zzhu(this) : this;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, java.util.AbstractList, com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ String set(int i, String str) {
        zzrz();
        return zzl(this.zzajs.set(i, str));
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.android.gms.internal.measurement.zzdj
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.android.gms.internal.measurement.zzdj
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // java.util.List, com.google.android.gms.internal.measurement.zzdj
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.List, java.util.AbstractList, com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ String remove(int i) {
        zzrz();
        Object remove = this.zzajs.remove(i);
        this.modCount++;
        return zzl(remove);
    }

    @Override // com.google.android.gms.internal.measurement.zzff, com.google.android.gms.internal.measurement.zzdj
    public final /* bridge */ /* synthetic */ boolean zzrx() {
        return super.zzrx();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, java.util.AbstractList, com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ void add(int i, String str) {
        zzrz();
        this.zzajs.add(i, str);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzff
    public final /* synthetic */ zzff zzap(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzajs);
            return new zzfq(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.List, java.util.AbstractList
    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzajs.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzdp) {
            zzdp zzdp = (zzdp) obj;
            String zzsa = zzdp.zzsa();
            if (zzdp.zzsb()) {
                this.zzajs.set(i, zzsa);
            }
            return zzsa;
        }
        byte[] bArr = (byte[]) obj;
        String zzi = zzez.zzi(bArr);
        if (zzez.zzh(bArr)) {
            this.zzajs.set(i, zzi);
        }
        return zzi;
    }

    static {
        zzfq zzfq = new zzfq();
        zzajq = zzfq;
        zzfq.zzry();
    }
}
