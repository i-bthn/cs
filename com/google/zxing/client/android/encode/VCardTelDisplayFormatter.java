package com.google.zxing.client.android.encode;

import android.telephony.PhoneNumberUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class VCardTelDisplayFormatter implements Formatter {
    private final List<Map<String, Set<String>>> metadataForIndex;

    VCardTelDisplayFormatter() {
        this(null);
    }

    VCardTelDisplayFormatter(List<Map<String, Set<String>>> list) {
        this.metadataForIndex = list;
    }

    @Override // com.google.zxing.client.android.encode.Formatter
    public CharSequence format(CharSequence charSequence, int i) {
        String formatNumber = PhoneNumberUtils.formatNumber(charSequence.toString());
        List<Map<String, Set<String>>> list = this.metadataForIndex;
        return formatMetadata(formatNumber, (list == null || list.size() <= i) ? null : this.metadataForIndex.get(i));
    }

    private static CharSequence formatMetadata(CharSequence charSequence, Map<String, Set<String>> map) {
        if (map == null || map.isEmpty()) {
            return charSequence;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            Set<String> value = entry.getValue();
            if (value != null && !value.isEmpty()) {
                Iterator<String> it = value.iterator();
                sb.append(it.next());
                while (it.hasNext()) {
                    sb.append(',');
                    sb.append(it.next());
                }
            }
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(charSequence);
        return sb;
    }
}
