package com.galaxy.s8.messagepro.messagergalaxys8.objects;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 9/26/2017.
 */

public class BodySMS implements Serializable {
    private String body;
    private long date;
    private String type;
    private int checksent;

    public BodySMS(String body, long date, String type, int checksent) {
        this.body = body;
        this.date = date;
        this.type = type;
        this.checksent = checksent;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getChecksent() {
        return checksent;
    }

    public void setChecksent(int checksent) {
        this.checksent = checksent;
    }
}
