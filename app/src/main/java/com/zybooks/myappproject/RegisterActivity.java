package com.zybooks.myappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    LoginDatabase db;
    EditText etRegUsername;
    EditText etRegPassword;
    EditText etRepeatPassword;
    EditText etGoalWeight;
    Button buttonRegister;
    TextView loginLink;
    // declare variables above
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // assigning above variables to below id locations
        db = new LoginDatabase(RegisterActivity.this);
        etRegUsername = (EditText) findViewById(R.id.etRegUsername);
        etRegPassword = (EditText) findViewById(R.id.etRegPassword);
        etRepeatPassword = (EditText) findViewById(R.id.etRepeatPassword);
        etGoalWeight = (EditText) findViewById(R.id.etGoalWeight);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        loginLink = (TextView) findViewById(R.id.loginLink);

        // if the login link is clicked it opens the login activity
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });
        // when the register button is clicked
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // declare strings for password and repeated password
                String pwd = etRegPassword.getText().toString().trim();
                String rpt_pwd = etRepeatPassword.getText().toString().trim();

                UserModel userModel = new UserModel(); // new user model object
                // set user model to the information to be added, goal weight is an int so pass integer parse int first
                userModel = new UserModel(-1, Integer.parseInt(etGoalWeight.getText().toString()),etRegUsername.getText().toString().trim(),
                        etRegPassword.getText().toString().trim(),etRepeatPassword.getText().toString().trim());
                if(pwd.equals(rpt_pwd)) { // use variables declared above to check if the password repeats
                    Toast.makeText(RegisterActivity.this, "You have registered!", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(moveToLogin); // new intent to move to login screen
                } else { // if passwords dont match display error message
                    Toast.makeText(RegisterActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                }
                LoginDatabase loginDatabase = new LoginDatabase(RegisterActivity.this);
                long success = loginDatabase.addUser(userModel); // new database object to add user given user model parameter
                Toast.makeText(RegisterActivity.this, "Success " + success, Toast.LENGTH_SHORT).show(); // additional success message
                LoginDatabase db = new LoginDatabase(RegisterActivity.this);
            }
        });
    }
}


/*
                String user = etRegUsername.getText().toString().trim();
                String pwd = etRegPassword.getText().toString().trim();
                String rpt_pwd = etRepeatPassword.getText().toString().trim();
                int goal = Integer.parseInt(etGoalWeight.getText().toString());

                if(pwd.equals(rpt_pwd)){
                    //long val = db.addUser(user,pwd);
                    long val = db3.addUser(userModel);
                    if(val > 0) {
                        Toast.makeText(RegisterActivity.this, "You have registered!", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Other Issue", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                }


                if(etRegPassword.equals(etRepeatPassword)) {
                        Toast.makeText(RegisterActivity.this, "You have registered!", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                    }

             */