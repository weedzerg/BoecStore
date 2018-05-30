package com.galaxy.s8.messagepro.messagergalaxys8.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 8/30/2017.
 */

public class CustomTextViewSanFranciscoBoldBlack extends TextView {
    public CustomTextViewSanFranciscoBoldBlack(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomTextViewSanFranciscoBoldBlack(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomTextViewSanFranciscoBoldBlack(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("SanFranciscoBold.ttf", context);
        setTypeface(customFont);
        Cache cache = new Cache(context);
        String color = cache.getLink(Contants.CACHE_TEXTVIEW);

            setTextColor(Color.BLACK);


    }
}
