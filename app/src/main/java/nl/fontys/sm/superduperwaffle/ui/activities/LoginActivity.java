package nl.fontys.sm.superduperwaffle.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nl.fontys.sm.superduperwaffle.R;

public class LoginActivity extends AppCompatActivity {
    EditText loginName;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = (EditText) findViewById(R.id.tbNaam);
        password  = (EditText) findViewById(R.id.tbWachtwoord);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(handleClickLogin );

        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(handleClickRegister );

    }

    private View.OnClickListener handleClickLogin = new View.OnClickListener(){
        public void onClick(View arg0){
            if (checkFieldsValid()) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        }
    };

    private View.OnClickListener handleClickRegister = new View.OnClickListener(){
        public void onClick(View arg0){
            if (checkFieldsValid()) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        }
    };

    private boolean checkFieldsValid() {
        if (loginName.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "No username and password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (loginName.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "No username", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "No password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
