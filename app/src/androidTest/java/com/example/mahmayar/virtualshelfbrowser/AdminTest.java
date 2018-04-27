package com.example.mahmayar.virtualshelfbrowser;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AdminTest extends InstrumentationTestCase {
    private AdminModel adminModel;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;


    @Before
    public void setUp(){
        dbHelper = new DatabaseHelper(InstrumentationRegistry.getTargetContext(), "TestDB");
        db = dbHelper.getWritableDatabase();
        adminModel = new AdminModel(db);
    }

    @Test
    public void testAddBook() {
        ArrayList<Book>b = new ArrayList<>();
        Book book = new Book();
        book.setISBN("1");
        book.setTitle("1");
        book.setPrice(80);
        book.setCategory("drama");
        book.setAuthor("rewan");
        book.setImage_url("http:url");
        book.setDescription("good");
        book.setReleaseDate("2018-02-09");
        b.add(book);

        adminModel.addBook(b);
        Cursor cursor = db.rawQuery("select * from book where isbn = '1'", null);
        while(cursor.moveToNext()) {
            assertEquals("drama",cursor.getString(cursor.getColumnIndex("category")));
        }
    }

    @Test
    public void testEditBook() {
        ArrayList<Book>b = new ArrayList<>();
        Book book = new Book();
        book.setISBN("2");
        book.setTitle("1");
        book.setPrice(80);
        book.setCategory("drama");
        book.setAuthor("rewan");
        book.setImage_url("http:url");
        book.setDescription("good");
        book.setReleaseDate("2018-02-09");
        b.add(book);

        adminModel.addBook(b);
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("isbn","2");
        attributes.put("price","90");
        adminModel.editBook(attributes);

        Cursor cursor = db.rawQuery("select price from book where isbn='2'", null);
        while(cursor.moveToNext()) {
            assertEquals(90,cursor.getInt(cursor.getColumnIndex("price")));
        }
    }

    @Test
    public void testRemoveBook1() {
        ArrayList<Book>b = new ArrayList<>();
        Book book = new Book();
        book.setISBN("3");
        book.setTitle("1");
        book.setPrice(80);
        book.setCategory("drama");
        book.setAuthor("rewan");
        book.setImage_url("http:url");
        book.setDescription("good");
        book.setReleaseDate("2018-02-09");
        b.add(book);

        adminModel.addBook(b);

        Cursor cursor = db.rawQuery("select * from book", null);


        assertEquals(3,cursor.getCount());

        adminModel.deleteBook("3");

        cursor = db.rawQuery("select * from book", null);

        assertEquals(2,cursor.getCount());
    }

    @Test
    public void testRemoveBook2() {
        ArrayList<Book>b = new ArrayList<>();
        Book book = new Book();
        book.setISBN("4");
        book.setTitle("1");
        book.setPrice(80);
        book.setCategory("drama");
        book.setAuthor("rewan");
        book.setImage_url("http:url");
        book.setDescription("good");
        book.setReleaseDate("2018-02-09");
        b.add(book);

        adminModel.addBook(b);

        adminModel.deleteBook("4");

        Cursor cursor = db.rawQuery("select * from book where isbn='4'", null);

        assertEquals(0,cursor.getCount());
    }


    @Test
    public void testMultiFunction() {
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("isbn","2");
        attributes.put("price","25");
        adminModel.editBook(attributes);

        Cursor cursor = db.rawQuery("select price from book where isbn='2'", null);
        while(cursor.moveToNext()) {
            assertEquals(25,cursor.getInt(cursor.getColumnIndex("price")));
        }

        adminModel.deleteBook("2");

        cursor = db.rawQuery("select * from book where isbn='2'", null);

        assertEquals(0, cursor.getCount());
    }








}
