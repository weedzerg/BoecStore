package com.security.filelocker.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import com.security.filelocker.interfaces.UploadDecodeFile;
import com.security.filelocker.objects.InfoFile;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/12/2017.
 */

public class AsynTaskLoadData extends AsyncTask<Void, String, ArrayList<InfoFile>> {
    private LinearLayout progressBar;
    private int value;
    private Activity activity;
    private UploadDecodeFile uploadDecodeFile;

    public AsynTaskLoadData(int value, Activity activity, UploadDecodeFile uploadDecodeFile) {
        this.value = value;
        this.activity = activity;
        this.uploadDecodeFile = uploadDecodeFile;
    }

    public void setProgressBar(LinearLayout progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected ArrayList<InfoFile> doInBackground(Void... arrayLists) {
        ArrayList<InfoFile> lsFile = null;
        switch (value) {
            case 0: {
                lsFile=FileExternalStorage.getAllFileImage(activity);
                break;
            }
            case 1: {
                lsFile=FileExternalStorage.getAllFileMusic(activity);

                break;
            }
            case 2: {
                lsFile=FileExternalStorage.getAllFileVideo(activity);
                break;
            }
        }
        return lsFile;
    }

    @Override
    protected void onPostExecute(ArrayList<InfoFile> ls) {
        progressBar.setVisibility(View.GONE);
        if (ls != null) {
            uploadDecodeFile.uploadFile(ls);

        } else {

        }
    }

}
