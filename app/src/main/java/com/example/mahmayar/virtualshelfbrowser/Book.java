package com.example.mahmayar.virtualshelfbrowser;

public class Book{
    private String ISBN;
    private String title;
    private float price;
    private String releaseDate;
    private String description;
    private String category;
    private String author;
    private String image_url;
    private String currency = "";

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releasedDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}