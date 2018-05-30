package com.funnystudio.whistleaction.model;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 8/10/2017.
 */

public class ObjectMusic implements Serializable {
    private boolean checkringDefault;
    private int idlink;
    private boolean check;
    private String namemusic;
    private String filename;

    public ObjectMusic(boolean checkringDefault, int idlink, String namemusic, String filename) {
        this.checkringDefault = checkringDefault;
        this.idlink = idlink;
        this.namemusic = namemusic;
        this.filename = filename;
    }

    public boolean isCheckringDefault() {
        return checkringDefault;
    }

    public void setCheckringDefault(boolean checkringDefault) {
        this.checkringDefault = checkringDefault;
    }

    public ObjectMusic(boolean check, String namemusic, String filename) {
        this.check = check;
        this.namemusic = namemusic;
        this.filename = filename;
    }

    public ObjectMusic(int idlink, String namemusic) {
        this.idlink = idlink;
        this.namemusic = namemusic;
    }

    public ObjectMusic() {

    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getIdlink() {
        return idlink;
    }

    public void setIdlink(int idlink) {
        this.idlink = idlink;
    }

    public String getNamemusic() {
        return namemusic;
    }

    public void setNamemusic(String namemusic) {
        this.namemusic = namemusic;
    }
}
