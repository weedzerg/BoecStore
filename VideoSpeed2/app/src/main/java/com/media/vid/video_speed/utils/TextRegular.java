package com.media.vid.video_speed.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 12/1/2017.
 */

public class TextRegular extends TextView {
    public TextRegular(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Avo.ttf", context);
        setTypeface(customFont);
    }
}
