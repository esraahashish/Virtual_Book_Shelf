package com.example.mahmayar.virtualshelfbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {
    private BooksFragment fragment;
    private DbConnection dbConnection;
    private AdminModel adminModel;
    private DbQuery dbQuery;
    private String searchKey = "";
    Intent intent ;

    private final void clearText(){
        EditText searchText = (EditText) findViewById(R.id.searchText);
        searchText.setText("");
        EditText customizeText = (EditText) findViewById(R.id.cutomizeText);
        customizeText.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        dbConnection = DbConnection.getInstace(AdminActivity.this);
        adminModel = new AdminModel(dbConnection);
        dbQuery = new DbQuery(dbConnection);
        intent = getIntent();
        fragment = new BooksFragment();

        boolean user = intent.getBooleanExtra("user", false);
        if(user) {
             findViewById(R.id.editBook).setVisibility(View.INVISIBLE);
             findViewById(R.id.deleteBook).setVisibility(View.INVISIBLE);
             findViewById(R.id.addBooks).setVisibility(View.INVISIBLE);

        }

        Button addBtn = (Button) findViewById(R.id.addBooks);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 new FetchBooks(adminModel, fragment.getBooksGridUpdater()).execute();
            }
        });

        Button editBtn = (Button) findViewById(R.id.editBook);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.isbnView).setVisibility(View.VISIBLE);
                findViewById(R.id.isbnText).setVisibility(View.VISIBLE);
                findViewById(R.id.priceView).setVisibility(View.VISIBLE);
                findViewById(R.id.priceText).setVisibility(View.VISIBLE);
                findViewById(R.id.okEdit).setVisibility(View.VISIBLE);
            }
        });


        Button delBtn = (Button) findViewById(R.id.deleteBook);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.isbnView).setVisibility(View.VISIBLE);
                findViewById(R.id.isbnText).setVisibility(View.VISIBLE);
                findViewById(R.id.okDelete).setVisibility(View.VISIBLE);
            }
        });

        Button okDelBtn = (Button) findViewById(R.id.okDelete);
        okDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText isbnText = (EditText) findViewById(R.id.isbnText);
                adminModel.deleteBook(isbnText.getText().toString());

                findViewById(R.id.isbnView).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.isbnText)).setText("");
                findViewById(R.id.isbnText).setVisibility(View.INVISIBLE);
                findViewById(R.id.okDelete).setVisibility(View.INVISIBLE);
            }
        });

        Button okEditBtn = (Button) findViewById(R.id.okEdit);
        okEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> attributes = new HashMap<String, String>();

                EditText isbnText = (EditText) findViewById(R.id.isbnText);
                String isbn = isbnText.getText().toString();
                attributes.put("isbn", isbn);

                EditText priceText = (EditText) findViewById(R.id.priceText);
                String price = priceText.getText().toString();
                attributes.put("price", price);

                adminModel.editBook(attributes);

                findViewById(R.id.isbnView).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.isbnText)).setText("");
                findViewById(R.id.isbnText).setVisibility(View.INVISIBLE);
                findViewById(R.id.priceView).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.priceText)).setText("");
                findViewById(R.id.priceText).setVisibility(View.INVISIBLE);
                findViewById(R.id.okEdit).setVisibility(View.INVISIBLE);

            }
        });


        Button searchSubmit = (Button) findViewById(R.id.searchSubmit);
        searchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchText = (EditText) findViewById(R.id.searchText);
                String searchValue = searchText.getText().toString();
                List<Book> bookList = dbQuery.search(searchKey, searchValue);
                for(Book b : bookList) {
                    System.out.println(b.getISBN());
                }
            }
        });

        Button customizeSubmit = (Button) findViewById(R.id.cutomizeSubmit);
        customizeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText customizeText = (EditText) findViewById(R.id.cutomizeText);
                String customizeValue = customizeText.getText().toString();
                List<Book> bookList = dbQuery.search(searchKey, customizeValue);
                for(Book b : bookList) {
                    System.out.println(b.getISBN());
                }
            }
        });


        Button searchButton = (Button) findViewById(R.id.search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearText();
                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                popup.inflate(R.menu.search_menu);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.isbn:
                                searchKey = "isbn";
                                return true;
                            case R.id.title:
                                searchKey = "title";
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });

        Button customizeButton = (Button) findViewById(R.id.customize);
        customizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearText();
                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                popup.inflate(R.menu.customize_menu);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.author:
                                searchKey = "author";
                                return true;
                            case R.id.category:
                                searchKey = "category";
                                return true;
                            case R.id.price:
                                searchKey = "price";
                                return true;
                            case R.id.releaseDate:
                                searchKey = "release_date";
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });

        getFragmentManager().beginTransaction().add(R.id.bookFragment, fragment, "tag").commit();
    }
}