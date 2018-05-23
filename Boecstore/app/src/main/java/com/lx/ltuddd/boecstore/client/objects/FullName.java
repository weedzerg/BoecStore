package com.lx.ltuddd.boecstore.client.objects;

/**
 * Created by DaiPhongPC on 5/24/2018.
 */

public class FullName {
    private String firstName, lastName, midName;

    public FullName(String firstName, String lastName, String midName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }
}
