package com.lx.ltuddd.boecstore.client.objects;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Item implements Serializable {
    private String id;
    private String name;
    private double price;
    private float saleOff;
    private String description;
    private String [] urlImage;

    public Item() {
    }

    public Item(String id, String name,
                double price, float saleOff, String description, String[] urlImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.saleOff = saleOff;
        this.description = description;
        this.urlImage = urlImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(float saleOff) {
        this.saleOff = saleOff;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String[] urlImage) {
        this.urlImage = urlImage;
    }
    public double sumaryPrice(){
        return this.price-this.price*saleOff;
    }
}
