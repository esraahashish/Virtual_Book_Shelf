package com.example.mahmayar.virtualshelfbrowser;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchTest {

    private AdminModel adminUserFunc;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    @Before
    public void setUp(){
        dbHelper = new DatabaseHelper(InstrumentationRegistry.getTargetContext(), "TestDB");
        db = dbHelper.getWritableDatabase();
        adminUserFunc = new AdminModel(db);
    }

    @After
    public void tearDown() {
        db.execSQL("DELETE from  book");
    }


    @Test
    public void testSearchByCategory() {
        ArrayList<Book> b = new ArrayList<>();
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

        adminUserFunc.addBook(b);
        ArrayList<Book> books = adminUserFunc.search("category", "drama");
        assertEquals("drama",books.get(0).getCategory());
        assertEquals("1",books.get(0).getISBN());
    }

    @Test
    public void testSearchSize() {
        ArrayList<Book> b = new ArrayList<>();
        Book book1 = new Book();
        book1.setISBN("1");
        book1.setTitle("1");
        book1.setPrice(80);
        book1.setCategory("drama");
        book1.setAuthor("rewan");
        book1.setImage_url("http:url");
        book1.setDescription("good");
        book1.setReleaseDate("2018-02-09");

        Book book2 = new Book();
        book2.setISBN("2");
        book2.setTitle("2");
        book2.setPrice(90);
        book2.setCategory("drama");
        book2.setAuthor("rewan");
        book2.setImage_url("http:url");
        book2.setDescription("good");
        book2.setReleaseDate("2018-02-09");

        b.add(book1);
        b.add(book2);

        adminUserFunc.addBook(b);
        ArrayList<Book> books = adminUserFunc.search("category", "drama");
        assertEquals(2,books.size());

        books = adminUserFunc.search("category", "dream");
        assertEquals(0,books.size());
    }

    @Test
    public void testWrongPriceFormat() {
        boolean thrown = false;
        ArrayList<Book> b = new ArrayList<>();
        Book book1 = new Book();
        book1.setISBN("1");
        book1.setTitle("1");
        book1.setPrice(80);
        book1.setCategory("drama");
        book1.setAuthor("rewan");
        book1.setImage_url("http:url");
        book1.setDescription("good");
        book1.setReleaseDate("2018-02-09");
        b.add(book1);
        adminUserFunc.addBook(b);

        try {
            adminUserFunc.search("price", "12defee");
        }catch(NumberFormatException nfe) {
            thrown = true;
        }
    }



    @Test
    public void testWrongAtrribute() {
        boolean thrown = false;
        ArrayList<Book> b = new ArrayList<>();
        Book book1 = new Book();
        book1.setISBN("1");
        book1.setTitle("1");
        book1.setPrice(80);
        book1.setCategory("drama");
        book1.setAuthor("rewan");
        book1.setImage_url("http:url");
        book1.setDescription("good");
        book1.setReleaseDate("2018-02-09");


        b.add(book1);
        adminUserFunc.addBook(b);
        try {
            ArrayList<Book> books = adminUserFunc.search("categoryy", "dream");
            assertEquals(0, books.size());
        } catch(Exception e) {
            thrown = true;
        }
        assertEquals(true,thrown);
    }





}
