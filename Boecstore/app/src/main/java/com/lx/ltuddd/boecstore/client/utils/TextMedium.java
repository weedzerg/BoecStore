package com.lx.ltuddd.boecstore.client.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 12/1/2017.
 */

public class TextMedium extends TextView {
    public TextMedium(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("FS NokioMedium.ttf", context);
        setTypeface(customFont);
    }
}
