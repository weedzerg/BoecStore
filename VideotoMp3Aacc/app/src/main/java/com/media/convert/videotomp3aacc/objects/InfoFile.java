package com.media.convert.videotomp3aacc.objects;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 9/11/2017.
 */

public class InfoFile implements Serializable {
    private String nameAlbum;
    private String namefile;
    private String pathfile;
    private float size;
    private String date;

    public InfoFile(String namefile, String pathfile) {
        this.namefile = namefile;
        this.pathfile = pathfile;
    }

    public InfoFile(String nameAlbum, String namefile, String pathfile, float size, String date) {
        this.nameAlbum = nameAlbum;
        this.namefile = namefile;
        this.pathfile = pathfile;
        this.size = size;
        this.date = date;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getNamefile() {
        return namefile;
    }

    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }

    public String getPathfile() {
        return pathfile;
    }

    public void setPathfile(String pathfile) {
        this.pathfile = pathfile;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
