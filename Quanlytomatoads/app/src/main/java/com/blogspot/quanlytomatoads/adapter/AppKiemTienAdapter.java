package com.blogspot.quanlytomatoads.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.blogspot.quanlytomatoads.HKCache.Cache;
import com.blogspot.quanlytomatoads.R;
import com.blogspot.quanlytomatoads.common.Common;
import com.blogspot.quanlytomatoads.controller.ConvertPackage;
import com.blogspot.quanlytomatoads.model.TTappstore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Javis on 3/10/2017.
 */

public class AppKiemTienAdapter extends BaseAdapter implements Filterable {

    private ArrayList<TTappstore> lspackage;
    private ArrayList<TTappstore> lspackage1;
    private Activity activity;
    private Cache cache;

    public AppKiemTienAdapter(ArrayList<TTappstore> lspackage, Activity activity) {
        this.lspackage = lspackage;
        this.activity = activity;
        this.lspackage1 = lspackage;
    }

    public ArrayList<TTappstore> getLspackage() {
        return lspackage;
    }

    public void setLspackage(ArrayList<TTappstore> lspackage) {
        this.lspackage = lspackage;
    }


    @Override
    public int getCount() {
        return lspackage.size();
    }

    @Override
    public TTappstore getItem(int i) {
        return lspackage.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.app_kiem_tien_item, null);
        }
        txttrangthai = (TextView) convertView.findViewById(R.id.txttrangthai);
        imgapp = (ImageView) convertView.findViewById(R.id.img_app_pic_kiemtien);
        nameapp = (TextView) convertView.findViewById(R.id.txt_app_ten_kiemtien);
        namedev = (TextView) convertView.findViewById(R.id.txt_app_nhapt_kiemtien);
        ratingBar = (RatingBar) convertView.findViewById(R.id.rat_app_danhgia_kiemtien);
        progressBar = (ProgressBar) convertView.findViewById(R.id.progress_itemkt);
        lnok = (LinearLayout) convertView.findViewById(R.id.phetduyet);
        lnhuy = (LinearLayout) convertView.findViewById(R.id.huy);
        txtpheduyet = (TextView) convertView.findViewById(R.id.huypheduyet);
        imghuypheduyet = (ImageView) convertView.findViewById(R.id.imghuypheduyet);
        cache = new Cache(convertView.getContext());
        final TTappstore item = getItem(i);
        String namepackage = item.getNamepackage();
        namedev.setText(item.getNamedev());
        switch (item.getTrangthai()) {
            case 0: {
                lspackage.remove(i);
                notifyDataSetChanged();
                break;
            }
            case 1: {
                txttrangthai.setText("Chờ phê duyệt");
                txttrangthai.setTextColor(Color.parseColor("#ff7b00"));
                txtpheduyet.setText("Phê duyệt");
                break;
            }
            case 2: {
                txttrangthai.setText("Đã phê duyệt");
                txttrangthai.setTextColor(Color.parseColor("#0eb602"));
                txtpheduyet.setText("Hủy phê duyệt");
                break;
            }
        }

        new HtmlExtraterTaskAppKiemTien(imgapp, nameapp, progressBar, cache, ratingBar).execute(namepackage);
        lnhuy.setOnClickListener(on_click);
        lnok.setOnClickListener(on_click);
        lnhuy.setTag(i);
        lnok.setTag(i);

        return convertView;
    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final int vt = (int) view.getTag();
            final TTappstore item = lspackage.get(vt);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
            final DatabaseReference refNode = Common.data.child("/listxetduyet/" +
                    ConvertPackage.packagetoKey(item.getNamepackage()));
            HashMap<String, Objects> lsvalues = new HashMap<>();

            switch (view.getId()) {
                case R.id.phetduyet: {
                    switch (item.getTrangthai()) {
                        case 1: {
                            alertDialogBuilder.setMessage("Bạn chắc chắn muốn phê duyệt ứng dụng này không?");
                            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    refNode.child("ttpheduyet").setValue(2);
                                    DatabaseReference addThongbao = Common.data.child("/user/" + item.getIddev() +
                                            "/notification/").push();
                                    String detail = "8@" + item.getNamepackage();
                                    addNoti(addThongbao, detail);
                                }

                            });
                            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    // button "no" ẩn dialog đi
                                }
                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            // tạo dialog
                            alertDialog.show();
                            break;
                        }
                        case 2: {
                            alertDialogBuilder.setMessage("Bạn có muốn hủy phê duyệt không?");
                            // thiết lập nội dung cho dialog
                            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    refNode.child("ttpheduyet").setValue(1);
                                    DatabaseReference addThongbao = Common.data.child("/user/" + item.getIddev() +
                                            "/notification/").push();
                                    String detail = "9@" + item.getNamepackage() + "@";
                                    addNoti(addThongbao, detail);
                                }

                            });

                            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    // button "no" ẩn dialog đi


                                }
                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            // tạo dialog
                            alertDialog.show();
                            break;
                        }

                    }
                    break;
                }
                case R.id.huy: {
                    alertDialogBuilder.setMessage("Bạn có muốn xóa không?");
                    alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            refNode.setValue(null);
                            Common.data.child("/user/" + item.getIddev() + "/mypackage/" +
                                    ConvertPackage.packagetoKey(item.getNamepackage()))
                                    .setValue(null);
                            DatabaseReference addThongbao = Common.data.child("/user/" + item.getIddev() +
                                    "/notification/").push();
                            String detail = "10@" + item.getNamepackage();
                            addNoti(addThongbao, detail);
                        }
                    });

                    alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // button "no" ẩn dialog đi
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // tạo dialog
                    alertDialog.show();

                    break;
                }
            }
        }
    };


    public void addNoti(DatabaseReference reference, String detail) {
        HashMap<String, Object> hs = new HashMap<>();
        hs.put("checkread", false);
        hs.put("timer", ServerValue.TIMESTAMP);
        hs.put("detail", detail);
        reference.updateChildren(hs);
    }

    ImageView imgapp, imghuypheduyet;
    TextView nameapp, namedev, txttrangthai, txtpheduyet;
    RatingBar ratingBar;
    ProgressBar progressBar;
    LinearLayout lnok, lnhuy;
    ValueFilter valueFilter;

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<TTappstore> filterList = new ArrayList();

                for (int i = 0; i < lspackage1.size(); i++) {
                    TTappstore a = lspackage1.get(i);
                    if ((a.getNamepackage().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(a);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = lspackage1.size();
                results.values = lspackage1;
            }
            return results;

        }


        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            lspackage = (ArrayList<TTappstore>) results.values;
            notifyDataSetChanged();
        }

    }

    private final String LINK_PART = "https://play.google.com/store/apps/details?id=";


    class HtmlExtraterTaskAppKiemTien extends AsyncTask<String, String, String[]> {
        private ImageView imageView;
        private TextView titleapp;
        private ProgressBar pro;
        private Cache cache;
        private final String LINK_PART = "https://play.google.com/store/apps/details?id=";
        private RatingBar ratingBar;

        public HtmlExtraterTaskAppKiemTien(ImageView imageView, TextView titleapp, ProgressBar pro,
                                           Cache cache, RatingBar ratingBar) {
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
            loadinf_(strings, imageView, titleapp, pro, ratingBar);
            this.cancel(true);
        }

        public void loadinf_(String[] res, ImageView imageView, TextView titleapp, ProgressBar progressBar,
                             RatingBar ratingBar) {
            Float rat = Float.valueOf(res[2]);
            titleapp.setText(res[1]);
            ratingBar.setRating(rat);
            progressBar.setVisibility(View.INVISIBLE);
            Picasso.with(activity).load(res[0]).into(imageView);
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

