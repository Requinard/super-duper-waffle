package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import nl.fontys.sm.superduperwaffle.R;

/**
 * Created by David on 5/20/2016.
 */
public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(handleClickCalender );

        Button btnPlanTaak = (Button)findViewById(R.id.btnPlanTaak);
        btnLogin.setOnClickListener(handleClickPlanTaak );

        Button btnMentaleToestandKlas = (Button)findViewById(R.id.btnMentaleToestandKlas);
        btnLogin.setOnClickListener(handleClickMentaleToestandKlas );

        Button btnGemiddeldePerLeraar = (Button)findViewById(R.id.btnGemiddeldePerLeraar);
        btnLogin.setOnClickListener(handleClickGemiddeldePerLeraar );
    }

    private View.OnClickListener handleClickCalender = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(HomeActivity.this,CalendarActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener handleClickPlanTaak = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(HomeActivity.this,TaakActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener handleClickMentaleToestandKlas = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(HomeActivity.this,TaakActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener handleClickGemiddeldePerLeraar = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(HomeActivity.this,TaakActivity.class);
            startActivity(intent);
        }
    };

}
