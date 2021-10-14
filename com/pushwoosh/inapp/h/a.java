package com.pushwoosh.inapp.h;

import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.pushwoosh.inbox.ui.model.customizing.formatter.InboxDateFormatterKt;
import com.pushwoosh.internal.utils.PWLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class a {
    private static final HashMap<String, String> a = new HashMap<>();

    private static String a(String str) {
        char[] charArray = str.toCharArray();
        boolean z = true;
        for (int i = 0; i < charArray.length; i++) {
            if (z && Character.isLetter(charArray[i])) {
                charArray[i] = Character.toUpperCase(charArray[i]);
            }
            z = Character.isWhitespace(charArray[i]);
        }
        return String.valueOf(charArray);
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return "";
        }
        try {
            return "lowercase".equals(str2) ? str.toLowerCase() : "UPPERCASE".equals(str2) ? str.toUpperCase() : "CapitalizeAllFirst".equals(str2) ? a(str) : "CapitalizeFirst".equals(str2) ? b(str) : "cent".equals(str2) ? e(str) : "dollar".equals(str2) ? o(str) : "comma".equals(str2) ? f(str) : "euro".equals(str2) ? p(str) : "jpy".equals(str2) ? q(str) : "lira".equals(str2) ? r(str) : "M-d-y".equals(str2) ? g(str) : "m-d-y".equals(str2) ? h(str) : "M d y".equals(str2) ? i(str) : "M d Y".equals(str2) ? j(str) : "l".equals(str2) ? k(str) : "M d".equals(str2) ? l(str) : "H:i".equals(str2) ? m(str) : "m-d-y H:i".equals(str2) ? n(str) : str;
        } catch (Exception e) {
            PWLog.exception(e);
            return str;
        }
    }

    private static Map<String, String> a() {
        if (a.isEmpty()) {
            a.put("AD", "Andorra");
            a.put("AE", "United Arab Emirates");
            a.put("AF", "Afghanistan");
            a.put("AG", "Antigua and Barbuda");
            a.put("AI", "Anguilla");
            a.put("AL", "Albania");
            a.put("AM", "Armenia");
            a.put("AO", "Angola");
            a.put("AP", "Asia/Pacific Region");
            a.put("AQ", "Antarctica");
            a.put("AR", "Argentina");
            a.put("AS", "American Samoa");
            a.put("AT", "Austria");
            a.put("AU", "Australia");
            a.put("AW", "Aruba");
            a.put("AX", "Aland Islands");
            a.put("AZ", "Azerbaijan");
            a.put("BA", "Bosnia and Herzegovina");
            a.put("BB", "Barbados");
            a.put("BD", "Bangladesh");
            a.put("BE", "Belgium");
            a.put("BF", "Burkina Faso");
            a.put("BG", "Bulgaria");
            a.put("BH", "Bahrain");
            a.put("BI", "Burundi");
            a.put("BJ", "Benin");
            a.put("BL", "Saint Bartelemey");
            a.put("BM", "Bermuda");
            a.put("BN", "Brunei Darussalam");
            a.put("BO", "Bolivia");
            a.put("BQ", "Bonaire, Saint Eustatius and Saba");
            a.put("BR", "Brazil");
            a.put("BS", "Bahamas");
            a.put("BT", "Bhutan");
            a.put("BV", "Bouvet Island");
            a.put("BW", "Botswana");
            a.put("BY", "Belarus");
            a.put("BZ", "Belize");
            a.put("CA", "Canada");
            a.put("CC", "Cocos (Keeling) Islands");
            a.put("CD", "Congo, The Democratic Republic of the");
            a.put("CF", "Central African Republic");
            a.put("CG", "Congo");
            a.put("CH", "Switzerland");
            a.put("CI", "Cote d'Ivoire");
            a.put("CK", "Cook Islands");
            a.put("CL", "Chile");
            a.put("CM", "Cameroon");
            a.put("CN", "China");
            a.put("CO", "Colombia");
            a.put("CR", "Costa Rica");
            a.put("CU", "Cuba");
            a.put("CV", "Cape Verde");
            a.put("CW", "Curacao");
            a.put("CX", "Christmas Island");
            a.put("CY", "Cyprus");
            a.put("CZ", "Czech Republic");
            a.put("DE", "Germany");
            a.put("DJ", "Djibouti");
            a.put("DK", "Denmark");
            a.put("DM", "Dominica");
            a.put("DO", "Dominican Republic");
            a.put("DZ", "Algeria");
            a.put("EC", "Ecuador");
            a.put("EE", "Estonia");
            a.put("EG", "Egypt");
            a.put("EH", "Western Sahara");
            a.put("ER", "Eritrea");
            a.put("ES", "Spain");
            a.put("ET", "Ethiopia");
            a.put("EU", "Europe");
            a.put("FI", "Finland");
            a.put("FJ", "Fiji");
            a.put("FK", "Falkland Islands (Malvinas)");
            a.put("FM", "Micronesia, Federated States of");
            a.put("FO", "Faroe Islands");
            a.put("FR", "France");
            a.put("GA", "Gabon");
            a.put("GB", "United Kingdom");
            a.put("GD", "Grenada");
            a.put("GE", "Georgia");
            a.put("GF", "French Guiana");
            a.put("GG", "Guernsey");
            a.put("GH", "Ghana");
            a.put("GI", "Gibraltar");
            a.put("GL", "Greenland");
            a.put("GM", "Gambia");
            a.put("GN", "Guinea");
            a.put("GP", "Guadeloupe");
            a.put("GQ", "Equatorial Guinea");
            a.put("GR", "Greece");
            a.put("GS", "South Georgia and the South Sandwich Islands");
            a.put("GT", "Guatemala");
            a.put("GU", "Guam");
            a.put("GW", "Guinea-Bissau");
            a.put("GY", "Guyana");
            a.put("HK", "Hong Kong");
            a.put("HM", "Heard Island and McDonald Islands");
            a.put("HN", "Honduras");
            a.put("HR", "Croatia");
            a.put("HT", "Haiti");
            a.put("HU", "Hungary");
            a.put("ID", "Indonesia");
            a.put("IE", "Ireland");
            a.put("IL", "Israel");
            a.put("IM", "Isle of Man");
            a.put("IN", "India");
            a.put("IO", "British Indian Ocean Territory");
            a.put("IQ", "Iraq");
            a.put("IR", "Iran, Islamic Republic of");
            a.put("IS", "Iceland");
            a.put("IT", "Italy");
            a.put("JE", "Jersey");
            a.put("JM", "Jamaica");
            a.put("JO", "Jordan");
            a.put("JP", "Japan");
            a.put("KE", "Kenya");
            a.put(ExpandedProductParsedResult.KILOGRAM, "Kyrgyzstan");
            a.put("KH", "Cambodia");
            a.put("KI", "Kiribati");
            a.put("KM", "Comoros");
            a.put("KN", "Saint Kitts and Nevis");
            a.put("KP", "Korea, Democratic People's Republic of");
            a.put("KR", "Korea, Republic of");
            a.put("KW", "Kuwait");
            a.put("KY", "Cayman Islands");
            a.put("KZ", "Kazakhstan");
            a.put("LA", "Lao People's Democratic Republic");
            a.put(ExpandedProductParsedResult.POUND, "Lebanon");
            a.put("LC", "Saint Lucia");
            a.put("LI", "Liechtenstein");
            a.put("LK", "Sri Lanka");
            a.put("LR", "Liberia");
            a.put("LS", "Lesotho");
            a.put("LT", "Lithuania");
            a.put("LU", "Luxembourg");
            a.put("LV", "Latvia");
            a.put("LY", "Libyan Arab Jamahiriya");
            a.put("MA", "Morocco");
            a.put("MC", "Monaco");
            a.put("MD", "Moldova, Republic of");
            a.put("ME", "Montenegro");
            a.put("MF", "Saint Martin");
            a.put("MG", "Madagascar");
            a.put("MH", "Marshall Islands");
            a.put("MK", "Macedonia");
            a.put("ML", "Mali");
            a.put("MM", "Myanmar");
            a.put("MN", "Mongolia");
            a.put("MO", "Macao");
            a.put("MP", "Northern Mariana Islands");
            a.put("MQ", "Martinique");
            a.put("MR", "Mauritania");
            a.put("MS", "Montserrat");
            a.put("MT", "Malta");
            a.put("MU", "Mauritius");
            a.put("MV", "Maldives");
            a.put("MW", "Malawi");
            a.put("MX", "Mexico");
            a.put("MY", "Malaysia");
            a.put("MZ", "Mozambique");
            a.put("NA", "Namibia");
            a.put("NC", "New Caledonia");
            a.put("NE", "Niger");
            a.put("NF", "Norfolk Island");
            a.put("NG", "Nigeria");
            a.put("NI", "Nicaragua");
            a.put("NL", "Netherlands");
            a.put("NO", "Norway");
            a.put("NP", "Nepal");
            a.put("NR", "Nauru");
            a.put("NU", "Niue");
            a.put("NZ", "New Zealand");
            a.put("OM", "Oman");
            a.put("PA", "Panama");
            a.put("PE", "Peru");
            a.put("PF", "French Polynesia");
            a.put("PG", "Papua New Guinea");
            a.put("PH", "Philippines");
            a.put("PK", "Pakistan");
            a.put("PL", "Poland");
            a.put("PM", "Saint Pierre and Miquelon");
            a.put("PN", "Pitcairn");
            a.put("PR", "Puerto Rico");
            a.put("PS", "Palestinian Territory");
            a.put("PT", "Portugal");
            a.put("PW", "Palau");
            a.put("PY", "Paraguay");
            a.put("QA", "Qatar");
            a.put("RE", "Reunion");
            a.put("RO", "Romania");
            a.put("RS", "Serbia");
            a.put("RU", "Russian Federation");
            a.put("RW", "Rwanda");
            a.put("SA", "Saudi Arabia");
            a.put("SB", "Solomon Islands");
            a.put("SC", "Seychelles");
            a.put("SD", "Sudan");
            a.put("SE", "Sweden");
            a.put("SG", "Singapore");
            a.put("SH", "Saint Helena");
            a.put("SI", "Slovenia");
            a.put("SJ", "Svalbard and Jan Mayen");
            a.put("SK", "Slovakia");
            a.put("SL", "Sierra Leone");
            a.put("SM", "San Marino");
            a.put("SN", "Senegal");
            a.put("SO", "Somalia");
            a.put("SR", "Suriname");
            a.put("SS", "South Sudan");
            a.put("ST", "Sao Tome and Principe");
            a.put("SV", "El Salvador");
            a.put("SX", "Sint Maarten");
            a.put("SY", "Syrian Arab Republic");
            a.put("SZ", "Swaziland");
            a.put("TC", "Turks and Caicos Islands");
            a.put("TD", "Chad");
            a.put("TF", "French Southern Territories");
            a.put("TG", "Togo");
            a.put("TH", "Thailand");
            a.put("TJ", "Tajikistan");
            a.put("TK", "Tokelau");
            a.put("TL", "Timor-Leste");
            a.put("TM", "Turkmenistan");
            a.put("TN", "Tunisia");
            a.put("TO", "Tonga");
            a.put("TR", "Turkey");
            a.put("TT", "Trinidad and Tobago");
            a.put("TV", "Tuvalu");
            a.put("TW", "Taiwan");
            a.put("TZ", "Tanzania, United Republic of");
            a.put("UA", "Ukraine");
            a.put("UG", "Uganda");
            a.put("UM", "United States Minor Outlying Islands");
            a.put("US", "United States");
            a.put("UY", "Uruguay");
            a.put("UZ", "Uzbekistan");
            a.put("VA", "Holy See (Vatican City State)");
            a.put("VC", "Saint Vincent and the Grenadines");
            a.put("VE", "Venezuela");
            a.put("VG", "Virgin Islands, British");
            a.put("VI", "Virgin Islands, U.S.");
            a.put("VN", "Vietnam");
            a.put("VU", "Vanuatu");
            a.put("WF", "Wallis and Futuna");
            a.put("WS", "Samoa");
            a.put("YE", "Yemen");
            a.put("YT", "Mayotte");
            a.put("ZA", "South Africa");
            a.put("ZM", "Zambia");
            a.put("ZW", "Zimbabwe");
        }
        return a;
    }

    public static void a(Map<String, Object> map) {
        try {
            if (map.containsKey("Country")) {
                String d = d(map.get("Country").toString());
                if (d != null) {
                    map.put("Country", d);
                } else {
                    map.remove("Country");
                }
            }
            if (map.containsKey("City")) {
                map.put("City", c(map.get("City").toString()));
            }
        } catch (Exception e) {
            PWLog.error("Failed converting geoTags", e);
        }
    }

    private static String b(String str) {
        if (str.length() == 0) {
            return "";
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    private static String c(String str) {
        String[] split = str.split(", ");
        return split[split.length - 1];
    }

    private static String d(String str) {
        String upperCase = str.toUpperCase();
        Map<String, String> a2 = a();
        if (a2.containsKey(upperCase)) {
            return a2.get(upperCase);
        }
        return null;
    }

    private static String e(String str) {
        if (str.length() == 0) {
            return "$.00";
        }
        if (str.length() == 1) {
            str = "0" + str;
        }
        String substring = str.substring(str.length() - 2, str.length());
        return "$" + str.substring(0, str.length() - 2) + "." + substring;
    }

    private static String f(String str) {
        if (str.length() == 0) {
            return "";
        }
        String str2 = "";
        int length = str.length();
        while (length > 0) {
            length -= 3;
            str2 = str.substring(Math.max(length, 0), length + 3) + "," + str2;
        }
        return str2.substring(0, str2.length() - 1);
    }

    private static String g(String str) {
        Date date = new Date(Long.valueOf(Long.parseLong(str)).longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        return simpleDateFormat.format(date);
    }

    private static String h(String str) {
        Date date = new Date(Long.valueOf(Long.parseLong(str)).longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        return simpleDateFormat.format(date);
    }

    private static String i(String str) {
        Date date = new Date(Long.valueOf(Long.parseLong(str)).longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd yy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        return simpleDateFormat.format(date);
    }

    private static String j(String str) {
        Date date = new Date(Long.valueOf(Long.parseLong(str)).longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        return simpleDateFormat.format(date);
    }

    private static String k(String str) {
        Date date = new Date(Long.valueOf(Long.parseLong(str)).longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        return simpleDateFormat.format(date);
    }

    private static String l(String str) {
        Date date = new Date(Long.valueOf(Long.parseLong(str)).longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(InboxDateFormatterKt.DEFAULT_DATE_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        return simpleDateFormat.format(date);
    }

    private static String m(String str) {
        Date date = new Date(Long.valueOf(Long.parseLong(str)).longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        return simpleDateFormat.format(date);
    }

    private static String n(String str) {
        Date date = new Date(Long.valueOf(Long.parseLong(str)).longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy hh:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        return simpleDateFormat.format(date);
    }

    private static String o(String str) {
        if (str.length() == 0) {
            return "$0";
        }
        return "$" + f(str);
    }

    private static String p(String str) {
        if (str.length() == 0) {
            return "€0";
        }
        return "€" + f(str);
    }

    private static String q(String str) {
        if (str.length() == 0) {
            return "¥0";
        }
        return "¥" + f(str);
    }

    private static String r(String str) {
        if (str.length() == 0) {
            return "₤0";
        }
        return "₤" + f(str);
    }
}
