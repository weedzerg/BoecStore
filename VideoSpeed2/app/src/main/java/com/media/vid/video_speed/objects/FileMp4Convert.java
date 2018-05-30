package com.media.vid.video_speed.objects;

/**
 * Created by DaiPhongPC on 11/28/2017.
 */

public class FileMp4Convert {
    private String pathin;
    private String pathout;
    private boolean slomotion;
    private float speed;
    private boolean audio;

    public FileMp4Convert() {
    }

    public FileMp4Convert(String pathin, String pathout,
                          boolean slomotion, float speed, boolean audio) {
        this.pathin = pathin;
        this.pathout = pathout;
        this.slomotion = slomotion;
        this.speed = speed;
        this.audio = audio;
    }

    public String getPathin() {
        return pathin;
    }

    public void setPathin(String pathin) {
        this.pathin = pathin;
    }

    public String getPathout() {
        return pathout;
    }

    public void setPathout(String pathout) {
        this.pathout = pathout;
    }

    public boolean isSlomotion() {
        return slomotion;
    }

    public void setSlomotion(boolean slomotion) {
        this.slomotion = slomotion;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isAudio() {
        return audio;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }
}
