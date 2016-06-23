package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.wearable.Sensor;
import nl.fontys.sm.superduperwaffle.wearable.ConnectionManager;

/**
 * Created by MT on 22-Jun-16.
 */
public class ShowDeadlineDetailsActivity extends Activity {

    LineChart lineChart;
    ConnectionManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetail);
        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return; // fuck
        }

        TextView courseName = (TextView) findViewById(R.id.tvCourseName);
        TextView deadline = (TextView) findViewById(R.id.tvDeadline);
        TextView description = (TextView) findViewById(R.id.tvDescription);

        courseName.setText(extras.getString("coursename"));
        description.setText(extras.getString("description"));

        Date date=new Date(extras.getLong("deadline"));
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(date);
        deadline.setText("Deadline: " + dateText);

        lineChart = (LineChart) findViewById(R.id.lineChartCourse);
        lineChart.setDescription("Stress levels");
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        cm = new ConnectionManager();

        setData(20, 100);


    }
    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float mult = (range + 1);
            //float val = (float) (Math.random() * mult) + 3;// + (float)
            // ((mult *
            // 0.1) / 10);x
            float val = (float) cm.calculateStressLevel(
                    Sensor.both,
                    (double) cm.calibrateHeartRateSensor(),
                    (double) cm.calibrateSkinConductionSensor());
            yVals.add(new Entry(val, i));
        }

        LineDataSet set1;

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)lineChart.getData().getDataSetByIndex(0);
            set1.setYVals(yVals);
            lineChart.getData().setXVals(xVals);
            lineChart.getData().notifyDataChanged();

            lineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "Stress levels");

            // set1.setFillAlpha(110);
            // set1.setFillColor(Color.RED);

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);

            /*if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            }
            else {*/
                set1.setFillColor(Color.BLACK);
            //}

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(xVals, dataSets);

            // set data
            lineChart.setData(data);
        }
    }
}
