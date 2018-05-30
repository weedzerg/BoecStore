package com.security.filelocker.adapters;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.security.filelocker.R;
import com.security.filelocker.objects.InfoFile;

import java.util.ArrayList;


/**
 * Created by DaiPhongPC on 9/12/2017.
 */

public class AdapterVideo extends AdapterInfor {
    ImageView iv_image, iv_check;
    TextView tv_nameFile;

    public AdapterVideo(ArrayList<InfoFile> lsFile, LayoutInflater inflater) {
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
        loadData l = new loadData(iv_image);
        l.execute(item.getPathfile());
        if (item.isCheck()) {
            iv_check.setImageResource(R.drawable.ic_tick);
        } else {
            iv_check.setImageResource(R.drawable.ic_no_tick);
        }
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
            iv.setImageBitmap(Bitmap.createScaledBitmap(aVoid, 120, 120, false));
            this.cancel(true);

        }

    }
}
