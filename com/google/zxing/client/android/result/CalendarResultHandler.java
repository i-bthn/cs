package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.client.result.CalendarParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.DateFormat;
import java.util.Date;

public final class CalendarResultHandler extends ResultHandler {
    private static final String TAG = "CalendarResultHandler";
    private static final int[] buttons = {R.string.button_add_calendar};

    public CalendarResultHandler(Activity activity, ParsedResult parsedResult) {
        super(activity, parsedResult);
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonCount() {
        return buttons.length;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonText(int i) {
        return buttons[i];
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i) {
        String str;
        if (i == 0) {
            CalendarParsedResult calendarParsedResult = (CalendarParsedResult) getResult();
            String description = calendarParsedResult.getDescription();
            String organizer = calendarParsedResult.getOrganizer();
            if (organizer == null) {
                str = description;
            } else if (description == null) {
                str = organizer;
            } else {
                str = description + '\n' + organizer;
            }
            addCalendarEvent(calendarParsedResult.getSummary(), calendarParsedResult.getStart(), calendarParsedResult.isStartAllDay(), calendarParsedResult.getEnd(), calendarParsedResult.getLocation(), str, calendarParsedResult.getAttendees());
        }
    }

    private void addCalendarEvent(String str, Date date, boolean z, Date date2, String str2, String str3, String[] strArr) {
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setType("vnd.android.cursor.item/event");
        long time = date.getTime();
        intent.putExtra("beginTime", time);
        if (z) {
            intent.putExtra("allDay", true);
        }
        if (date2 != null) {
            time = date2.getTime();
        } else if (z) {
            time += 86400000;
        }
        intent.putExtra("endTime", time);
        intent.putExtra("title", str);
        intent.putExtra("eventLocation", str2);
        intent.putExtra("description", str3);
        if (strArr != null) {
            intent.putExtra("android.intent.extra.EMAIL", strArr);
        }
        try {
            rawLaunchIntent(intent);
        } catch (ActivityNotFoundException unused) {
            Log.w(TAG, "No calendar app available that responds to android.intent.action.INSERT");
            intent.setAction("android.intent.action.EDIT");
            launchIntent(intent);
        }
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public CharSequence getDisplayContents() {
        CalendarParsedResult calendarParsedResult = (CalendarParsedResult) getResult();
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(calendarParsedResult.getSummary(), sb);
        Date start = calendarParsedResult.getStart();
        ParsedResult.maybeAppend(format(calendarParsedResult.isStartAllDay(), start), sb);
        Date end = calendarParsedResult.getEnd();
        if (end != null) {
            ParsedResult.maybeAppend(format(calendarParsedResult.isEndAllDay(), (!calendarParsedResult.isEndAllDay() || start.equals(end)) ? end : new Date(end.getTime() - 86400000)), sb);
        }
        ParsedResult.maybeAppend(calendarParsedResult.getLocation(), sb);
        ParsedResult.maybeAppend(calendarParsedResult.getOrganizer(), sb);
        ParsedResult.maybeAppend(calendarParsedResult.getAttendees(), sb);
        ParsedResult.maybeAppend(calendarParsedResult.getDescription(), sb);
        return sb.toString();
    }

    private static String format(boolean z, Date date) {
        DateFormat dateFormat;
        if (date == null) {
            return null;
        }
        if (z) {
            dateFormat = DateFormat.getDateInstance(2);
        } else {
            dateFormat = DateFormat.getDateTimeInstance(2, 2);
        }
        return dateFormat.format(date);
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return R.string.result_calendar;
    }
}
