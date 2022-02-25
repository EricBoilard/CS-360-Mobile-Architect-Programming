package com.zybooks.myappproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class WeightDatabase extends SQLiteOpenHelper {
    // declaring variables according to table structure
    public static final String DATABASE_NAME = "weight.db";
    private static final String TABLE_NAME = "WEIGHT_TABLE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "WEIGHT";
    private static final String COL_3 = "DATE";


    public WeightDatabase(Context context) { super(context, DATABASE_NAME, null, 1); }


    @Override // declaring elements to go in table on creation
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE WEIGHT_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, WEIGHT INT, DATE TEXT)");
    }

    @Override //upgrade so that updated versions dont stop working
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    // add weight method
    public long addWeight(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, userModel.getWeight()); // choosing weight and date variables
        contentValues.put(COL_3, userModel.getDate());
            // inserting data into the table declared above
        long res = db.insert("WEIGHT_TABLE", null, contentValues);
        db.close();
        return res;
    }
        // method for deleting items from table in database
    public boolean deleteOne(UserModel userModel){
        SQLiteDatabase db3 = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + userModel.getId();
        // raw query to check database
        Cursor cursor = db3.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }

    }
        // method for turning weights and dates into an array
    public List<UserModel> getEveryone() {

        List<UserModel> returnList = new ArrayList();

        // get data from the database
        String queryString = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            // loop through the cursor, create new objects, put them into list
            do { //specifying data location
                int userID = cursor.getInt(0);
                int userWeight = cursor.getInt(1);
                String userDate = cursor.getString(2);

                UserModel newUser = new UserModel(userID, userWeight, userDate);
                returnList.add(newUser);

            } while (cursor.moveToNext());
        } else { }

        cursor.close();
        db.close();
        return returnList;

    }

}
