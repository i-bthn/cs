package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;
import kotlin.text.Typography;

public final class ExpandedProductResultParser extends ResultParser {
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0230  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x023c  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x026b  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0272  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0279  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0280  */
    @Override // com.google.zxing.client.result.ResultParser
    public ExpandedProductParsedResult parse(Result result) {
        char c;
        ExpandedProductParsedResult expandedProductParsedResult = null;
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        String massagedText = getMassagedText(result);
        HashMap hashMap = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        int i = 0;
        while (i < massagedText.length()) {
            String findAIvalue = findAIvalue(i, massagedText);
            if (findAIvalue == null) {
                return expandedProductParsedResult;
            }
            int length = i + findAIvalue.length() + 2;
            String findValue = findValue(length, massagedText);
            int length2 = length + findValue.length();
            int hashCode = findAIvalue.hashCode();
            if (hashCode != 1570) {
                if (hashCode != 1572) {
                    if (hashCode != 1574) {
                        switch (hashCode) {
                            case 1536:
                                if (findAIvalue.equals("00")) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case 1537:
                                if (findAIvalue.equals("01")) {
                                    c = 1;
                                    break;
                                }
                                break;
                            default:
                                switch (hashCode) {
                                    case 1567:
                                        if (findAIvalue.equals("10")) {
                                            c = 2;
                                            break;
                                        }
                                        break;
                                    case 1568:
                                        if (findAIvalue.equals("11")) {
                                            c = 3;
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (hashCode) {
                                            case 1567966:
                                                if (findAIvalue.equals("3100")) {
                                                    c = 7;
                                                    break;
                                                }
                                                break;
                                            case 1567967:
                                                if (findAIvalue.equals("3101")) {
                                                    c = '\b';
                                                    break;
                                                }
                                                break;
                                            case 1567968:
                                                if (findAIvalue.equals("3102")) {
                                                    c = '\t';
                                                    break;
                                                }
                                                break;
                                            case 1567969:
                                                if (findAIvalue.equals("3103")) {
                                                    c = '\n';
                                                    break;
                                                }
                                                break;
                                            case 1567970:
                                                if (findAIvalue.equals("3104")) {
                                                    c = 11;
                                                    break;
                                                }
                                                break;
                                            case 1567971:
                                                if (findAIvalue.equals("3105")) {
                                                    c = '\f';
                                                    break;
                                                }
                                                break;
                                            case 1567972:
                                                if (findAIvalue.equals("3106")) {
                                                    c = '\r';
                                                    break;
                                                }
                                                break;
                                            case 1567973:
                                                if (findAIvalue.equals("3107")) {
                                                    c = 14;
                                                    break;
                                                }
                                                break;
                                            case 1567974:
                                                if (findAIvalue.equals("3108")) {
                                                    c = 15;
                                                    break;
                                                }
                                                break;
                                            case 1567975:
                                                if (findAIvalue.equals("3109")) {
                                                    c = 16;
                                                    break;
                                                }
                                                break;
                                            default:
                                                switch (hashCode) {
                                                    case 1568927:
                                                        if (findAIvalue.equals("3200")) {
                                                            c = 17;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568928:
                                                        if (findAIvalue.equals("3201")) {
                                                            c = 18;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568929:
                                                        if (findAIvalue.equals("3202")) {
                                                            c = 19;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568930:
                                                        if (findAIvalue.equals("3203")) {
                                                            c = 20;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568931:
                                                        if (findAIvalue.equals("3204")) {
                                                            c = 21;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568932:
                                                        if (findAIvalue.equals("3205")) {
                                                            c = 22;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568933:
                                                        if (findAIvalue.equals("3206")) {
                                                            c = 23;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568934:
                                                        if (findAIvalue.equals("3207")) {
                                                            c = 24;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568935:
                                                        if (findAIvalue.equals("3208")) {
                                                            c = 25;
                                                            break;
                                                        }
                                                        break;
                                                    case 1568936:
                                                        if (findAIvalue.equals("3209")) {
                                                            c = 26;
                                                            break;
                                                        }
                                                        break;
                                                    default:
                                                        switch (hashCode) {
                                                            case 1575716:
                                                                if (findAIvalue.equals("3920")) {
                                                                    c = 27;
                                                                    break;
                                                                }
                                                                break;
                                                            case 1575717:
                                                                if (findAIvalue.equals("3921")) {
                                                                    c = 28;
                                                                    break;
                                                                }
                                                                break;
                                                            case 1575718:
                                                                if (findAIvalue.equals("3922")) {
                                                                    c = 29;
                                                                    break;
                                                                }
                                                                break;
                                                            case 1575719:
                                                                if (findAIvalue.equals("3923")) {
                                                                    c = 30;
                                                                    break;
                                                                }
                                                                break;
                                                            default:
                                                                switch (hashCode) {
                                                                    case 1575747:
                                                                        if (findAIvalue.equals("3930")) {
                                                                            c = 31;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 1575748:
                                                                        if (findAIvalue.equals("3931")) {
                                                                            c = ' ';
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 1575749:
                                                                        if (findAIvalue.equals("3932")) {
                                                                            c = '!';
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 1575750:
                                                                        if (findAIvalue.equals("3933")) {
                                                                            c = Typography.quote;
                                                                            break;
                                                                        }
                                                                        break;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                        switch (c) {
                            case 0:
                                str2 = findValue;
                                str11 = str11;
                                str10 = str10;
                                break;
                            case 1:
                                str = findValue;
                                str11 = str11;
                                str10 = str10;
                                break;
                            case 2:
                                str3 = findValue;
                                str11 = str11;
                                str10 = str10;
                                break;
                            case 3:
                                str4 = findValue;
                                str11 = str11;
                                str10 = str10;
                                break;
                            case 4:
                                str5 = findValue;
                                str11 = str11;
                                str10 = str10;
                                break;
                            case 5:
                                str6 = findValue;
                                str11 = str11;
                                str10 = str10;
                                break;
                            case 6:
                                str7 = findValue;
                                str11 = str11;
                                str10 = str10;
                                break;
                            case 7:
                            case '\b':
                            case '\t':
                            case '\n':
                            case 11:
                            case '\f':
                            case '\r':
                            case 14:
                            case 15:
                            case 16:
                                str10 = findAIvalue.substring(3);
                                str9 = ExpandedProductParsedResult.KILOGRAM;
                                str11 = str11;
                                str8 = findValue;
                                break;
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                                str10 = findAIvalue.substring(3);
                                str9 = ExpandedProductParsedResult.POUND;
                                str11 = str11;
                                str8 = findValue;
                                break;
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                                str12 = findAIvalue.substring(3);
                                str11 = findValue;
                                str10 = str10;
                                break;
                            case 31:
                            case ' ':
                            case '!':
                            case '\"':
                                if (findValue.length() >= 4) {
                                    str11 = findValue.substring(3);
                                    String substring = findValue.substring(0, 3);
                                    str12 = findAIvalue.substring(3);
                                    str13 = substring;
                                    str10 = str10;
                                    break;
                                } else {
                                    return null;
                                }
                            default:
                                hashMap.put(findAIvalue, findValue);
                                str11 = str11;
                                str10 = str10;
                                break;
                        }
                        i = length2;
                        expandedProductParsedResult = null;
                    } else if (findAIvalue.equals("17")) {
                        c = 6;
                        switch (c) {
                        }
                        i = length2;
                        expandedProductParsedResult = null;
                    }
                } else if (findAIvalue.equals("15")) {
                    c = 5;
                    switch (c) {
                    }
                    i = length2;
                    expandedProductParsedResult = null;
                }
            } else if (findAIvalue.equals("13")) {
                c = 4;
                switch (c) {
                }
                i = length2;
                expandedProductParsedResult = null;
            }
            c = 65535;
            switch (c) {
            }
            i = length2;
            expandedProductParsedResult = null;
        }
        return new ExpandedProductParsedResult(massagedText, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, hashMap);
    }

    private static String findAIvalue(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String substring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == '(') {
                if (findAIvalue(i2, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
