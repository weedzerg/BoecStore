package com.security.filelocker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.security.filelocker.objects.InfoFile;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/13/2017.
 */

public class AdapterInfor extends BaseAdapter {
    private ArrayList<InfoFile> lsFile;
    private LayoutInflater inflater;

    public AdapterInfor(ArrayList<InfoFile> lsFile, LayoutInflater inflater) {
        this.lsFile = lsFile;
        this.inflater = inflater;
    }
    public ArrayList<InfoFile> getLsFile() {
        return lsFile;
    }

    public void setLsFile(ArrayList<InfoFile> lsFile) {
        this.lsFile = lsFile;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }
    @Override
    public int getCount() {
        return lsFile.size();
    }
    @Override
    public InfoFile getItem(int i) {
        return lsFile.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }
}