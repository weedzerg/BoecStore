package com.security.filelocker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.security.filelocker.R;
import com.security.filelocker.objects.InfoFile;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/13/2017.
 */

public class AdapterAudio extends AdapterInfor {

    ImageView iv_image, iv_check;
    TextView tv_nameFile;

    public AdapterAudio(ArrayList<InfoFile> lsFile, LayoutInflater inflater) {
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
        if (item.isCheck()) {
            iv_check.setImageResource(R.drawable.ic_tick);
        } else {
            iv_check.setImageResource(R.drawable.ic_no_tick);
        }
        iv_image.setImageResource(R.drawable.ic_music);
        tv_nameFile.setText(item.getNamefile());

        return view;
    }
}
