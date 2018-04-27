package com.example.mahmayar.virtualshelfbrowser;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class BooksGridUpdater {
    private static BooksGridUpdater booksGridUpdater;
    private static BookAdapter bookAdapter;
    private static Context mContext;

    public BooksGridUpdater(BookAdapter adapter, Context context) {
        bookAdapter = adapter;
        mContext = context;
    }


    public void update(ArrayList<Book> books) {
        System.out.println( " <<< " + books.size() + " >>> ");

        if (books != null) {
            if (bookAdapter.getCount() > 0) {
                bookAdapter.clear();
            }
            for (int i = 0; i < books.size(); i++) {
                bookAdapter.add(books.get(i));
            }
        } else {
            Toast.makeText(mContext, "There is no available books!", Toast.LENGTH_LONG).show();
        }
    }


}
