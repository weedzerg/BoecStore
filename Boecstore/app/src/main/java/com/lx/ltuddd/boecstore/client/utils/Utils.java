package com.lx.ltuddd.boecstore.client.utils;

import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.objects.Item;

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
}
