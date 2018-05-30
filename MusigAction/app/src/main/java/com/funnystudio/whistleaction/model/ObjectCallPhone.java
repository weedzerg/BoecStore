package com.funnystudio.whistleaction.model;

/**
 * Created by DaiPhongPC on 8/10/2017.
 */

public class ObjectCallPhone extends ObjectCamera {
    private String numberphone;

    public ObjectCallPhone() {
    }

    public ObjectCallPhone(boolean status,
                           String numberphone) {
        super(status);
        this.numberphone = numberphone;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }
}
