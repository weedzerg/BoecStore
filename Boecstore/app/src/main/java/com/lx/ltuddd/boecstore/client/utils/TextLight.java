package com.lx.ltuddd.boecstore.client.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 12/1/2017.
 */

public class TextLight extends TextView {
    public TextLight(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("FS NokioLight.ttf", context);
        setTypeface(customFont);
    }
}
