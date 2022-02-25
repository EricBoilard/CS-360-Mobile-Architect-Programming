package com.zybooks.myappproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
// declaring the database variables and structure
public class LoginDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register.db";
    public static final String Table_Name = "registerUser";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String COL_4 = "goal";

    public LoginDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override // on create used to declare table creation
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("CREATE TABLE registerUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE registerUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, goal INTEGER)");
    }

    @Override // on upgrade to make sure future versions are supported
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);
    }
    // add user function, uses user model class for methods, getters, and setters
    public long addUser(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, userModel.getUsername());
        contentValues.put(COL_3, userModel.getPassword()); // setting content value to variables and getters
        contentValues.put(COL_4, userModel.getGoal());
        long res = db.insert("registerUser", null, contentValues); // insert into db
        db.close();
        return res;
    }
        // check user takes username and string
    public boolean checkUser (String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase(); //read from database columns 2 and 3 which have username and password
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(Table_Name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount(); // query existing table and cursor through it
        cursor.close();
        db.close();

        if(count > 0) // return true if data exists
            return true;
        else
            return false;
    }
    // creating a list for goal display
    public List<UserModel> getGoal(){
        List<UserModel> returnList = new ArrayList();
        String queryString = "SELECT * FROM " + Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            // loop through the cursor, create new objects, put them into list
            do {
                int userID = cursor.getInt(0);
                int userGoal = cursor.getInt(4); // selecting only the user goal location
                UserModel newGoal = new UserModel(userID, userGoal);
                returnList.add(newGoal);

            } while (cursor.moveToNext());
        } else { }

        cursor.close();
        db.close();
        return returnList;
    }




}




/*

    public long addUser(String user, String password, int goal){  //public long addUser(String user, String password, String goal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        contentValues.put("goal", goal);
        long res = db.insert("registerUser", null, contentValues);
        db.close();
        return res;
    }

     */