package com.galaxy.s8.messagepro.messagergalaxys8.utils;

import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoContact;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DaiPhongPC on 9/26/2017.
 */

public class Contants {
    public static final String SMS_URI_INBOX = "content://sms/inbox";
    public static final String SMS_URI_ALL = "content://sms/";
    public static final String SMS_URI_SENT = "content://sms/sent";
    public static final String BODY_SMS_ACTIVITY = "sendbodysms";
    public static final String INTENT_MESSAGE_SENT = "message.sent";
    public static final String INTENT_MESSAGE_DELIVERED = "message.delivered";
    public static final String INTENT_GET_CONTACT = "intentcontact";
    public static final String EXTRA_MESSAGE = "extra.message";
    public static final String EXTRA_RECEIVERS = "extra.receivers";
    public static final String BLOCK_SMS = "block_sms";
    public static final String INTENT_SMS = "intentsms";
    public static final String INTENT_SMS_DATA = "data";
    public static ArrayList<InfoContact> lscontacts = new ArrayList<>();
    public static HashMap<String,InfoContact> mapcontacts = new HashMap<>();
    //status bar
    public static String COLOR_STATUS_BAR = null;
    public static int BACKGROUND = -1;
    public static String CACHE_STATUS_BAR = "statusbar";
    public static String CACHE_BACKGROUND = "background";
    public static String CACHE_TEXTVIEW = "textviewco";
}
