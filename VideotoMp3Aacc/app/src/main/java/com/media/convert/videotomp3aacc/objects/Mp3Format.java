package com.media.convert.videotomp3aacc.objects;

/**
 * Created by DaiPhongPC on 11/13/2017.
 */

public class Mp3Format {
    private String namemp3;
    private String pathin;
    private String pathout;
    private String typeFormat;//aac,mp3
    private long qualitys;

    public Mp3Format() {
    }

    public Mp3Format(String pathin, String typeFormat, long qualitys) {
        this.pathin = pathin;
        this.typeFormat = typeFormat;
        this.qualitys = qualitys;
    }

    public String getNamemp3() {
        return namemp3;
    }

    public void setNamemp3(String namemp3) {
        this.namemp3 = namemp3;
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

    public String getTypeFormat() {
        return typeFormat;
    }

    public void setTypeFormat(String typeFormat) {
        this.typeFormat = typeFormat;
    }

    public long getQualitys() {
        return qualitys;
    }

    public void setQualitys(long qualitys) {
        this.qualitys = qualitys;
    }
}
