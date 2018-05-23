package com.lx.ltuddd.boecstore.client.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lx.ltuddd.boecstore.client.objects.Order;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Contants {
    public static final String DATAINTENT = "dataintent";
    public static final String BOOK = "book";
    public static final String CLOTHES = "clothes";
    public static final String ELECTRONIC = "electonic";
    public static Order order = null;
    public static final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
}
