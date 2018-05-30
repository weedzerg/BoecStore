package com.security.filelocker.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.security.filelocker.interfaces.UploadDecodeFile;
import com.security.filelocker.objects.InfoFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/12/2017.
 */

public class AsynTaskDecodeShow extends AsyncTask<ArrayList<InfoFile>, Void, ArrayList<InfoFile>> {
    private LinearLayout progressBar;
    private String key;
    private UploadDecodeFile uploadDecodeFile;

    public AsynTaskDecodeShow(LinearLayout progressBar, String key,
                              UploadDecodeFile uploadDecodeFile) {
        this.progressBar = progressBar;
        this.key = key;
        this.uploadDecodeFile = uploadDecodeFile;
    }


    @Override
    protected ArrayList<InfoFile> doInBackground(ArrayList<InfoFile>... arrayLists) {
        try {
            ArrayList<InfoFile> lsSecurity = arrayLists[0];
            return decodeFile(lsSecurity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<InfoFile> ls) {
        progressBar.setVisibility(View.GONE);
        if (ls != null) {
            uploadDecodeFile.uploadFile(ls);
            Log.d("DEBUG", "ma hoa thanh cong");
        } else {
            Log.d("DEBUG", "ma hoa loi");
        }
    }

    public ArrayList<InfoFile> decodeFile(ArrayList<InfoFile> lsFile) {
        for (int i = 0; i < lsFile.size(); i++) {
            InfoFile s = lsFile.get(i);
            if (s.isStatus()) {
                try {
                    FileInputStream in = new FileInputStream(s.getPathfile());
                    FileOutputStream out = new FileOutputStream(Contants.PATH + i + Utils.getNameFile(s.getPathfile()));
                    SecurityFile.decrypt(key, in, out);
                    lsFile.get(i).setPathfileCache(s.getPathfile());
                    lsFile.get(i).setPathfile(Contants.PATH + i + Utils.getNameFile(s.getPathfile()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                lsFile.get(i).setPathfileCache(s.getPathfile());
                lsFile.get(i).setPathfile(s.getPathfile());

            }

        }
        return lsFile;
    }
}
