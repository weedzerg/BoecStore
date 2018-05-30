package com.blogspot.quanlytomatoads.common;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by DevPro on 6/23/2017.
 */

public class InputFilterMinMax implements InputFilter {
    //    myEditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 59)});
    private int min;
    private int max;

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        //noinspection EmptyCatchBlock
        try {
            int input = Integer.parseInt(dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length()));
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
