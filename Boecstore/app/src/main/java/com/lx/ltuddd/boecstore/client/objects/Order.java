package com.lx.ltuddd.boecstore.client.objects;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Order {
    private String id;
    private ArrayList<Cart> lsCart;
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Cart> getLsCart() {
        return lsCart;
    }

    public void setLsCart(ArrayList<Cart> lsCart) {
        this.lsCart = lsCart;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean addCart(Cart cart) {
        return this.lsCart.add(cart);
    }

}
