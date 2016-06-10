package nl.fontys.sm.superduperwaffle.ui.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.calendar.CalendarItem;

/**
 * Created by MT on 10-Jun-16.
 */
public class CalendarItemAdapter extends ArrayAdapter<CalendarItem>{
    private Context context;
    private List<CalendarItem> calendarItems;

    public CalendarItemAdapter(Context context,
                               List<CalendarItem> calendarItems) {
        super(context, R.layout.calendar_item);
        this.context = context;
        this.calendarItems = calendarItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CalendarItem requestedItem = calendarItems.get(position);

        View itemView = convertView;
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.calendar_item, null);
        }

        TextView subjectTitle = (TextView) itemView.findViewById(R.id.tvEventSubjectTitle);
        TextView deadline = (TextView) itemView.findViewById(R.id.tvEventDeadline);
        TextView description = (TextView) itemView.findViewById(R.id.tvEventDescription);

        subjectTitle.setText(requestedItem.subjectName);

        Date date = new Date(requestedItem.stopTime);
        DateFormat formatter = new SimpleDateFormat("d M Y HH:MM");
        deadline.setText(formatter.format(date));

        description.setText(requestedItem.description);

        return itemView;
    }
}
