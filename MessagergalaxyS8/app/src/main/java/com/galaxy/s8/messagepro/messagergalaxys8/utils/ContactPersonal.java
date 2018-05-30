package com.galaxy.s8.messagepro.messagergalaxys8.utils;

/**
 * Created by DaiPhongPC on 9/26/2017.
 */

public class ContactPersonal {
    private String name;
    private String number;

    public ContactPersonal(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
