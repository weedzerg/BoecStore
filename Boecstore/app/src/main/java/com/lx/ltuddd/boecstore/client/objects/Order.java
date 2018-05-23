package com.lx.ltuddd.boecstore.client.objects;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Order {
    private String id;
    private ArrayList<Cart> lsCart;
    private boolean status;

    public Order() {
        lsCart=new ArrayList<>();
    }

    //false :dang van chuye
    //true: da van chuyen thanh cong
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
        int check = checkCart(cart.getItem());
        if (check == -1) {
            return this.lsCart.add(cart);
        } else {
            Cart c = this.lsCart.get(check);
            c.setQuantily(c.getQuantily() + cart.getQuantily());
            lsCart.set(check, c);
            return true;
        }
    }

    public int checkCart(Item i) {
        for (int p = 0; p < this.lsCart.size(); p++) {
            Cart c = this.lsCart.get(p);
            if (c.getItem().getId().equals(i.getId())) {
                return p;
            }
        }
        return -1;
    }
}
