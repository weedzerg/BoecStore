package com.blogspot.quanlytomatoads.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/30/2017.
 */

public class TTdev implements Serializable {
    private String name, userid, linkavatar;
    private String loaicount;
    private int diem;
    private ArrayList<TTMypackage> mypackage;

    public TTdev() {
    }

    public TTdev(String name, String userid, String linkavatar, String loaicount, int diem) {
        this.name = name;
        this.userid = userid;
        this.linkavatar = linkavatar;
        this.loaicount = loaicount;
        this.diem = diem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLinkavatar() {
        return linkavatar;
    }

    public void setLinkavatar(String linkavatar) {
        this.linkavatar = linkavatar;
    }

    public String getLoaicount() {
        return loaicount;
    }

    public void setLoaicount(String loaicount) {
        this.loaicount = loaicount;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public ArrayList<TTMypackage> getMypackage() {
        return mypackage;
    }

    public void setMypackage(ArrayList<TTMypackage> mypackage) {
        this.mypackage = mypackage;
    }
}
