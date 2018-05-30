package com.media.vid.video_speed.objects;

import java.io.Serializable;

/**
 * Created by DaiPhongPC on 9/11/2017.
 */

public class InfoFile implements Serializable {
    private String namefile;
    private String pathfile;
    private long duration;
    private long size;

    public InfoFile(String namefile, String pathfile, long duration, long size) {
        this.namefile = namefile;
        this.pathfile = pathfile;
        this.duration = duration;
        this.size = size;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
