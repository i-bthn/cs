package org.apache.cordova.globalization;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Globalization extends CordovaPlugin {
    public static final String CURRENCY = "currency";
    public static final String CURRENCYCODE = "currencyCode";
    public static final String DATE = "date";
    public static final String DATESTRING = "dateString";
    public static final String DATETOSTRING = "dateToString";
    public static final String DAYS = "days";
    public static final String FORMATLENGTH = "formatLength";
    public static final String FULL = "full";
    public static final String GETCURRENCYPATTERN = "getCurrencyPattern";
    public static final String GETDATENAMES = "getDateNames";
    public static final String GETDATEPATTERN = "getDatePattern";
    public static final String GETFIRSTDAYOFWEEK = "getFirstDayOfWeek";
    public static final String GETLOCALENAME = "getLocaleName";
    public static final String GETNUMBERPATTERN = "getNumberPattern";
    public static final String GETPREFERREDLANGUAGE = "getPreferredLanguage";
    public static final String ISDAYLIGHTSAVINGSTIME = "isDayLightSavingsTime";
    public static final String ITEM = "item";
    public static final String LONG = "long";
    public static final String MEDIUM = "medium";
    public static final String MONTHS = "months";
    public static final String NARROW = "narrow";
    public static final String NUMBER = "number";
    public static final String NUMBERSTRING = "numberString";
    public static final String NUMBERTOSTRING = "numberToString";
    public static final String OPTIONS = "options";
    public static final String PERCENT = "percent";
    public static final String SELECTOR = "selector";
    public static final String STRINGTODATE = "stringToDate";
    public static final String STRINGTONUMBER = "stringToNumber";
    public static final String TIME = "time";
    public static final String TYPE = "type";
    public static final String WIDE = "wide";

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) {
        JSONObject jSONObject;
        new JSONObject();
        try {
            if (str.equals(GETLOCALENAME)) {
                jSONObject = getLocaleName();
            } else if (str.equals(GETPREFERREDLANGUAGE)) {
                jSONObject = getPreferredLanguage();
            } else if (str.equalsIgnoreCase(DATETOSTRING)) {
                jSONObject = getDateToString(jSONArray);
            } else if (str.equalsIgnoreCase(STRINGTODATE)) {
                jSONObject = getStringtoDate(jSONArray);
            } else if (str.equalsIgnoreCase(GETDATEPATTERN)) {
                jSONObject = getDatePattern(jSONArray);
            } else if (str.equalsIgnoreCase(GETDATENAMES)) {
                if (Build.VERSION.SDK_INT >= 9) {
                    jSONObject = getDateNames(jSONArray);
                } else {
                    throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
                }
            } else if (str.equalsIgnoreCase(ISDAYLIGHTSAVINGSTIME)) {
                jSONObject = getIsDayLightSavingsTime(jSONArray);
            } else if (str.equalsIgnoreCase(GETFIRSTDAYOFWEEK)) {
                jSONObject = getFirstDayOfWeek(jSONArray);
            } else if (str.equalsIgnoreCase(NUMBERTOSTRING)) {
                jSONObject = getNumberToString(jSONArray);
            } else if (str.equalsIgnoreCase(STRINGTONUMBER)) {
                jSONObject = getStringToNumber(jSONArray);
            } else if (str.equalsIgnoreCase(GETNUMBERPATTERN)) {
                jSONObject = getNumberPattern(jSONArray);
            } else if (!str.equalsIgnoreCase(GETCURRENCYPATTERN)) {
                return false;
            } else {
                jSONObject = getCurrencyPattern(jSONArray);
            }
            callbackContext.success(jSONObject);
            return true;
        } catch (GlobalizationError e) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.toJson()));
            return true;
        } catch (Exception unused) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
            return true;
        }
    }

    private String toBcp47Language(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String variant = locale.getVariant();
        if (language.equals("no") && country.equals("NO") && variant.equals("NY")) {
            language = "nn";
            country = "NO";
            variant = "";
        }
        if (language.isEmpty() || !language.matches("\\p{Alpha}{2,8}")) {
            language = "und";
        } else if (language.equals("iw")) {
            language = "he";
        } else if (language.equals("in")) {
            language = "id";
        } else if (language.equals("ji")) {
            language = "yi";
        }
        if (!country.matches("\\p{Alpha}{2}|\\p{Digit}{3}")) {
            country = "";
        }
        if (!variant.matches("\\p{Alnum}{5,8}|\\p{Digit}\\p{Alnum}{3}")) {
            variant = "";
        }
        StringBuilder sb = new StringBuilder(language);
        if (!country.isEmpty()) {
            sb.append('-');
            sb.append(country);
        }
        if (!variant.isEmpty()) {
            sb.append('-');
            sb.append(variant);
        }
        return sb.toString();
    }

    private JSONObject getLocaleName() throws GlobalizationError {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("value", toBcp47Language(Locale.getDefault()));
            return jSONObject;
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getPreferredLanguage() throws GlobalizationError {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("value", toBcp47Language(Locale.getDefault()));
            return jSONObject;
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getDateToString(JSONArray jSONArray) throws GlobalizationError {
        try {
            return new JSONObject().put("value", new SimpleDateFormat(getDatePattern(jSONArray).getString("pattern")).format(new Date(((Long) jSONArray.getJSONObject(0).get(DATE)).longValue())));
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.FORMATTING_ERROR);
        }
    }

    private JSONObject getStringtoDate(JSONArray jSONArray) throws GlobalizationError {
        JSONObject jSONObject = new JSONObject();
        try {
            Date parse = new SimpleDateFormat(getDatePattern(jSONArray).getString("pattern")).parse(jSONArray.getJSONObject(0).get(DATESTRING).toString());
            Time time = new Time();
            time.set(parse.getTime());
            jSONObject.put("year", time.year);
            jSONObject.put("month", time.month);
            jSONObject.put("day", time.monthDay);
            jSONObject.put("hour", time.hour);
            jSONObject.put("minute", time.minute);
            jSONObject.put("second", time.second);
            jSONObject.put("millisecond", (Object) 0L);
            return jSONObject;
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.PARSING_ERROR);
        }
    }

    private JSONObject getDatePattern(JSONArray jSONArray) throws GlobalizationError {
        JSONObject jSONObject = new JSONObject();
        try {
            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateFormat(this.cordova.getActivity());
            SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) DateFormat.getTimeFormat(this.cordova.getActivity());
            String str = simpleDateFormat.toLocalizedPattern() + " " + simpleDateFormat2.toLocalizedPattern();
            if (jSONArray.getJSONObject(0).has(OPTIONS)) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(0).getJSONObject(OPTIONS);
                if (!jSONObject2.isNull(FORMATLENGTH)) {
                    String string = jSONObject2.getString(FORMATLENGTH);
                    if (string.equalsIgnoreCase("medium")) {
                        simpleDateFormat = (SimpleDateFormat) DateFormat.getMediumDateFormat(this.cordova.getActivity());
                    } else if (string.equalsIgnoreCase(LONG) || string.equalsIgnoreCase(FULL)) {
                        simpleDateFormat = (SimpleDateFormat) DateFormat.getLongDateFormat(this.cordova.getActivity());
                    }
                }
                str = simpleDateFormat.toLocalizedPattern() + " " + simpleDateFormat2.toLocalizedPattern();
                if (!jSONObject2.isNull(SELECTOR)) {
                    String string2 = jSONObject2.getString(SELECTOR);
                    if (string2.equalsIgnoreCase(DATE)) {
                        str = simpleDateFormat.toLocalizedPattern();
                    } else if (string2.equalsIgnoreCase(TIME)) {
                        str = simpleDateFormat2.toLocalizedPattern();
                    }
                }
            }
            TimeZone timeZone = TimeZone.getTimeZone(Time.getCurrentTimezone());
            jSONObject.put("pattern", str);
            jSONObject.put("timezone", timeZone.getDisplayName(timeZone.inDaylightTime(Calendar.getInstance().getTime()), 0));
            jSONObject.put("iana_timezone", timeZone.getID());
            jSONObject.put("utc_offset", timeZone.getRawOffset() / 1000);
            jSONObject.put("dst_offset", timeZone.getDSTSavings() / 1000);
            return jSONObject;
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.PATTERN_ERROR);
        }
    }

    @TargetApi(9)
    private JSONObject getDateNames(JSONArray jSONArray) throws GlobalizationError {
        int i;
        int i2;
        final Map<String, Integer> map;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray2 = new JSONArray();
        ArrayList arrayList = new ArrayList();
        try {
            if (jSONArray.getJSONObject(0).length() > 0) {
                i2 = (((JSONObject) jSONArray.getJSONObject(0).get(OPTIONS)).isNull("type") || !((String) ((JSONObject) jSONArray.getJSONObject(0).get(OPTIONS)).get("type")).equalsIgnoreCase(NARROW)) ? 0 : 1;
                i = (((JSONObject) jSONArray.getJSONObject(0).get(OPTIONS)).isNull(ITEM) || !((String) ((JSONObject) jSONArray.getJSONObject(0).get(OPTIONS)).get(ITEM)).equalsIgnoreCase(DAYS)) ? 0 : 10;
            } else {
                i = 0;
                i2 = 0;
            }
            int i3 = i + i2;
            if (i3 == 1) {
                map = Calendar.getInstance().getDisplayNames(2, 1, Locale.getDefault());
            } else if (i3 == 10) {
                map = Calendar.getInstance().getDisplayNames(7, 2, Locale.getDefault());
            } else if (i3 == 11) {
                map = Calendar.getInstance().getDisplayNames(7, 1, Locale.getDefault());
            } else {
                map = Calendar.getInstance().getDisplayNames(2, 2, Locale.getDefault());
            }
            for (String str : map.keySet()) {
                arrayList.add(str);
            }
            Collections.sort(arrayList, new Comparator<String>() {
                /* class org.apache.cordova.globalization.Globalization.AnonymousClass1 */

                public int compare(String str, String str2) {
                    return ((Integer) map.get(str)).compareTo((Integer) map.get(str2));
                }
            });
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                jSONArray2.put(arrayList.get(i4));
            }
            return jSONObject.put("value", jSONArray2);
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getIsDayLightSavingsTime(JSONArray jSONArray) throws GlobalizationError {
        try {
            return new JSONObject().put("dst", TimeZone.getTimeZone(Time.getCurrentTimezone()).inDaylightTime(new Date(((Long) jSONArray.getJSONObject(0).get(DATE)).longValue())));
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getFirstDayOfWeek(JSONArray jSONArray) throws GlobalizationError {
        try {
            return new JSONObject().put("value", Calendar.getInstance(Locale.getDefault()).getFirstDayOfWeek());
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getNumberToString(JSONArray jSONArray) throws GlobalizationError {
        try {
            return new JSONObject().put("value", getNumberFormatInstance(jSONArray).format(jSONArray.getJSONObject(0).get(NUMBER)));
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.FORMATTING_ERROR);
        }
    }

    private JSONObject getStringToNumber(JSONArray jSONArray) throws GlobalizationError {
        try {
            return new JSONObject().put("value", getNumberFormatInstance(jSONArray).parse((String) jSONArray.getJSONObject(0).get(NUMBERSTRING)));
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.PARSING_ERROR);
        }
    }

    private JSONObject getNumberPattern(JSONArray jSONArray) throws GlobalizationError {
        JSONObject jSONObject = new JSONObject();
        try {
            DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
            String valueOf = String.valueOf(decimalFormat.getDecimalFormatSymbols().getDecimalSeparator());
            if (jSONArray.getJSONObject(0).length() > 0 && !((JSONObject) jSONArray.getJSONObject(0).get(OPTIONS)).isNull("type")) {
                String str = (String) ((JSONObject) jSONArray.getJSONObject(0).get(OPTIONS)).get("type");
                if (str.equalsIgnoreCase("currency")) {
                    decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.getDefault());
                    valueOf = decimalFormat.getDecimalFormatSymbols().getCurrencySymbol();
                } else if (str.equalsIgnoreCase(PERCENT)) {
                    decimalFormat = (DecimalFormat) DecimalFormat.getPercentInstance(Locale.getDefault());
                    valueOf = String.valueOf(decimalFormat.getDecimalFormatSymbols().getPercent());
                }
            }
            jSONObject.put("pattern", decimalFormat.toPattern());
            jSONObject.put("symbol", valueOf);
            jSONObject.put("fraction", decimalFormat.getMinimumFractionDigits());
            jSONObject.put("rounding", (Object) 0);
            jSONObject.put("positive", decimalFormat.getPositivePrefix());
            jSONObject.put("negative", decimalFormat.getNegativePrefix());
            jSONObject.put("decimal", String.valueOf(decimalFormat.getDecimalFormatSymbols().getDecimalSeparator()));
            jSONObject.put("grouping", String.valueOf(decimalFormat.getDecimalFormatSymbols().getGroupingSeparator()));
            return jSONObject;
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.PATTERN_ERROR);
        }
    }

    private JSONObject getCurrencyPattern(JSONArray jSONArray) throws GlobalizationError {
        JSONObject jSONObject = new JSONObject();
        try {
            String string = jSONArray.getJSONObject(0).getString(CURRENCYCODE);
            DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.getDefault());
            Currency instance = Currency.getInstance(string);
            decimalFormat.setCurrency(instance);
            jSONObject.put("pattern", decimalFormat.toPattern());
            jSONObject.put("code", instance.getCurrencyCode());
            jSONObject.put("fraction", decimalFormat.getMinimumFractionDigits());
            jSONObject.put("rounding", (Object) 0);
            jSONObject.put("decimal", String.valueOf(decimalFormat.getDecimalFormatSymbols().getDecimalSeparator()));
            jSONObject.put("grouping", String.valueOf(decimalFormat.getDecimalFormatSymbols().getGroupingSeparator()));
            return jSONObject;
        } catch (Exception unused) {
            throw new GlobalizationError(GlobalizationError.FORMATTING_ERROR);
        }
    }

    private DecimalFormat getNumberFormatInstance(JSONArray jSONArray) throws JSONException {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
        try {
            if (jSONArray.getJSONObject(0).length() <= 1 || ((JSONObject) jSONArray.getJSONObject(0).get(OPTIONS)).isNull("type")) {
                return decimalFormat;
            }
            String str = (String) ((JSONObject) jSONArray.getJSONObject(0).get(OPTIONS)).get("type");
            if (str.equalsIgnoreCase("currency")) {
                return (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.getDefault());
            }
            return str.equalsIgnoreCase(PERCENT) ? (DecimalFormat) DecimalFormat.getPercentInstance(Locale.getDefault()) : decimalFormat;
        } catch (JSONException unused) {
            return decimalFormat;
        }
    }
}
