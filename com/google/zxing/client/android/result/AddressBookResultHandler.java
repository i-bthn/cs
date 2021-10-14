package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class AddressBookResultHandler extends ResultHandler {
    private static final int[] BUTTON_TEXTS = {R.string.button_add_contact, R.string.button_show_map, R.string.button_dial, R.string.button_email};
    private static final DateFormat[] DATE_FORMATS = {new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH), new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH), new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)};
    private int buttonCount;
    private final boolean[] fields = new boolean[4];

    static {
        for (DateFormat dateFormat : DATE_FORMATS) {
            dateFormat.setLenient(false);
        }
    }

    private int mapIndexToAction(int i) {
        if (i < this.buttonCount) {
            int i2 = -1;
            for (int i3 = 0; i3 < 4; i3++) {
                if (this.fields[i3]) {
                    i2++;
                }
                if (i2 == i) {
                    return i3;
                }
            }
        }
        return -1;
    }

    public AddressBookResultHandler(Activity activity, ParsedResult parsedResult) {
        super(activity, parsedResult);
        AddressBookParsedResult addressBookParsedResult = (AddressBookParsedResult) parsedResult;
        String[] addresses = addressBookParsedResult.getAddresses();
        String[] phoneNumbers = addressBookParsedResult.getPhoneNumbers();
        String[] emails = addressBookParsedResult.getEmails();
        boolean[] zArr = this.fields;
        zArr[0] = true;
        zArr[1] = addresses != null && addresses.length > 0 && addresses[0] != null && !addresses[0].isEmpty();
        this.fields[2] = phoneNumbers != null && phoneNumbers.length > 0;
        this.fields[3] = emails != null && emails.length > 0;
        this.buttonCount = 0;
        for (int i = 0; i < 4; i++) {
            if (this.fields[i]) {
                this.buttonCount++;
            }
        }
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonCount() {
        return this.buttonCount;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonText(int i) {
        return BUTTON_TEXTS[mapIndexToAction(i)];
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i) {
        AddressBookParsedResult addressBookParsedResult = (AddressBookParsedResult) getResult();
        String[] addresses = addressBookParsedResult.getAddresses();
        String str = (addresses == null || addresses.length < 1) ? null : addresses[0];
        String[] addressTypes = addressBookParsedResult.getAddressTypes();
        String str2 = (addressTypes == null || addressTypes.length < 1) ? null : addressTypes[0];
        switch (mapIndexToAction(i)) {
            case 0:
                addContact(addressBookParsedResult.getNames(), addressBookParsedResult.getNicknames(), addressBookParsedResult.getPronunciation(), addressBookParsedResult.getPhoneNumbers(), addressBookParsedResult.getPhoneTypes(), addressBookParsedResult.getEmails(), addressBookParsedResult.getEmailTypes(), addressBookParsedResult.getNote(), addressBookParsedResult.getInstantMessenger(), str, str2, addressBookParsedResult.getOrg(), addressBookParsedResult.getTitle(), addressBookParsedResult.getURLs(), addressBookParsedResult.getBirthday(), addressBookParsedResult.getGeo());
                return;
            case 1:
                searchMap(str);
                return;
            case 2:
                dialPhone(addressBookParsedResult.getPhoneNumbers()[0]);
                return;
            case 3:
                sendEmail(addressBookParsedResult.getEmails(), null, null, null, null);
                return;
            default:
                return;
        }
    }

    private static Date parseDate(String str) {
        DateFormat[] dateFormatArr;
        for (DateFormat dateFormat : DATE_FORMATS) {
            try {
                return dateFormat.parse(str);
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public CharSequence getDisplayContents() {
        Date parseDate;
        AddressBookParsedResult addressBookParsedResult = (AddressBookParsedResult) getResult();
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(addressBookParsedResult.getNames(), sb);
        int length = sb.length();
        String pronunciation = addressBookParsedResult.getPronunciation();
        if (pronunciation != null && !pronunciation.isEmpty()) {
            sb.append("\n(");
            sb.append(pronunciation);
            sb.append(')');
        }
        ParsedResult.maybeAppend(addressBookParsedResult.getTitle(), sb);
        ParsedResult.maybeAppend(addressBookParsedResult.getOrg(), sb);
        ParsedResult.maybeAppend(addressBookParsedResult.getAddresses(), sb);
        String[] phoneNumbers = addressBookParsedResult.getPhoneNumbers();
        if (phoneNumbers != null) {
            for (String str : phoneNumbers) {
                if (str != null) {
                    ParsedResult.maybeAppend(PhoneNumberUtils.formatNumber(str), sb);
                }
            }
        }
        ParsedResult.maybeAppend(addressBookParsedResult.getEmails(), sb);
        ParsedResult.maybeAppend(addressBookParsedResult.getURLs(), sb);
        String birthday = addressBookParsedResult.getBirthday();
        if (!(birthday == null || birthday.isEmpty() || (parseDate = parseDate(birthday)) == null)) {
            ParsedResult.maybeAppend(DateFormat.getDateInstance(2).format(Long.valueOf(parseDate.getTime())), sb);
        }
        ParsedResult.maybeAppend(addressBookParsedResult.getNote(), sb);
        if (length <= 0) {
            return sb.toString();
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new StyleSpan(1), 0, length, 0);
        return spannableString;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return R.string.result_address_book;
    }
}
