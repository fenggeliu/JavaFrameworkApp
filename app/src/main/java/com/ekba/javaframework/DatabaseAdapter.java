package com.ekba.javaframework;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fengge on 11/12/2016.
 */

public class DatabaseAdapter {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAdapter instance;

    DatabaseAdapter(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public List<String> getInterface() {
        database = openHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT DISTINCT interface FROM Methods;", null);
        cursor.moveToFirst();
        List<String> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //get the class names for the interface
    public List<String> getClasses (String interFace) {
        database = openHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT DISTINCT class FROM Methods " +
                "WHERE interface = '" + interFace + "';", null);
        cursor.moveToFirst();
        List<String> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Cursor getInstance(String className) {
        database = openHelper.getReadableDatabase();
        String query = "SELECT * FROM Methods WHERE class = '" + className + "';";
        return database.rawQuery(query, null);
    }

    //Close the database connection.
    public void close() {
        database = openHelper.getReadableDatabase();
        if (database != null) {
            this.database.close();
        }
    }

}
