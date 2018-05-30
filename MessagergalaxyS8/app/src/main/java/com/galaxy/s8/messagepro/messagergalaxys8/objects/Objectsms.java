package com.galaxy.s8.messagepro.messagergalaxys8.objects;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 10/6/2017.
 */

public class Objectsms implements Serializable {
    private String number;
    private String body;

    public Objectsms(String number, String body) {
        this.number = number;
        this.body = body;
    }

    public String getNumber() {
        return number;
    }

    public String getBody() {
        return body;
    }
}
