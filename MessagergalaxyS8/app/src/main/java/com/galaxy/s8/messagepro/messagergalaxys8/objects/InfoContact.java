package com.galaxy.s8.messagepro.messagergalaxys8.objects;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 9/30/2017.
 */

public class InfoContact implements Serializable {
    private String idContact;
    private String nameContact;
    private String numContact;
    private Uri uri;
    private boolean onclick;

    public InfoContact(String idContact, String nameContact, String numContact) {
        this.idContact = idContact;
        this.nameContact = nameContact;
        this.numContact = numContact;
    }

    public InfoContact(String idContact, String nameContact, String numContact, Uri uri) {
        this.idContact = idContact;
        this.nameContact = nameContact;
        this.numContact = numContact;
        this.uri = uri;
    }

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getNumContact() {
        return numContact;
    }

    public void setNumContact(String numContact) {
        this.numContact = numContact;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public boolean isOnclick() {
        return onclick;
    }

    public void setOnclick(boolean onclick) {
        this.onclick = onclick;
    }
}
