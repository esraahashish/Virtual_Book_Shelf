package com.example.mahmayar.virtualshelfbrowser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String bookTable = "create table book (isbn varchar, title varchar, author varchar, release_date varchar, description varchar, price double, category varchar, image_url varchar, primary key(isbn));";
        db.execSQL(bookTable);

       /* String libraryTable = "create table libraries (id int constraint AUTO_INCREMENT  PRIMARY KEY , name varchar, location varchar);";
        db.execSQL(libraryTable);

        String intermediateTable = "create table book_libraries (isbn varchar NOT NULL, id int NOT NULL,primary key(isbn, id), FOREIGN KEY (isbn) references book (isbn) ON delete cascade, "
                +"FOREIGN KEY (id) references libraries (id) ON delete cascade);";
        db.execSQL(intermediateTable);
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS book");
        onCreate(db);
    }
}
