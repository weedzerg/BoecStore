package com.lx.ltuddd.boecstore.client.utils;

import com.google.firebase.database.DatabaseReference;
import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.objects.Clothes;
import com.lx.ltuddd.boecstore.client.objects.Customer;
import com.lx.ltuddd.boecstore.client.objects.Electronics;
import com.lx.ltuddd.boecstore.client.objects.Item;

import java.util.HashMap;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Utils {
    public static Book convertItemtoBook(Item item) {
        Book b = new Book();
        b.setId(item.getId());
        b.setName(item.getName());
        b.setPrice(item.getPrice());
        b.setSaleOff(item.getSaleOff());
        b.setDescription(item.getDescription());
        b.setUrlImage(item.getUrlImage());
        return b;
    }

    public static boolean upInfCustomer(DatabaseReference reference, Customer customer) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("fullname/firstName", customer.getFullName().getFirstName());
        hashMap.put("fullname/lastName", customer.getFullName().getLastName());
        hashMap.put("fullname/midName", customer.getFullName().getMidName());
        hashMap.put("id", customer.getId());
        hashMap.put("email", customer.getEmail());
        hashMap.put("urlImage", customer.getUrlImage());
        try {
            reference.updateChildren(hashMap);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public static HashMap<String, Object> creatItem(Book b) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", b.getId());
        hashMap.put("name", b.getName());
        hashMap.put("price", b.getPrice());
        hashMap.put("saleOff", b.getSaleOff());
        hashMap.put("description", b.getDescription());
        hashMap.put("type", 1);
        hashMap.put("urlImage", b.getUrlImage()[0]);
        return hashMap;
    }

    public static HashMap<String, Object> creatBook(Book b) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("itemID", b.getId());
        hashMap.put("author", b.getAuthor());
        hashMap.put("publisher", b.getPublisher());
        hashMap.put("size", b.getSize());
        hashMap.put("year", b.getYear());
        return hashMap;
    }

    public static HashMap<String, Object> creatClothes(Clothes b) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("itemID", b.getId());
        hashMap.put("brand", b.getBrand());
        hashMap.put("color", b.getColor());
        hashMap.put("material", b.getMaterial());
        return hashMap;
    }

    public static HashMap<String, Object> creatElectronic(Electronics b) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("itemID", b.getId());
        hashMap.put("brand", b.getBrand());
        hashMap.put("color", b.getColor());
        return hashMap;
    }

    public static HashMap<String, Object> creatItem(Clothes b) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", b.getId());
        hashMap.put("name", b.getName());
        hashMap.put("price", b.getPrice());
        hashMap.put("saleOff", b.getSaleOff());
        hashMap.put("description", b.getDescription());
        hashMap.put("type", 1);
        hashMap.put("urlImage", b.getUrlImage()[0] + b.getUrlImage()[1] + b.getUrlImage()[2]);
        return hashMap;
    }

    public static HashMap<String, Object> creatItem(Electronics b) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", b.getId());
        hashMap.put("name", b.getName());
        hashMap.put("price", b.getPrice());
        hashMap.put("saleOff", b.getSaleOff());
        hashMap.put("description", b.getDescription());
        hashMap.put("type", 1);
        hashMap.put("urlImage", b.getUrlImage()[0] + b.getUrlImage()[1] + b.getUrlImage()[2]);
        return hashMap;
    }
}
