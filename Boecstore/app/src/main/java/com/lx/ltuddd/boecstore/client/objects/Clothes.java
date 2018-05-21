package com.lx.ltuddd.boecstore.client.objects;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Clothes extends Item{
    private String brand;
    private String type;
    private String material;
    private String color;
    private String pattern;

    public Clothes(String id, String name, double price, float saleOff, String description, String urlImage, String brand,
                   String type, String material, String color, String pattern) {
        super(id, name, price, saleOff, description, urlImage);
        this.brand = brand;
        this.type = type;
        this.material = material;
        this.color = color;
        this.pattern = pattern;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
