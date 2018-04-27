package com.example.mahmayar.virtualshelfbrowser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class DbConnection implements Serializable {
    private static DbConnection instance;
    private static DatabaseHelper dbHelper;
    private static SQLiteDatabase db;
    private static Context context;

    private DbConnection() {

    }

    public static DbConnection getInstace(Context mContext) {
        if (instance == null) {
            context = mContext;
            dbHelper = new DatabaseHelper(context, "BookStore");
            db = dbHelper.getWritableDatabase();
            instance = new DbConnection();
        }
        return instance;
    }

    public SQLiteDatabase getConnection() {
        return db;
    }

}
