package nl.fontys.sm.superduperwaffle.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nl.fontys.sm.superduperwaffle.R;

/**
 * Created by Gebruiker on 19-6-2016.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(handleClickRegister );

    }


    private View.OnClickListener handleClickRegister = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    };
}