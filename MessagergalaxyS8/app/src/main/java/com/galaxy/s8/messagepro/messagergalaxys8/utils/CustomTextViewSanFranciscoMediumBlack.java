package com.galaxy.s8.messagepro.messagergalaxys8.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 8/30/2017.
 */

public class CustomTextViewSanFranciscoMediumBlack extends TextView {
    public CustomTextViewSanFranciscoMediumBlack(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomTextViewSanFranciscoMediumBlack(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomTextViewSanFranciscoMediumBlack(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("SanFranciscoMedium.ttf", context);
        setTypeface(customFont);

            setTextColor(Color.parseColor("#252525"));

    }
}
