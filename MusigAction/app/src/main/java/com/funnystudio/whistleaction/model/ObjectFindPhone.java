package com.funnystudio.whistleaction.model;

/**
 * Created by DaiPhongPC on 8/10/2017.
 */

public class ObjectFindPhone extends ObjectCamera {
    private ObjectMusic ring;
    private boolean flash;

    public ObjectFindPhone() {
    }

    public ObjectFindPhone(boolean status,
                           ObjectMusic ring, boolean flash) {
        super(status);
        this.ring = ring;
        this.flash = flash;
    }

    public ObjectMusic getRing() {
        return ring;
    }

    public void setRing(ObjectMusic ring) {
        this.ring = ring;
    }

    public boolean isFlash() {
        return flash;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }
}
