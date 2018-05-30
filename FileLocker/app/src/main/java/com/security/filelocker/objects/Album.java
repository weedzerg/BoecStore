package com.security.filelocker.objects;

import java.util.ArrayList;

/**
 * Created by Peih Gnaoh on 8/20/2017.
 */

public class Album {
    private String idAlbum;
    private String bucket;
    private String pathFirstImage;
    private ArrayList<InfoFile> arrImage;

    public Album(String bucket, String pathFirstImage, ArrayList<InfoFile> arrImage) {
        this.bucket = bucket;
        this.pathFirstImage = pathFirstImage;
        this.arrImage = arrImage;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public void addImage(InfoFile image) {
        arrImage.add(image);
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getPathFirstImage() {
        return pathFirstImage;
    }

    public void setPathFirstImage(String pathFirstImage) {
        this.pathFirstImage = pathFirstImage;
    }

    public ArrayList<InfoFile> getArrImage() {
        return arrImage;
    }

    public void setArrImage(ArrayList<InfoFile> arrImage) {
        this.arrImage = arrImage;
    }
}
