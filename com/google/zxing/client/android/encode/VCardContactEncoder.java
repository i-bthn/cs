package com.google.zxing.client.android.encode;

import com.google.zxing.client.android.Intents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class VCardContactEncoder extends ContactEncoder {
    private static final char TERMINATOR = '\n';

    private static String vCardContextLabelForAndroidType(int i) {
        if (i == 10) {
            return "work";
        }
        switch (i) {
            case 1:
            case 2:
            case 5:
            case 6:
                return "home";
            case 3:
            case 4:
                return "work";
            default:
                switch (i) {
                    case 17:
                    case 18:
                        return "work";
                    default:
                        return null;
                }
        }
    }

    private static String vCardPurposeLabelForAndroidType(int i) {
        if (i == 13) {
            return "fax";
        }
        if (i == 16) {
            return "textphone";
        }
        if (i == 18) {
            return "pager";
        }
        if (i == 20) {
            return "text";
        }
        switch (i) {
            case 4:
            case 5:
                return "fax";
            case 6:
                return "pager";
            default:
                return null;
        }
    }

    VCardContactEncoder() {
    }

    @Override // com.google.zxing.client.android.encode.ContactEncoder
    public String[] encode(List<String> list, String str, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, String str2) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("BEGIN:VCARD");
        sb.append(TERMINATOR);
        sb.append("VERSION:3.0");
        sb.append(TERMINATOR);
        StringBuilder sb2 = new StringBuilder(100);
        VCardFieldFormatter vCardFieldFormatter = new VCardFieldFormatter();
        appendUpToUnique(sb, sb2, "N", list, 1, null, vCardFieldFormatter, TERMINATOR);
        append(sb, sb2, "ORG", str, vCardFieldFormatter, TERMINATOR);
        appendUpToUnique(sb, sb2, "ADR", list2, 1, null, vCardFieldFormatter, TERMINATOR);
        List<Map<String, Set<String>>> buildPhoneMetadata = buildPhoneMetadata(list3, list4);
        appendUpToUnique(sb, sb2, "TEL", list3, Integer.MAX_VALUE, new VCardTelDisplayFormatter(buildPhoneMetadata), new VCardFieldFormatter(buildPhoneMetadata), TERMINATOR);
        appendUpToUnique(sb, sb2, "EMAIL", list5, Integer.MAX_VALUE, null, vCardFieldFormatter, TERMINATOR);
        appendUpToUnique(sb, sb2, "URL", list6, Integer.MAX_VALUE, null, vCardFieldFormatter, TERMINATOR);
        append(sb, sb2, "NOTE", str2, vCardFieldFormatter, TERMINATOR);
        sb.append("END:VCARD");
        sb.append(TERMINATOR);
        return new String[]{sb.toString(), sb2.toString()};
    }

    private static List<Map<String, Set<String>>> buildPhoneMetadata(Collection<String> collection, List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < collection.size(); i++) {
            if (list.size() <= i) {
                arrayList.add(null);
            } else {
                HashMap hashMap = new HashMap();
                arrayList.add(hashMap);
                HashSet hashSet = new HashSet();
                hashMap.put(Intents.WifiConnect.TYPE, hashSet);
                String str = list.get(i);
                Integer maybeIntValue = maybeIntValue(str);
                if (maybeIntValue == null) {
                    hashSet.add(str);
                } else {
                    String vCardPurposeLabelForAndroidType = vCardPurposeLabelForAndroidType(maybeIntValue.intValue());
                    String vCardContextLabelForAndroidType = vCardContextLabelForAndroidType(maybeIntValue.intValue());
                    if (vCardPurposeLabelForAndroidType != null) {
                        hashSet.add(vCardPurposeLabelForAndroidType);
                    }
                    if (vCardContextLabelForAndroidType != null) {
                        hashSet.add(vCardContextLabelForAndroidType);
                    }
                }
            }
        }
        return arrayList;
    }

    private static Integer maybeIntValue(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
