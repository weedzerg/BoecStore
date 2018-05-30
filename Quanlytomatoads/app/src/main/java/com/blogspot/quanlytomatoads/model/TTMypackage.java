package com.blogspot.quanlytomatoads.model;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 7/7/2017.
 */

public class TTMypackage implements Serializable {
    private String namepackage;
    private int download;
    private int trangthai;

    public TTMypackage(String namepackage, int download, int trangthai) {
        this.namepackage = namepackage;
        this.download = download;
        this.trangthai = trangthai;
    }

    public String getNamepackage() {
        return namepackage;
    }

    public void setNamepackage(String namepackage) {
        this.namepackage = namepackage;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
