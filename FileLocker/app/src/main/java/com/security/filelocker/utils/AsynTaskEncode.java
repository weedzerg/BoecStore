package com.security.filelocker.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.security.filelocker.R;
import com.security.filelocker.interfaces.UploadDecodeFile;
import com.security.filelocker.objects.InfoFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/12/2017.
 */

public class AsynTaskEncode extends AsyncTask<ArrayList<InfoFile>, String, ArrayList<InfoFile>> {
    private LinearLayout progressBar;
    private TextView tv_process;
    private Cache cache;
    private String key;
    private String style;
    private Activity activity;
    private UploadDecodeFile uploadDecodeFile;

    public AsynTaskEncode(Cache cache, String key, String style,
                          Activity activity, UploadDecodeFile uploadDecodeFile) {
        this.cache = cache;
        this.key = key;
        this.style = style;
        this.activity = activity;
        this.uploadDecodeFile = uploadDecodeFile;
    }

    public void setProgressBar(LinearLayout progressBar) {
        this.progressBar = progressBar;
    }

    public void setTv_process(TextView tv_process) {
        this.tv_process = tv_process;
    }

    @Override
    protected ArrayList<InfoFile> doInBackground(ArrayList<InfoFile>... arrayLists) {
        try {
            ArrayList<InfoFile> lsEncode = arrayLists[0];
            return securityFile(lsEncode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<InfoFile> lsEncode) {
        progressBar.setVisibility(View.GONE);
        if (lsEncode != null) {
            ArrayList<InfoFile> lscache = getListSecurity();
            if (lscache == null) {
                lscache = lsEncode;
            } else {
                lscache.addAll(lsEncode);
            }
            String json = ProcessJson.ArrayToJson(lscache);
            cache.putLink(style, json);
            //tro ve main activity
            uploadDecodeFile.uploadFile(lsEncode);
            Log.d("DEBUG", "ma hoa thanh cong");
        } else {
            Log.d("DEBUG", "ma hoa loi");
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        tv_process.setText(activity.getString(R.string.processing) + " " + values[0]);
    }

    public ArrayList<InfoFile> getListSecurity() {
        try {
            return ProcessJson.JsontoArray(cache.getLink(style));
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<InfoFile> securityFile(ArrayList<InfoFile> lsFile) {
        for (int i = 0; i < lsFile.size(); i++) {
            try {
                InfoFile s = lsFile.get(i);
                FileInputStream in = new FileInputStream(s.getPathfile());
                if (Utils.isMicroSD(s.getPathfile())) {
                    String sKEY = Utils.convertToUri(Contants.PATH_DOCUMENT, s.getPathfile());
                    String namefileEN = "." + Utils.getNameFile(s.getPathfile());

                    Uri uriDecode = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        uriDecode = DocumentsContract.buildDocumentUriUsingTree(Contants.uriSD,
                                Utils.convertToUriDecode(Contants.PATH_DOCUMENT, s.getPathfile()));
                        if (s.isStatus()) {
                            Uri uriencode = DocumentContactsFile.createDirectory(activity, Contants.uriSD, sKEY,
                                    namefileEN, s.getMineType());
                            FileOutputStream out = DocumentContactsFile.alterDocument(activity, uriencode);
                            SecurityFile.encrypt(key, in, out);
                            DocumentsContract.deleteDocument(activity.getContentResolver(), uriDecode);
                        } else {
                            DocumentContactsFile.renameDocumentContanst(activity.getContentResolver(), uriDecode, namefileEN);
                        }
                    }

                } else {
                    if (s.isStatus()) {
                        FileOutputStream out = new FileOutputStream(Utils.convertPathFile(s.getPathfile()));
                        SecurityFile.encrypt(key, in, out);
                        Utils.removeFile(s.getPathfile());
                    } else {
                        File f1 = new File(s.getPathfile());
                        File f2 = new File(Utils.convertPathFile(s.getPathfile()));
                        boolean t = f1.renameTo(f2);
                        Log.d("DEBUG", t + "");
                    }

                }
                activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(s.getPathfile()))));
                lsFile.get(i).setPathfile(Utils.convertPathFile(s.getPathfile()));
                activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(s.getPathfile()))));
                lsFile.get(i).setCheck(false);
                publishProgress((i + 1) + "/" + lsFile.size());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return lsFile;
    }


}
