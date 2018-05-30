package com.galaxy.s8.messagepro.messagergalaxys8.inerfaces;

import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoContact;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoSender;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.SenderSMS;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DaiPhongPC on 9/26/2017.
 */

public interface UploadSMS {
    void uploadListViewSender(ArrayList<InfoSender> lsendeer,boolean check);
    void uploadDataSMS(HashMap<String,SenderSMS> map);
    void uploadContacts(HashMap<String,InfoContact> lsContacts);
}
