package nl.fontys.sm.superduperwaffle.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nl.fontys.sm.superduperwaffle.R;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = (Button)findViewById(R.id.btnLogin);
        btn.setOnClickListener(handleClick );

    }

    private View.OnClickListener handleClick = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(login.this,HomeActivity.class);
            startActivity(intent);
        }
    };
}
