package com.media.vid.video_speed.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 12/1/2017.
 */

public class TextBlod_ extends TextView {
    public TextBlod_(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextBlod_(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextBlod_(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("AvoBold.ttf", context);
        setTypeface(customFont);
    }
}
