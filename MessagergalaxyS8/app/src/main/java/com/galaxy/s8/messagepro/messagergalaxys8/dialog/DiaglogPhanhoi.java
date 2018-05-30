package com.galaxy.s8.messagepro.messagergalaxys8.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.adapters.AdapterPhanhoi;


/**
 * Created by binhnk on 7/25/2017.
 */

public class DiaglogPhanhoi extends Dialog implements AdapterView.OnItemClickListener {
    private Context context;
    private AdapterPhanhoi adapterPhanhoi;
    private ListView lsview;
    OnResult onResult;
    private String[] ls = new String[]{"Rất tiếc, tôi đã bỏ lỡ cuộc gọi của bạn.",
            "Tôi đang bị trễ nhưng tôi sẽ đến đó ngay.",
            "Bạn thế nào rồi?", "Có chuyện gì vậy?", "Bạn đang ở đâu?",
            "Vui lòng gọi cho tôi khi bạn nhận được tin nhắn này.",
            "Khi nào chúng ta gặp nhau?", "Tôi sẽ nói chuyện với bạn sau.",
            "Họp ở đâu?", "Số bao nhiêu?"};

    public DiaglogPhanhoi(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.diaglog_phanhoi);
        lsview = (ListView) findViewById(R.id.lsview_phanhoi);
        adapterPhanhoi = new AdapterPhanhoi(ls, LayoutInflater.from(context));
        lsview.setAdapter(adapterPhanhoi);
        lsview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        onResult.onFished(ls[i]);
        dismiss();
    }

    public void setOnResult(OnResult onResult) {
        this.onResult = onResult;
    }

    public interface OnResult {
        void onFished(String result);
    }
}
