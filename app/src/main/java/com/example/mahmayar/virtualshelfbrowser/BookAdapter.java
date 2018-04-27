package com.example.mahmayar.virtualshelfbrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    private static LayoutInflater inflater;
    private ArrayList<Book> books;
    private Context mContext;

    public BookAdapter(Context context) {
        this.mContext = context;
        this.books = new ArrayList<>();
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView bookView;
        if (convertView == null) {
            bookView = (ImageView) inflater.inflate(R.layout.book_item, parent, false);       // Not Recycled ImageView
        } else {
            bookView = (ImageView) convertView;                                                // Recycled ImageView
        }
        System.out.println(books.get(position).getImage_url());
        Picasso.with(mContext).load(books.get(position).getImage_url()).into(bookView);
        return bookView;
    }

    public void add(Book book) {
        books.add(book);
        notifyDataSetChanged();
    }

    public void clear() {
        this.books.clear();
    }
}
