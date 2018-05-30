package com.media.vid.video_speed.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.media.vid.video_speed.R;
import com.media.vid.video_speed.dialog.RemoveDialog;
import com.media.vid.video_speed.objects.InfoFile;
import com.media.vid.video_speed.utils.Utils;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by DaiPhongPC on 9/12/2017.
 */

public class AdapterVideo extends BaseAdapter {
    private ArrayList<InfoFile> ls;
    private LayoutInflater inflater;

    public AdapterVideo(ArrayList<InfoFile> ls, LayoutInflater inflater) {
        this.ls = ls;
        this.inflater = inflater;
    }

    public ArrayList<InfoFile> getLs() {
        return ls;
    }

    public void setLs(ArrayList<InfoFile> ls) {
        this.ls = ls;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public InfoFile getItem(int i) {
        return ls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    ImageView iv_image, iv_remove;
    TextView tv_name, tv_time, tv_size;
    RemoveDialog remove;

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_file, null);
        }
        iv_image = (ImageView) view.findViewById(R.id.iv_thumlvid);
        iv_remove = (ImageView) view.findViewById(R.id.iv_remove);
        tv_name = (TextView) view.findViewById(R.id.tv_namevid);
        tv_time = (TextView) view.findViewById(R.id.tv_datevid);
        tv_size = (TextView) view.findViewById(R.id.tv_sizevid);
        final Context context = view.getContext();
        remove = new RemoveDialog(view.getContext(), new RemoveDialog.OnButtonClicked() {
            @Override
            public void onRateClicked() {
                File f = new File(ls.get(remove.getKey()).getPathfile());
                if (f.exists()) {
                    Toast.makeText(context, ls.get(remove.getKey()).getPathfile(), Toast.LENGTH_SHORT).show();
                    if (f.delete()) {
                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                Uri.fromFile(f)));
                        ls.remove(remove.getKey());
                        notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelClicked() {

            }
        });
        final InfoFile item = getItem(i);
        tv_name.setText(item.getNamefile());
        tv_time.setText(Utils.MMStohhmmss(item.getDuration()));
        tv_size.setText(Utils.sizeMB(item.getSize()));
        Glide.with(view.getContext())
                .load(Uri.fromFile(new File(item.getPathfile())))
                .into(iv_image);
        iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove.setKey(i);
                remove.show();
            }
        });
        return view;
    }
}
