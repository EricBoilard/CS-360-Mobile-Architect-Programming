package com.zybooks.myappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private int SMS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // request button
        Button buttonRequest = findViewById(R.id.button);
        buttonRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    requestUserPermission(); // request permission method
            }
        });
        // deny permission button
        Button buttonDeny = findViewById(R.id.btn);
        buttonDeny.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { // if permission is denied continue using application
                Toast.makeText(MainActivity2.this, "Permission Denied",Toast.LENGTH_SHORT).show();
                Intent MainPage2 = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(MainPage2); // move to the main activity page once click has been verified
            }
        });
    }
    // dialog box telling user what the permission is for if they click not accept
    private void requestUserPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.SEND_SMS)) {
                // giving user more information on permission and allowing them to change their mind or continue
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to be able to send notifications")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity2.this,
                                    new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }

    @Override // permission result method to give message on if granted or not
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
            Intent MainPage2 = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(MainPage2); // switches to main activity page
        }
    }
}

