package com.example.mahmayar.virtualshelfbrowser;


import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Map;

public class AdminModel extends DbQuery {
    private SQLiteDatabase db;

    public AdminModel(DbConnection dbConnection) {
        super(dbConnection);
        this.db = dbConnection.getConnection();
    }

    private final String BOOK_TABLE_NAME = "book";

    public void addBook(ArrayList<Book> books) {

        for(Book book :books)
        {
            String sqlInsert = "INSERT INTO " + BOOK_TABLE_NAME +
                    " VALUES (" + "\"" + book.getISBN() + "\"," +
                    "\"" + book.getTitle() + "\"," +
                    "\"" +book.getAuthor() + "\","+
                    "\"" + book.getReleaseDate() + "\"," +
                    "\"" + book.getDescription() + "\"," +
                    "\"" + book.getPrice() + "\"," +
                    "\"" + book.getCategory()  + "\"," +
                    "\""+ book.getImage_url() + "\");";

            System.out.println(sqlInsert);

            db.execSQL(sqlInsert);
        }

    }

    public void editBook(Map<String, String> attributes)
    {
        String sqlUpdate = "UPDATE " + BOOK_TABLE_NAME + " SET ";
        for (Map.Entry<String, String> entry : attributes.entrySet())
        {
            sqlUpdate += entry.getKey() + " = \""+ entry.getValue() +"\"" + ",";
        }
        sqlUpdate = sqlUpdate.substring(0, sqlUpdate.length() - 1);
        sqlUpdate += " where isbn = " + "\""+attributes.get("isbn")+"\";";
        db.execSQL(sqlUpdate);
    }

    public void deleteBook(String isbn)
    {
        String deleteBook = "DELETE FROM " + BOOK_TABLE_NAME +
                " WHERE isbn=" + isbn;
        db.execSQL(deleteBook);
    }


}