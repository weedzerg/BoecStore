package com.blogspot.quanlytomatoads.common;


import com.blogspot.quanlytomatoads.model.TTappstore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by DevPro on 6/23/2017.
 */

public class Common {
    public static final DatabaseReference data = FirebaseDatabase.getInstance().getReference("DEBUG");
    public static final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public static ArrayList<TTappstore> filterapp(ArrayList<TTappstore> ls, int trangthai) {
        ArrayList<TTappstore> result = new ArrayList<>();
        for (TTappstore app : ls) {
            if (app.getTrangthai() == trangthai) {
                result.add(app);
            }
        }
        return result;
    }
}
