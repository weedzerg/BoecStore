package com.blogspot.quanlytomatoads.HKCache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DaiPhongPC on 3/15/2017.
 */

public class Cache {
    public static SharedPreferences cache = null;
    public static Cache instance = null;
    static Context context;

    public Cache(Context context) {
        this.context = context;
        cache = context.getSharedPreferences("linkcache", Context.MODE_PRIVATE);
        instance = this;
    }

    public static Cache getInstance() {
        if (cache == null) {
            instance = new Cache(context);
        }
        return instance;
    }

    public void putLink(String pac, String[] link) {
        String link_ = ghepmang(link);
        SharedPreferences.Editor editor = cache.edit();
        editor.putString(pac, link_);
        editor.commit();
    }

    public String [] getLink(String pac) {
       String getc=cache.getString(pac,null);
        return tachxau(getc);
    }

    public void clear() {
        cache.edit().clear().commit();
    }

    public void setCahe(SharedPreferences cahe) {
        this.cache = cahe;
    }

    public String ghepmang(String[] s) {
        String xau = "";
        for (int i = 0; i < s.length - 1; i++) {
            xau += s[i] + ";";
        }
        xau += s[s.length - 1];
        return xau;
    }
    public String [] tachxau(String s){
        if (s!=null){
            String [] slip_=s.split(";");
            return slip_;
        }
        return null;
    }
}
