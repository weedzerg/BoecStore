/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesOrder;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nguye
 */
public class Cart {
    private HashMap<Long, Item> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public Cart(HashMap<Long, Item> cartItems) {
        this.cartItems = cartItems;
    }

    public HashMap<Long, Item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<Long, Item> cartItems) {
        this.cartItems = cartItems;
    }

    // insert to cart
    public void plusToCart(Long key, Item item) {
        boolean check = cartItems.containsKey(key);
        if (check) {
            int quantity_old = item.getQuantity();
            item.setQuantity(quantity_old + 1);
            cartItems.put(key, item);
        } else {
            cartItems.put(key, item);
        }
    }

    // sub to cart
    public void subToCart(Long key, Item item) {
        boolean check = cartItems.containsKey(key);
        if (check) {
            int quantity_old = item.getQuantity();
            if (quantity_old <= 1) {
                cartItems.remove(key);
            } else {
                item.setQuantity(quantity_old - 1);
                cartItems.put(key, item);
            }
        }
    }

    // remove to cart
    public void removeToCart(Long key) {
        boolean check = cartItems.containsKey(key);
        if (check) {
            cartItems.remove(key);
        }
    }

    // count item
    public int countItem() {
        return cartItems.size();
    }

    // sum total 
    public double totalCart() {
        double count = 0;
        // count = price * quantity
        for (Map.Entry<Long, Item> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
        }
        return count;
    }

    public double total() {
        double count = 0;
        for (Map.Entry<Long, Item> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
        }
        return count;
    }

    public double tax() {
        double count = 0;
        for (Map.Entry<Long, Item> list : cartItems.entrySet()) {
            count = list.getValue().getProduct().getPrice() * 0.05;
        }
        double roundOff = Math.round(count * 100) / 100;
        return roundOff;
    }

    public double sum() {
        double count = 0;
        for (Map.Entry<Long, Item> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity() + 3.6 + 15000;
        }
        return count;
    }
}
