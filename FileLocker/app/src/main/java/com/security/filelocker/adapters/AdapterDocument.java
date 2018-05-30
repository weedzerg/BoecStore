package com.security.filelocker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.security.filelocker.R;
import com.security.filelocker.objects.InfoFile;
import com.security.filelocker.utils.Utils;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/19/2017.
 */

public class AdapterDocument extends AdapterInfor {

    ImageView iv_image, iv_check;
    TextView tv_nameFile;

    public AdapterDocument(ArrayList<InfoFile> lsFile, LayoutInflater inflater) {
        super(lsFile, inflater);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = getInflater().inflate(R.layout.item_file, null);
        }
        iv_image = (ImageView) view.findViewById(R.id.iv_image);
        iv_check = (ImageView) view.findViewById(R.id.iv_check);
        tv_nameFile = (TextView) view.findViewById(R.id.tv_name_folder);

        InfoFile item = getItem(i);
        String type = Utils.getTypeFile(item.getPathfile());
        switch (type) {
            case "docx":
            case "doc":
                iv_image.setImageResource(R.drawable.ic_word);
                break;
            case "pdf":
            case "PDF":
                iv_image.setImageResource(R.drawable.ic_pdf);
                break;
            case "xlsx":
            case "xls":
                iv_image.setImageResource(R.drawable.ic_excel);
                break;
        }
        if (item.isCheck()) {
            iv_check.setImageResource(R.drawable.ic_tick);
        } else {
            iv_check.setImageResource(R.drawable.ic_no_tick);
        }
        tv_nameFile.setText(item.getNamefile());
        return view;
    }
}
