package com.security.filelocker.utils;

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

    public void putLink(String pac, String value) {
        SharedPreferences.Editor editor = cache.edit();
        editor.putString(pac, value);
        editor.commit();
    }

    public String getLink(String pac) {
       String getc=cache.getString(pac,null);
        return getc;
    }

    public void clear() {
        cache.edit().clear().commit();
    }

    public void setCahe(SharedPreferences cahe) {
        this.cache = cahe;
    }

}
