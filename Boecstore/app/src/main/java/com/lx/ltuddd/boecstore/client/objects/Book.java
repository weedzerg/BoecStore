package com.lx.ltuddd.boecstore.client.objects;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Book extends Item{
    private int year;
    private String size;
    private String author;
    private String publisher;

    public Book() {
    }

    public Book(String id, String name, double price, float saleOff, String description, String[] urlImage) {
        super(id, name, price, saleOff, description, urlImage);
    }

    public Book(String id, String name, double price,
                float saleOff, String description, String[] urlImage,
                int year, String author, String publisher) {
        super(id, name, price, saleOff, description, urlImage);
        this.year = year;
        this.author = author;
        this.publisher = publisher;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
