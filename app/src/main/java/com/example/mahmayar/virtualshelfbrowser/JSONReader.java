package com.example.mahmayar.virtualshelfbrowser;

import android.net.Uri;
import android.util.Log;

import com.example.mahmayar.virtualshelfbrowser.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class JSONReader {

    public ArrayList<Book> getBooks(String booksJsonStr) {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            // Root Element of JSON
            JSONObject root = new JSONObject(booksJsonStr);

            JSONArray results = root.getJSONArray("items");

            for (int i = 0; i < results.length(); i++) {
                Book book = new Book();

                JSONObject bookInfo = results.getJSONObject(i);
                String isbn = bookInfo.getJSONObject("volumeInfo").getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier");
                book.setISBN(isbn);


                String title = bookInfo.getJSONObject("volumeInfo").getString("title");
                book.setTitle(replaceDQuotes(title));

                String author = bookInfo.getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
                book.setAuthor(author);

                String releaseDate = bookInfo.getJSONObject("volumeInfo").getString("publishedDate");
                book.setReleaseDate(releaseDate);

                String description = bookInfo.getJSONObject("volumeInfo").getString("description");
                book.setDescription(replaceDQuotes(description));

                boolean isForSale = bookInfo.getJSONObject("saleInfo").getBoolean("isEbook");

                float price = -1.0f;

                if (isForSale) {
                    price = (float) bookInfo.getJSONObject("saleInfo").getJSONObject("listPrice").getDouble("amount");
                    book.setCurrency(bookInfo.getJSONObject("saleInfo").getJSONObject("listPrice").getString("currencyCode"));
                }
                book.setPrice(price);

                String category = "";
                try {
                    category = bookInfo.getJSONObject("volumeInfo").getJSONArray("categories").getString(0);
                } catch (JSONException e) {
                    category = "Unspecified";
                }

                book.setCategory(category);

                // add a book only if  it has an image preview
                try {
                    String imageURL = bookInfo.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
                    book.setImage_url(imageURL);
                    books.add(book);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;
    }

    public String replaceDQuotes(String str) {
        return str.replaceAll("\"", "\'");
    }

}
