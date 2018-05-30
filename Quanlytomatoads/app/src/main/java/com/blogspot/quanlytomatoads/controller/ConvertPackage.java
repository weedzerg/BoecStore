package com.blogspot.quanlytomatoads.controller;

/**
 * Created by DaiPhongPC on 4/8/2017.
 */

public class ConvertPackage {
    public static String packagetoKey(String s){
        String result=s.replace(".","@");
        return result;
    }
    public static String packagetoValues(String s){
        String result=s.replace("@",".");
        return result;
    }
}
