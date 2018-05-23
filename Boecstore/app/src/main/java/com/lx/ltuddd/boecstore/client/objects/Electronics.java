package com.lx.ltuddd.boecstore.client.objects;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Electronics extends Item{
    private String brand;
    private String type;
    private String color;

    public Electronics(String id, String name, double price, float saleOff, String description, String[] urlImage) {
        super(id, name, price, saleOff, description, urlImage);
    }

    public Electronics(String id, String name, double price,
                       float saleOff, String description, String []urlImage,
                       String brand, String type, String color) {
        super(id, name,  price, saleOff, description, urlImage);
        this.brand = brand;
        this.type = type;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
