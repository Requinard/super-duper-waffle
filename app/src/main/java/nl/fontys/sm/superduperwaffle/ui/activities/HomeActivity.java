package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import nl.fontys.sm.superduperwaffle.R;

/**
 * Created by David on 5/20/2016.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnCalendar = (Button) findViewById(R.id.btnBekijkKalender);
        btnCalendar.setOnClickListener(handleClickCalender);

        Button btnPlanTaak = (Button) findViewById(R.id.btnPlanTaak);
        btnPlanTaak.setOnClickListener(handleClickPlanTaak);

        Button btnMentaleToestandKlas = (Button) findViewById(R.id.btnMentaleToestandKlas);
        btnMentaleToestandKlas.setOnClickListener(handleClickMentaleToestandKlas);

        Button btnGemiddeldePerLeraar = (Button) findViewById(R.id.btnGemiddeldePerLeraar);
        btnGemiddeldePerLeraar.setOnClickListener(handleClickGemiddeldePerLeraar);
    }

    private View.OnClickListener handleClickCalender = new View.OnClickListener() {
        public void onClick(View arg0) {
            //Intent intent = new Intent(HomeActivity.this, CalendarActivity.class);
            Intent intent = new Intent(HomeActivity.this, ImportCalendarActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener handleClickPlanTaak = new View.OnClickListener() {
        public void onClick(View arg0) {
            Intent intent = new Intent(HomeActivity.this, TaakActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener handleClickMentaleToestandKlas = new View.OnClickListener() {
        public void onClick(View arg0) {

            new AlertDialog.Builder(HomeActivity.this)
                    .setTitle("Deze functionaliteit is enkel beschikbaar voor leraren")
                    .setMessage("Jammer maar helaas")
                    .show();
        }
    };

    private View.OnClickListener handleClickGemiddeldePerLeraar = new View.OnClickListener() {
        public void onClick(View arg0) {
            new AlertDialog.Builder(HomeActivity.this)
                    .setTitle("Deze functionaliteit is enkel beschikbaar voor Blokeigenaren")
                    .setMessage("Kom later terug")
                    .show();
        }
    };

}
