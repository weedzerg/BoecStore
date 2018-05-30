package com.media.studio.gifmaker.adapters;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.studio.gifmaker.R;
import com.media.studio.gifmaker.objects.InfoFile;

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
    ImageView iv_image;
    TextView tv_nameFile;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_file, null);
        }
        iv_image = (ImageView) view.findViewById(R.id.iv_image);
        tv_nameFile = (TextView) view.findViewById(R.id.tv_name_folder);

        InfoFile item = getItem(i);
        loadData l = new loadData(iv_image);
        l.execute(item.getPathfile());
        tv_nameFile.setText(item.getNamefile());
        return view;
    }

    public class loadData extends AsyncTask<String, Void, Bitmap> {
        private ImageView iv;

        public loadData(ImageView iv) {
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... voids) {

            return ThumbnailUtils.createVideoThumbnail(voids[0],
                    MediaStore.Images.Thumbnails.MINI_KIND);
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            try {
                iv.setImageBitmap(Bitmap.createScaledBitmap(aVoid, 120, 120, false));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.cancel(true);

        }

    }
}
