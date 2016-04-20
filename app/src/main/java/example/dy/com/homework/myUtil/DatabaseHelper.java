package example.dy.com.homework.myUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import example.dy.com.homework.entity.Step;
import example.dy.com.homework.entity.User;

/**
 * Created by dy on 2016/4/19.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Set database properties
    public static final String DATABASE_NAME = "Health";
    public static final int DATABASE_VERSION = 1;


    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_STATEMENT);
        db.execSQL(Step.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Step.TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, u.getName());
        values.put(User.COLUMN_PASSWORD, StringUtils.getPasswordEncryption(u.getPassword()));
        values.put(User.COLUMN_REGISTRATION, StringUtils.getCurTime());
        values.put(User.COLUMN_LATITUDE, 0);
        values.put(User.COLUMN_LONGITUDE, 0);
        db.insert(User.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + User.TABLE_NAME +
                " WHERE " + User.COLUMN_NAME + " = " + id, null);
        if( cursor.moveToFirst() ){
            return true;
        }else{
            return  false;
        }
    }


    public void addSteps(Step s) {

    }

    public List<User> findAllUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + User.TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                User u = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getDouble(4),cursor.getDouble(5));
                list.add(u);
            } while(cursor.moveToNext());
        }
        System.out.println("get user from sqlite");
        for (User u : list) {
            System.out.println(u);
        }
        return list;
    }


}
