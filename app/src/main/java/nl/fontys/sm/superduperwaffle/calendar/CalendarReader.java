package nl.fontys.sm.superduperwaffle.calendar;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by MT on 26-May-16.
 */
public class CalendarReader {
    public List<CalendarItem> getEvents(Context context,
                                        Calendar start,
                                        Calendar end) {

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

        String selection = "(( " + CalendarContract.Events.DTSTART +" >= " + start.getTimeInMillis() +
                " ) AND ( " + CalendarContract.Events.DTSTART + " <= " + end.getTimeInMillis() + " ))";
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
                    Log.i("Calendar IDs", cursor.getString(7));
                    //Log.i("Calendar", "Title: " + cursor.getString(1) +
                    //        " Start-Time: " + (new Date(cursor.getLong(3))).toString());
                    CalendarItem calendarItem = new CalendarItem(
                            cursor.getString(1),
                            cursor.getLong(3),
                            cursor.getLong(4),
                            cursor.getInt(5) == 1,
                            cursor.getString(6),
                            cursor.getString(2),
                            cursor.getString(7)
                    );
                    calendarItems.add(calendarItem);
                } while ( cursor.moveToNext());
            }
            cursor.close();
        }
        catch (SecurityException ex) {
            Toast.makeText(context, "No calendar permission", Toast.LENGTH_SHORT).show();
        }

        return calendarItems;
    }
}
