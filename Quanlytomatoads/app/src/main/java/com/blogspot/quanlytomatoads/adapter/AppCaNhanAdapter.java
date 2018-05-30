package com.blogspot.quanlytomatoads.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.blogspot.quanlytomatoads.HKCache.Cache;
import com.blogspot.quanlytomatoads.R;
import com.blogspot.quanlytomatoads.model.TTMypackage;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Javis on 3/8/2017.
 */

public class AppCaNhanAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<TTMypackage> lspackage;
    private Cache cache;

    private ImageView img_app, imgtrangthai, imgtai;
    private TextView txt_ten_app, txttrangthai;
    private TextView txt_luottai;
    private ProgressBar progressBar;
    private RatingBar rate;

    public AppCaNhanAdapter(Activity activity, ArrayList<TTMypackage> lspackage) {
        this.activity = activity;
        this.lspackage = lspackage;
        cache = new Cache(activity);
    }

    public ArrayList<TTMypackage> getLspackage() {
        return lspackage;
    }

    public void setLspackage(ArrayList<TTMypackage> lspackage) {
        this.lspackage = lspackage;
    }

    @Override
    public int getCount() {
        return lspackage.size();
    }

    @Override
    public TTMypackage getItem(int position) {
        return lspackage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.itemmypackage, null);
        }
        TTMypackage a = lspackage.get(position);
        progressBar = (ProgressBar) convertView.findViewById(R.id.progress_itemapp);
        img_app = (ImageView) convertView.findViewById(R.id.img_app);
        txt_ten_app = (TextView) convertView.findViewById(R.id.txt_tenapp);
        txt_luottai = (TextView) convertView.findViewById(R.id.dowload_count);
        txttrangthai = (TextView) convertView.findViewById(R.id.txt_pheduyet);
        rate = (RatingBar) convertView.findViewById(R.id.ratmyapp);

        if (a.getTrangthai()== 0) {
            lspackage.remove(position);
            notifyDataSetChanged();
        } else if (a.getTrangthai() == 1) {
            txttrangthai.setText("Đang chờ phê duyệt");
            txttrangthai.setTextColor(Color.parseColor("#ff7b00"));
            txt_luottai.setVisibility(View.INVISIBLE);
        } else if (a.getTrangthai() == 2) {
            txt_luottai.setVisibility(View.VISIBLE);
            txt_luottai.setText("Lượt cài đặt " + a.getDownload() + " lượt");
            txttrangthai.setText("Đã được phê duyệt");
            txttrangthai.setTextColor(Color.parseColor("#0eb602"));
        }
        cache = new Cache(activity);
        new HtmlExtracterTaskMyApp(img_app, txt_ten_app, progressBar, rate, cache).execute(a.getNamepackage());
        return convertView;
    }

    private final String LINK_PART = "https://play.google.com/store/apps/details?id=";


    class HtmlExtracterTaskMyApp extends AsyncTask<String, String, String[]> {
        private ImageView imageView;
        private TextView titleapp;
        private ProgressBar pro;
        private RatingBar ratingBar;
        private Cache cache;
        private final String LINK_PART = "https://play.google.com/store/apps/details?id=";

        public HtmlExtracterTaskMyApp(ImageView imageView, TextView titleapp, ProgressBar pro, RatingBar ratingBar, Cache cache) {
            this.imageView = imageView;
            this.titleapp = titleapp;
            this.pro = pro;
            this.cache = cache;
            this.ratingBar = ratingBar;
        }

        @Override
        protected String[] doInBackground(String... strings) {
            String[] info_app = extracUrl(strings[0]);
            return info_app;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            load_inf(strings, imageView, titleapp, pro, ratingBar);
            this.cancel(true);
            this.isCancelled();
        }

        public void load_inf(String[] res, ImageView imgapp, TextView txttenapp, ProgressBar prowait, RatingBar ratingBar) {
            float rat = 0;
            try {
                rat = 0f;
                rat = Float.valueOf(res[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            txttenapp.setText(res[1]);
            ratingBar.setRating(rat);
            prowait.setVisibility(View.INVISIBLE);
            Picasso.with(activity).load(res[0]).into(imgapp);
        }

        protected String[] extracUrl(String pac) {
            String[] res = cache.getLink(pac);
            if (res != null) {
                return res;
            } else {
                res = new String[3];
                Log.d("Htlm-->", "dowloading html dom... + " + pac);
                Document doc = null;
                try {
                    doc = Jsoup.connect(LINK_PART + pac).userAgent("Mozilla").get();
                    Log.d("Htlm-->", "extracting html dom... + " + pac);
                    Element element_icon = doc.select("img.cover-image").first();
                    String linkicon = element_icon.attr("src");
                    Log.d("link icon ->> ", linkicon);
                    Elements element_title = doc.select("div.id-app-title");
                    String titleapp = element_title.text();
                    Elements element_rate = doc.select("div.score");
                    String rate = element_rate.text();
                    String[] sliprate = rate.split(",");
                    Log.d("Html---", "rate " + rate);
                    res[0] = "https:" + linkicon;
                    res[1] = titleapp;
                    try {
                        res[2] = sliprate[0] + "." + sliprate[1];
                    } catch (Exception e) {
                        res[2] = "0.0";
                        e.printStackTrace();
                    }
                    Log.d("Htlm-->", "link result:" + res);
                    cache.putLink(pac, res);
                    return res;
                } catch (IOException e) {
                    Log.d("erro", "package");
                    return null;
                }
            }

        }
    }


}
