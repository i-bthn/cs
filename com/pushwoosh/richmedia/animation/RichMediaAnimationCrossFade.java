package com.pushwoosh.richmedia.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class RichMediaAnimationCrossFade extends a {
    @Override // com.pushwoosh.richmedia.animation.a
    public Animation getCloseAnimation(View view) {
        return new AlphaAnimation(1.0f, 0.0f);
    }

    @Override // com.pushwoosh.richmedia.animation.a
    public Animation getOpenAnimation(View view) {
        return new AlphaAnimation(0.0f, 1.0f);
    }
}
