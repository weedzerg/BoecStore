package com.media.convert.videotomp3aacc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.convert.videotomp3aacc.R;
import com.media.convert.videotomp3aacc.objects.InfoFile;
import com.media.convert.videotomp3aacc.utils.Utils;

import java.util.ArrayList;


/**
 * Created by DaiPhongPC on 9/12/2017.
 */

public class AdapterAudio extends BaseAdapter {
    private ArrayList<InfoFile> lsFile;
    private LayoutInflater inflater;
    ImageView iv_image, iv_audio;
    TextView tv_nameFile, tv_date, tv_size;

    public AdapterAudio(ArrayList<InfoFile> lsFile, LayoutInflater inflater) {
        this.lsFile = lsFile;
        this.inflater = inflater;
    }

    public AdapterAudio(ArrayList<InfoFile> lsFile) {
        this.lsFile = lsFile;
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

    public ArrayList<InfoFile> getLsFile() {
        return lsFile;
    }

    public void setLsFile(ArrayList<InfoFile> lsFile) {
        this.lsFile = lsFile;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_file, null);
        }
        iv_image = (ImageView) view.findViewById(R.id.iv_image);
        iv_image.setVisibility(View.GONE);
        tv_nameFile = (TextView) view.findViewById(R.id.tv_name_folder);
        iv_audio = (ImageView) view.findViewById(R.id.iv_audio);
        iv_audio.setVisibility(View.VISIBLE);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_size = (TextView) view.findViewById(R.id.tv_size);
        InfoFile item = getItem(i);
        tv_date.setVisibility(View.GONE);
        tv_size.setVisibility(View.GONE);
        String name = Utils.coverName(item.getNamefile());
        tv_nameFile.setText(name);
        return view;
    }

}
