package com.security.filelocker.objects;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 9/11/2017.
 */

public class InfoFile implements Serializable {
    private String nameAlbum;
    private String namefile;
    private String pathfile;
    private int size;
    private String date;
    private boolean check;
    private String pathfileCache;
    private String mineType;
    private String idAlbum;
    private boolean status;
    private String bucket;

    //true ẩn + mã hóa if(size<5MB)
    //false ẩn if(size>5MB)
    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public InfoFile(String nameAlbum, String namefile,
                    String pathfile, int size, String date,
                    boolean check, String mineType, boolean status) {
        this.nameAlbum = nameAlbum;
        this.namefile = namefile;
        this.pathfile = pathfile;
        this.size = size;
        this.date = date;
        this.check = check;
        this.mineType = mineType;
        this.status = status;
    }

    public InfoFile(String namefile, String pathfile, int size, String date, boolean check, String mineType) {
        this.namefile = namefile;
        this.pathfile = pathfile;
        this.size = size;
        this.date = date;
        this.check = check;
        this.mineType = mineType;
    }

    public InfoFile(String nameAlbum, String namefile, String pathfile, int size, String date) {
        this.nameAlbum = nameAlbum;
        this.namefile = namefile;
        this.pathfile = pathfile;
        this.size = size;
        this.date = date;
    }

    public InfoFile(String namefile, String pathfile, boolean check) {
        this.namefile = namefile;
        this.pathfile = pathfile;
        this.check = check;
    }

    public InfoFile() {
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMineType(String mineType) {
        this.mineType = mineType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getNamefile() {
        return namefile;
    }


    public String getPathfile() {
        return pathfile;
    }

    public void setPathfile(String pathfile) {
        this.pathfile = pathfile;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getPathfileCache() {
        return pathfileCache;
    }

    public void setPathfileCache(String pathfileCache) {
        this.pathfileCache = pathfileCache;
    }

    public String getMineType() {
        return mineType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
