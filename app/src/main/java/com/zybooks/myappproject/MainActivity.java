package com.zybooks.myappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    //reference to buttons and controls
    Button btnAdd;
    EditText etWeight, etDate;
    GridView gridView1;
    TextView tvGoal;
    ArrayAdapter userArrayAdapter;
    ArrayAdapter goalArrayAdapter;
    WeightDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning each variable to its respective id in layout
        btnAdd = findViewById(R.id.btnAdd);
        etWeight = findViewById(R.id.etWeight);
        etDate = findViewById(R.id.etDate);
        gridView1 = findViewById(R.id.gridView1);
        tvGoal = findViewById(R.id.tvGoal);

            //create a new instance of database
        db = new WeightDatabase(MainActivity.this);
            // pass the method used to display information
            // info should stay saved and in place when app is restarted
        ShowUserGridView(db);

        // listener for buttons
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creating new usermodel instance
                UserModel userModel = new UserModel();

                try {             // try accepting data in from click
                    userModel = new UserModel(-1, Integer.parseInt(etWeight.getText().toString()), etDate.getText().toString());
                    Toast.makeText(MainActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();
                }                 // if it fails it will catch and show error
                catch (Exception exception){
                    Toast.makeText(MainActivity.this, "Error Adding Data", Toast.LENGTH_SHORT).show();
                }
                // new instance of weight database
                WeightDatabase weightDatabase = new WeightDatabase(MainActivity.this);
                long success = weightDatabase.addWeight(userModel); //add weight to database through toast success message
                Toast.makeText(MainActivity.this, "Success " + success, Toast.LENGTH_SHORT).show();

                WeightDatabase db = new WeightDatabase(MainActivity.this);
                // new instance and updates the grid view data once again
                ShowUserGridView(db);
            }
        });
        // method for clicking on grid items to delete
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserModel clickedData = (UserModel) parent.getItemAtPosition(position);
                db.deleteOne(clickedData);// delete data that is clicked in grid
                ShowUserGridView(db); // update the grid view to reflect the change
                Toast.makeText(MainActivity.this, "Deleted Entry    " + clickedData.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // method for updating the grid view through an array
    private void ShowUserGridView(WeightDatabase db) {
        userArrayAdapter = new ArrayAdapter<UserModel>(MainActivity.this, android.R.layout.simple_list_item_1, db.getEveryone());
        gridView1.setAdapter(userArrayAdapter);
    }




}