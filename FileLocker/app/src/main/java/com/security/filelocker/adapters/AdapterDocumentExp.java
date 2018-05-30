package com.security.filelocker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.security.filelocker.R;
import com.security.filelocker.objects.InfoFile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DaiPhongPC on 9/13/2017.
 */

public class AdapterDocumentExp extends BaseExpandableListAdapter {
    private ArrayList<String> lshead;
    private HashMap<String, ArrayList<InfoFile>> lschild;
    private LayoutInflater inflater;

    public AdapterDocumentExp(ArrayList<String> lshead, HashMap<String, ArrayList<InfoFile>> lschild,
                              LayoutInflater inflater) {
        this.lshead = lshead;
        this.lschild = lschild;
        this.inflater = inflater;
    }

    @Override
    public int getGroupCount() {
        return lshead.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.lschild.get(lshead.get(groupPosition)).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return lshead.get(groupPosition);
    }

    @Override
    public InfoFile getChild(int groupPosition, int childPosition) {
        return lschild.get(lshead.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_head, null);
        }
        String head = getGroup(groupPosition);
        ivheading = (ImageView) convertView.findViewById(R.id.iv_chude);
        tv_headring = (TextView) convertView.findViewById(R.id.tv_chude);
        tv_headring.setText(head);
        switch (lshead.get(groupPosition)) {
            case "Word":
                ivheading.setImageResource(R.drawable.ic_word);
                break;
            case "PDF":
                ivheading.setImageResource(R.drawable.ic_pdf);
                break;
            case "Excel":
                ivheading.setImageResource(R.drawable.ic_excel);
                break;
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        InfoFile child = getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_child_document, null);
        }
        tv_document = (TextView) convertView.findViewById(R.id.tv_document);
        iv_document = (ImageView) convertView.findViewById(R.id.iv_document);
        iv_check = (ImageView) convertView.findViewById(R.id.iv_check);
        if (child.isCheck()) {
            iv_check.setImageResource(R.drawable.ic_tick);
        } else {
            iv_check.setImageResource(R.drawable.ic_no_tick);
        }
        tv_document.setText(child.getNamefile());
        iv_document.setImageResource(R.drawable.ic_file2);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private TextView tv_document, tv_headring;
    private ImageView iv_document, iv_check, ivheading;
}
