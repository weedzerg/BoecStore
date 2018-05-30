package com.media.studio.reversevideo.utils;

import android.app.Activity;

import com.media.studio.reversevideo.R;


/**
 * Created by DaiPhongPC on 9/1/2017.
 */

public class AnimationTranslate {
    public static void nextAnimation(Activity activity){
        activity.overridePendingTransition(R.anim.anim_right, R.anim.anim_left);
    }
    public static void previewAnimation(Activity activity){
        activity.overridePendingTransition(R.anim.enter_anim_rtl, R.anim.exit_anim_rtl);
    }
}
