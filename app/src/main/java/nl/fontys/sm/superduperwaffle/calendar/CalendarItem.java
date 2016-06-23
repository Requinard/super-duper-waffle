package nl.fontys.sm.superduperwaffle.calendar;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by MT on 26-May-16.
 */
public class CalendarItem implements Comparable<CalendarItem>, Serializable{
    public String subjectName;
    public long startTime;
    public long stopTime;
    public boolean allDayEvent;
    public String location;
    public String description;
    public String calendarName;

    public CalendarItem(
            String subjectName,
            long startTime,
            long stopTime,
            boolean allDayEvent,
            String location,
            String description,
            String calendarName) {
        this.subjectName = subjectName;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.allDayEvent = allDayEvent;
        this.location = location;
        this.description = description;
        this.calendarName = calendarName;
    }

    public int compareTo(CalendarItem calendarItem) {
        return Long.compare(
                startTime,
                calendarItem.startTime);
    }
}
