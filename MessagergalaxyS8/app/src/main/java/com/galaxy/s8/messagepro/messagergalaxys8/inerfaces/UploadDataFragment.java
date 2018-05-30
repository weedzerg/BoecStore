package com.galaxy.s8.messagepro.messagergalaxys8.inerfaces;

import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoSender;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.SenderSMS;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DaiPhongPC on 9/30/2017.
 */

public interface UploadDataFragment {
    void dataSMS(ArrayList<InfoSender> lsSender,boolean check);

    void dataContact(ArrayList<String> lsContact);

    void dataSMSBody(HashMap<String, SenderSMS> mapBody);
}
