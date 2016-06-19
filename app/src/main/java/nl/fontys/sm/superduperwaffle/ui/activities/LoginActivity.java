package nl.fontys.sm.superduperwaffle.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nl.fontys.sm.superduperwaffle.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(handleClickLogin );

        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(handleClickRegister );

    }

    private View.OnClickListener handleClickLogin = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener handleClickRegister = new View.OnClickListener(){
        public void onClick(View arg0){
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    };
}
