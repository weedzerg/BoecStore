package com.funnystudio.whistleaction.model;

/**
 * Created by DaiPhongPC on 8/10/2017.
 */

public class ObjectCamera {
    private boolean status;
    // 0: no choice
    // 1: choice whistle
    // 2: choice clap

    public ObjectCamera() {
    }


    public ObjectCamera(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
