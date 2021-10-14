package com.google.zxing.client.android.encode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import kotlin.text.Typography;

final class VCardFieldFormatter implements Formatter {
    private static final Pattern NEWLINE = Pattern.compile("\\n");
    private static final Pattern RESERVED_VCARD_CHARS = Pattern.compile("([\\\\,;])");
    private final List<Map<String, Set<String>>> metadataForIndex;

    VCardFieldFormatter() {
        this(null);
    }

    VCardFieldFormatter(List<Map<String, Set<String>>> list) {
        this.metadataForIndex = list;
    }

    @Override // com.google.zxing.client.android.encode.Formatter
    public CharSequence format(CharSequence charSequence, int i) {
        String replaceAll = NEWLINE.matcher(RESERVED_VCARD_CHARS.matcher(charSequence).replaceAll("\\\\$1")).replaceAll("");
        List<Map<String, Set<String>>> list = this.metadataForIndex;
        return formatMetadata(replaceAll, (list == null || list.size() <= i) ? null : this.metadataForIndex.get(i));
    }

    private static CharSequence formatMetadata(CharSequence charSequence, Map<String, Set<String>> map) {
        StringBuilder sb = new StringBuilder();
        if (map != null) {
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                Set<String> value = entry.getValue();
                if (value != null && !value.isEmpty()) {
                    sb.append(';');
                    sb.append(entry.getKey());
                    sb.append('=');
                    if (value.size() > 1) {
                        sb.append(Typography.quote);
                    }
                    Iterator<String> it = value.iterator();
                    sb.append(it.next());
                    while (it.hasNext()) {
                        sb.append(',');
                        sb.append(it.next());
                    }
                    if (value.size() > 1) {
                        sb.append(Typography.quote);
                    }
                }
            }
        }
        sb.append(':');
        sb.append(charSequence);
        return sb;
    }
}
