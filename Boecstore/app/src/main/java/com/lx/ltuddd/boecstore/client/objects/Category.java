package com.lx.ltuddd.boecstore.client.objects;

import java.io.Serializable;

/**
 * Created by loanx on 2/12/2018.
 */

public class Category implements Serializable {
    private int idCategory;
    private String nameCategory;

    public Category(int idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
