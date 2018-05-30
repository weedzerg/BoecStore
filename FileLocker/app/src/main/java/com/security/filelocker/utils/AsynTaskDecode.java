package com.security.filelocker.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
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

public class AsynTaskDecode extends AsyncTask<ArrayList<InfoFile>, String, ArrayList<InfoFile>> {
    private LinearLayout progressBar;
    private TextView tv_procsss;
    private Cache cache;
    private String key;
    private String value;
    private Activity activity;
    private UploadDecodeFile uploadDecodeFile;

    public AsynTaskDecode(Cache cache, String key, String value,
                          Activity activity, UploadDecodeFile uploadDecodeFile) {
        this.cache = cache;
        this.key = key;
        this.value = value;
        this.activity = activity;
        this.uploadDecodeFile = uploadDecodeFile;
    }

    public void setProgressBar(LinearLayout progressBar) {
        this.progressBar = progressBar;
    }

    public void setTv_procsss(TextView tv_procsss) {
        this.tv_procsss = tv_procsss;
    }

    @Override
    protected ArrayList<InfoFile> doInBackground(ArrayList<InfoFile>... arrayLists) {
        try {
            ArrayList<InfoFile> lsdecode = arrayLists[0];
            ArrayList<InfoFile> result = decodeFile(lsdecode);
            if (result != null) {
                try {
                    ArrayList<InfoFile> lsSecurity = ProcessJson.JsontoArray(cache.getLink(value));
                    lsSecurity = Utils.removeArrayList(lsSecurity, lsdecode);
                    cache.putLink(value, ProcessJson.ArrayToJson(lsSecurity));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList aBoolean) {
        progressBar.setVisibility(View.GONE);
        if (aBoolean!=null) {
            //tro ve man hinh chinh
            uploadDecodeFile.uploadListview(aBoolean);
            Log.d("DEBUG", "ma hoa thanh cong");
        } else {
            Log.d("DEBUG", "ma hoa loi");
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        tv_procsss.setText(activity.getString(R.string.processing) + " " + values[0]);
    }

    public ArrayList<InfoFile> decodeFile(ArrayList<InfoFile> lsFile) {
        for (int i = 0; i < lsFile.size(); i++) {
            InfoFile s = lsFile.get(i);
            try {
                FileInputStream in = new FileInputStream(s.getPathfile());
                if (Utils.isMicroSD(s.getPathfile())) {
                    String sKEY = Utils.convertToUri(Contants.PATH_DOCUMENT, s.getPathfile());
                    String namefile = (Utils.getNameFile(s.getPathfile())).substring(1);
                    Uri uriDecode = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        uriDecode = DocumentsContract.buildDocumentUriUsingTree(Contants.uriSD,
                                Utils.convertToUriDecode(Contants.PATH_DOCUMENT, s.getPathfile()));
                        if (s.isStatus()) {
                            Uri uri = DocumentContactsFile.createDirectory(activity, Contants.uriSD, sKEY,
                                    (Utils.getNameFile(s.getPathfile())).substring(1), s.getMineType());
                            FileOutputStream out = DocumentContactsFile.alterDocument(activity, uri);
                            SecurityFile.decrypt(key, in, out);
                            DocumentsContract.deleteDocument(activity.getContentResolver(), uriDecode);

                        } else {
                            DocumentContactsFile.renameDocumentContanst(activity.getContentResolver(), uriDecode, namefile);
                        }
                    } else {
                        FileOutputStream out = new FileOutputStream(Utils.convertPathFile(s.getPathfile()));
                        SecurityFile.decrypt(key, in, out);
                        Utils.removeFile(s.getPathfile());
                    }
                    activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(new File(s.getPathfile()))));
                    activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(new File(Utils.convertPathFile1(s.getPathfile())))));
                } else {
                    if (s.isStatus()) {
                        FileOutputStream out = new FileOutputStream(Utils.convertPathFile1(s.getPathfile()));
                        SecurityFile.decrypt(key, in, out);
                        Utils.removeFile(s.getPathfile());
                    } else {
                        File f1 = new File(s.getPathfile());
                        File f2 = new File(Utils.convertPathFile1(s.getPathfile()));
                        boolean t = f1.renameTo(f2);
                    }
                    activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(new File(s.getPathfile()))));
                    activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(new File(Utils.convertPathFile1(s.getPathfile())))));

                }
                publishProgress((i + 1) + "/" + lsFile.size());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        return lsFile;
    }

}
