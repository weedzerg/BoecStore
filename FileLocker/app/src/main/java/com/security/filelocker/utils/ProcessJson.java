package com.security.filelocker.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.security.filelocker.objects.InfoFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaiPhongPC on 9/13/2017.
 */

public class ProcessJson {

    public static ArrayList<InfoFile> JsontoArray(String json) {
        ArrayList<InfoFile> lsInfoPackages = new ArrayList<>();
        if (json != null) {
            Gson gson = new Gson();
            lsInfoPackages = gson.fromJson(json, new TypeToken<List<InfoFile>>() {
            }.getType());
        }
        return lsInfoPackages;
    }

    public static String ArrayToJson(ArrayList<InfoFile> lsInfoPackages) {
        if (lsInfoPackages != null) {
            Gson gson = new Gson();
            String json = gson.toJson(lsInfoPackages);
            return json;
        }
        return null;
    }

    public static int searchArray(ArrayList<String> lsInfoPackages, String namepackage) {
        for (int i = 0; i < lsInfoPackages.size(); i++) {
            if (lsInfoPackages.get(i).equals(namepackage)) return i;
        }
        return -1;
    }

    public static boolean searchArray_(ArrayList<String> lsInfoPackages, String namepackage) {
        for (int i = 0; i < lsInfoPackages.size(); i++) {
            if (lsInfoPackages.get(i).equals(namepackage)) return true;
        }
        return false;
    }
}
