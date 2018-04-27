package com.example.mahmayar.virtualshelfbrowser;

import android.os.AsyncTask;
import java.util.ArrayList;

public class FetchBooks extends AsyncTask<Void, Void, ArrayList<Book>> {
    private AdminModel adminModel;
    private NetworkUtils networkUtils;
    private BooksGridUpdater updater;


    public FetchBooks(AdminModel adminModel, BooksGridUpdater updater) {
        this.updater = updater;
        this.adminModel = adminModel;
        networkUtils = new NetworkUtils();
    }

    @Override
    protected ArrayList<Book> doInBackground(Void... voids) {
        String jsonStr = networkUtils.getBookTnfo();
        JSONReader reader = new JSONReader();
        return reader.getBooks(jsonStr);
    }

    @Override
    protected void onPostExecute(ArrayList<Book> books) {
        super.onPostExecute(books);
        adminModel.addBook(books);
        updater.update(books);
    }

}
