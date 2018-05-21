package com.lx.ltuddd.boecstore.client.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 12/1/2017.
 */

public class TextHead extends TextView {
    public TextHead(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextHead(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("FS Harabara.ttf", context);
        setTypeface(customFont);
    }
}
