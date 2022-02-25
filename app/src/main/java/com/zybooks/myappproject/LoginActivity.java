package com.zybooks.myappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //declaring variables
    Button buttonLogin;
    EditText etUsername, etPassword;
    TextView registerLink;

    LoginDatabase db;

    @Override //on creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // define location for variables based on id's in xml
        db = new LoginDatabase(this);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        registerLink = (TextView)findViewById(R.id.registerLink);

        // if the register link is clicked it opens the register activity
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent); // starts new activity
            }
        });
        // when the login button is clicked
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = etUsername.getText().toString().trim(); // declare variables in new strings
                String pwd = etPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd); // pass through boolean check user method
                if (res == true){   //if user and pwd are true, meaning they come back as in the db
                    Toast.makeText(LoginActivity.this, "Successful Login",Toast.LENGTH_SHORT).show();
                    Intent MainPage = new Intent(LoginActivity.this, MainActivity2.class);
                    startActivity(MainPage); // opens next activity, permissions
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}