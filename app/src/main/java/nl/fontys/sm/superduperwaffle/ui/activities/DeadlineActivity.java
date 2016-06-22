package nl.fontys.sm.superduperwaffle.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import nl.fontys.sm.superduperwaffle.R;

public class DeadlineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline);

        Button btnInfo = (Button)findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(handleClickInfo );
    }

    private View.OnClickListener handleClickInfo = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(DeadlineActivity.this,InfoActivity.class);
            startActivity(intent);
        }
    };
}
