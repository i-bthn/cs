package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzhl {
    static String zzd(zzdp zzdp) {
        zzho zzho = new zzho(zzdp);
        StringBuilder sb = new StringBuilder(zzho.size());
        for (int i = 0; i < zzho.size(); i++) {
            byte zzaq = zzho.zzaq(i);
            if (zzaq == 34) {
                sb.append("\\\"");
            } else if (zzaq == 39) {
                sb.append("\\'");
            } else if (zzaq != 92) {
                switch (zzaq) {
                    case 7:
                        sb.append("\\a");
                        continue;
                    case 8:
                        sb.append("\\b");
                        continue;
                    case 9:
                        sb.append("\\t");
                        continue;
                    case 10:
                        sb.append("\\n");
                        continue;
                    case 11:
                        sb.append("\\v");
                        continue;
                    case 12:
                        sb.append("\\f");
                        continue;
                    case 13:
                        sb.append("\\r");
                        continue;
                    default:
                        if (zzaq < 32 || zzaq > 126) {
                            sb.append('\\');
                            sb.append((char) (((zzaq >>> 6) & 3) + 48));
                            sb.append((char) (((zzaq >>> 3) & 7) + 48));
                            sb.append((char) ((zzaq & 7) + 48));
                            break;
                        } else {
                            sb.append((char) zzaq);
                            continue;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
