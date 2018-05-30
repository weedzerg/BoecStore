package com.security.filelocker.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 8/30/2017.
 */

public class CustomTextViewSanFranciscoDisplayRegular extends TextView {
    public CustomTextViewSanFranciscoDisplayRegular(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomTextViewSanFranciscoDisplayRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomTextViewSanFranciscoDisplayRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("SanFranciscoDisplayRegular.otf", context);
        setTypeface(customFont);
        setTextColor(Color.BLACK);
    }
}
