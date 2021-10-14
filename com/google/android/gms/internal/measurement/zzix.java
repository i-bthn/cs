package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzix {
    private static final int[] zzakh = new int[0];
    private static final int zzaox = 11;
    private static final int zzaoy = 12;
    private static final int zzaoz = 16;
    private static final int zzapa = 26;
    private static final long[] zzapb = new long[0];
    private static final float[] zzapc = new float[0];
    private static final double[] zzapd = new double[0];
    private static final boolean[] zzape = new boolean[0];
    private static final String[] zzapf = new String[0];
    private static final byte[][] zzapg = new byte[0][];
    public static final byte[] zzaph = new byte[0];

    public static final int zzb(zzil zzil, int i) throws IOException {
        int position = zzil.getPosition();
        zzil.zzau(i);
        int i2 = 1;
        while (zzil.zzsg() == i) {
            zzil.zzau(i);
            i2++;
        }
        zzil.zzu(position, i);
        return i2;
    }
}
