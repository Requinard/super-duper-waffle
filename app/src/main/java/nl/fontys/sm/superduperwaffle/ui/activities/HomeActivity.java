package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import nl.fontys.sm.superduperwaffle.R;

/**
 * Created by David on 5/20/2016.
 */
public class HomeActivity extends Activity {

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
            Intent intent = new Intent(HomeActivity.this, CalendarActivity.class);
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
            Dialog dialog = new Dialog(getBaseContext());

            dialog.setTitle("Deze functionaliteit is er nog niet!");
            dialog.show();

        }
    };

    private View.OnClickListener handleClickGemiddeldePerLeraar = new View.OnClickListener() {
        public void onClick(View arg0) {
            Dialog dialog = new Dialog(getBaseContext());

            dialog.setTitle("Deze functionaliteit is er nog niet!");
            dialog.show();
        }
    };

}
