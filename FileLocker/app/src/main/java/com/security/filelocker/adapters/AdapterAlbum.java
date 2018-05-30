package com.security.filelocker.adapters;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.security.filelocker.R;
import com.security.filelocker.objects.InfoFile;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/12/2017.
 */

public class AdapterAlbum extends BaseAdapter {
    private ArrayList<InfoFile> lsFile;
    private LayoutInflater inflater;
    private int check;
    //true in folder
    //false out folder

    public AdapterAlbum(ArrayList<InfoFile> lsFile, LayoutInflater inflater, int check) {
        this.lsFile = lsFile;
        this.inflater = inflater;
        this.check = check;
    }

    public ArrayList<InfoFile> getLsFile() {
        return lsFile;
    }

    public void setLsFile(ArrayList<InfoFile> lsFile) {
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

    ImageView iv_image, iv_check;
    TextView tv_nameFile, tv_size;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_album, null);
        }
        iv_image = (ImageView) view.findViewById(R.id.iv_image);
        iv_check = (ImageView) view.findViewById(R.id.iv_check);
        tv_nameFile = (TextView) view.findViewById(R.id.tv_name_folder);
        tv_size = (TextView) view.findViewById(R.id.tv_size);
        InfoFile item = getItem(i);
        switch (check) {
            case 0: {
                Picasso.with(view.getContext()).load(new File(item.getPathfile())).resize(120, 120).centerCrop().into(iv_image);
                break;
            }
            case 1: {
                Bitmap bitmap = getImageAudio(view.getContext(), item.getIdAlbum());
                if (bitmap != null) {
                    iv_image.setImageBitmap(bitmap);
                } else {
                    Picasso.with(view.getContext()).load(R.drawable.ic_music2s).resize(120, 120).into(iv_image);
                }
                break;
            }
            case 2: {
                loadData l = new loadData(iv_image);
                l.execute(item.getPathfile());
            }
            break;
        }
        iv_check.setVisibility(View.GONE);
        tv_size.setVisibility(View.VISIBLE);
        tv_size.setText(item.getSize() + "");
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

    public Bitmap getImageAudio(Context context, String albumId) {
        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
        Uri uri = ContentUris.withAppendedId(sArtworkUri, Integer.valueOf(albumId));
        ContentResolver res = context.getContentResolver();
        InputStream in;
        try {
            in = res.openInputStream(uri);
            return BitmapFactory.decodeStream(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
