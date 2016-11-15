package com.ekba.javaframework;

import android.content.Context;


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;



/**
 * Created by fengge on 11/12/2016.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "JavaFramework.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}
