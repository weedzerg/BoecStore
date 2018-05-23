package com.lx.ltuddd.boecstore.client.objects;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Book extends Item {
    private String type;
    private int year;
    private String author;
    private String publisher;

    public Book(String id, String name, double price,
                float saleOff, String description, String urlImage,
                String type, int year, String author, String publisher) {
        super(id, name, price, saleOff, description, urlImage);
        this.type = type;
        this.year = year;
        this.author = author;
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
