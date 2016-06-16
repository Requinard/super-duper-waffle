package nl.fontys.sm.superduperwaffle.calendar;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by MT on 26-May-16.
 */
public class CalendarReader {
    private enum EventProperty {
        CanvasSubjectName,
        CalendarName
    }

    private enum ProjectionID {
        CalendarID,
        Title,
        Description,
        DateStart,
        DateEnd,
        AllDay,
        EventLocation,
        CalendarName
    }

    public List<String> GetCalendars(Context context) {
        List<String> calendarNames = new ArrayList<>();
        String[] projection = new String[]{
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME
        };

        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;

        try {
            Cursor cursor = contentResolver.query(uri,
                    projection,
                    null,
                    null,
                    null);
            while (cursor.moveToNext()) {
                calendarNames.add(cursor.getString(0));
            }
        }
        catch (SecurityException ex) {
            Toast.makeText(context, "No calendar permission", Toast.LENGTH_SHORT).show();
        }
        return calendarNames;
    }

    public List<CalendarItem> GetEvents(Context context,
                                        Calendar start,
                                        Calendar end,
                                        String matchCalendarName) {

        List<CalendarItem> calendarItems = new ArrayList<>();

        String[] projection = new String[] {
                CalendarContract.Events.CALENDAR_ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND,
                CalendarContract.Events.ALL_DAY,
                CalendarContract.Events.EVENT_LOCATION,
                CalendarContract.Events.CALENDAR_DISPLAY_NAME};

        String selection = "(( " +
                CalendarContract.Events.DTSTART +" >= " + start.getTimeInMillis() +
                " ) AND ( " + CalendarContract.Events.DTSTART + " <= " + end.getTimeInMillis() +
                " ))";
        ContentResolver contentResolver;
        contentResolver = context.getContentResolver();

        try {
            Cursor cursor = contentResolver.query(
                    CalendarContract.Events.CONTENT_URI,
                    projection,
                    selection,
                    null,
                    null);
            if (cursor.moveToFirst()) {
                do {
                    CalendarItem calendarItem = new CalendarItem(
                            extractInfo(
                                    EventProperty.CanvasSubjectName,
                                    cursor.getString(ProjectionID.Title.ordinal())
                            ),
                            cursor.getLong(ProjectionID.DateStart.ordinal()),
                            cursor.getLong(ProjectionID.DateEnd.ordinal()),
                            cursor.getInt(ProjectionID.AllDay.ordinal()) == 1,
                            cursor.getString(ProjectionID.EventLocation.ordinal()),
                            cursor.getString(ProjectionID.Description.ordinal()),
                            cursor.getString(ProjectionID.CalendarName.ordinal()
                    ));

                    if (calendarItem.calendarName.toLowerCase().contains(matchCalendarName.toLowerCase()) ||
                            matchCalendarName == "") {
                        calendarItems.add(calendarItem);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (SecurityException ex) {
            Toast.makeText(context, "No calendar permission", Toast.LENGTH_SHORT).show();
        }
        return calendarItems;
    }

    private String extractInfo(EventProperty prop, String input) {
        switch(prop) {
            case CanvasSubjectName:
                int indexStart = input.indexOf("[");
                int indexEnd = input.indexOf("]");
                if (indexStart == -1 || indexEnd == -1) {
                    return input;
                }
                return input.substring(indexStart+1, indexEnd);
            case CalendarName:
                return input;
        }
        return input;
    }
}
