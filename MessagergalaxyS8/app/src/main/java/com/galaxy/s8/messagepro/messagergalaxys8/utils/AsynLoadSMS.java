package com.galaxy.s8.messagepro.messagergalaxys8.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.galaxy.s8.messagepro.messagergalaxys8.inerfaces.UploadSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.BodySMS;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoContact;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoSender;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.SenderSMS;

import java.util.ArrayList;
import java.util.HashMap;

import static com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants.SMS_URI_ALL;

/**
 * Created by DaiPhongPC on 9/26/2017.
 */

public class AsynLoadSMS extends AsyncTask<Void, ArrayList<InfoSender>, Void> {
    private UploadSMS uploadSMS;
    private Activity activity;
    private HashMap<String, InfoContact> mContact = new HashMap<>();
    private HashMap<String, SenderSMS> mapSender = new HashMap<>();
    private ArrayList<InfoSender> lsSender = new ArrayList<>();

    public AsynLoadSMS(UploadSMS uploadSMS, Activity activity) {
        this.uploadSMS = uploadSMS;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (Contants.lscontacts.size() == 0) {
            ActionSMS.getContactList(activity, mContact);
            Log.d("DANHBA", "load danh ba");
        } else {
            mContact = Contants.mapcontacts;
        }
        getListSMS(activity, mapSender, lsSender, mContact);
        return null;
    }

    @Override
    protected void onProgressUpdate(ArrayList<InfoSender>... values) {
        super.onProgressUpdate(values);
        uploadSMS.uploadListViewSender(values[0], false);
    }

    @Override
    protected void onPostExecute(Void voids) {
        super.onPostExecute(voids);
        uploadSMS.uploadListViewSender(lsSender, true);
        uploadSMS.uploadDataSMS(mapSender);
        uploadSMS.uploadContacts(mContact);
        this.cancel(true);
    }

    private void getListSMS(Activity activity, HashMap<String,
            SenderSMS> hashSender, ArrayList<InfoSender> lsSender, HashMap<String, InfoContact> mapContact) {
        hashSender.clear();
        lsSender.clear();
        Uri uriSMSURI = Uri.parse(SMS_URI_ALL);
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
        Cursor cur = activity.getContentResolver().query(uriSMSURI, projection, null, null, null);
        int columAddress = cur.getColumnIndex("address");
        int columBody = cur.getColumnIndex("body");
        int columDate = cur.getColumnIndex("date");
        int columType = cur.getColumnIndex("type");
        while (cur != null && cur.moveToNext()) {
            String address = Utils.checkNumberPhone(cur.getString(columAddress));
            String body = cur.getString(columBody);
            long date = Long.parseLong(cur.getString(columDate));
            String name = null;
            try {
                name = mapContact.get(address.replace(" ", "")).getNameContact();
            } catch (Exception e) {
                e.printStackTrace();
                name = address;
            }
            if (name == null) {
                name = address;
            }

            String type = cur.getString(columType);
            //1: sender
            //2: me

            BodySMS bodySMS = new BodySMS(body, date, type, 1);
            if (hashSender.containsKey(address)) {
                hashSender.get(address).getLsbody().add(bodySMS);
            } else {
                ArrayList<BodySMS> ls = new ArrayList<>();
                ls.add(bodySMS);
                hashSender.put(address, new SenderSMS(name, address, ls));
                InfoSender infoSender = new InfoSender(name, address);
                infoSender.setBody(body);
                infoSender.setDate(date);
                lsSender.add(infoSender);
//                publishProgress(lsSender);
            }
        }
        if (cur != null) {
            cur.close();
        }
    }
}
