package com.galaxy.s8.messagepro.messagergalaxys8.objects;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 9/26/2017.
 */

public class InfoSender implements Serializable {
    private String nameSender;
    private String numberSender;
    private Long date;
    private String body;

    public InfoSender() {
    }

    public InfoSender(String nameSender, String numberSender) {
        this.nameSender = nameSender;
        this.numberSender = numberSender;
    }

    public InfoSender(String numberSender, Long date, String body) {
        this.numberSender = numberSender;
        this.date = date;
        this.body = body;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public String getNumberSender() {
        return numberSender;
    }

    public void setNumberSender(String numberSender) {
        this.numberSender = numberSender;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
