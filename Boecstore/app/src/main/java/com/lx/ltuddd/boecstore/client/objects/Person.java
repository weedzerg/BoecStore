package com.lx.ltuddd.boecstore.client.objects;

import java.util.Date;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Person {
    private String id;
    private Date doD;
    private String sex;
    private String number;
    private String email;
    private String urlImage;
    private Address address;
    private Account account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDoD() {
        return doD;
    }

    public void setDoD(Date doD) {
        this.doD = doD;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
