package com.blogspot.quanlytomatoads.model;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 4/10/2017.
 */

public class TTappstore implements Serializable {
    private String namepackage;
    private String namedev;
    private String iddev;
    private int trangthai;
    private long timestart;

    public TTappstore() {
    }

    public TTappstore(String namepackage, String namedev, String iddev, int trangthai, long timestart) {
        this.namepackage = namepackage;
        this.namedev = namedev;
        this.iddev = iddev;
        this.trangthai = trangthai;
        this.timestart = timestart;
    }

    public String getNamepackage() {
        return namepackage;
    }

    public void setNamepackage(String namepackage) {
        this.namepackage = namepackage;
    }

    public String getNamedev() {
        return namedev;
    }

    public void setNamedev(String namedev) {
        this.namedev = namedev;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public long getTimestart() {
        return timestart;
    }

    public void setTimestart(long timestart) {
        this.timestart = timestart;
    }

    public String getIddev() {
        return iddev;
    }

    public void setIddev(String iddev) {
        this.iddev = iddev;
    }
}
