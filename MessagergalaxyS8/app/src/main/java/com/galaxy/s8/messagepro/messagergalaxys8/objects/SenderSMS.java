package com.galaxy.s8.messagepro.messagergalaxys8.objects;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/26/2017.
 */

public class SenderSMS extends InfoSender {
    private ArrayList<BodySMS> lsbody;


    public SenderSMS(String nameSender, String numberSender, ArrayList<BodySMS> lsbody) {
        super(nameSender, numberSender);
        this.lsbody = lsbody;
    }

    public ArrayList<BodySMS> getLsbody() {
        return lsbody;
    }

    public void setLsbody(ArrayList<BodySMS> lsbody) {
        this.lsbody = lsbody;
    }
}
