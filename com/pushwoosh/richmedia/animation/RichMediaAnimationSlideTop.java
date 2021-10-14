package com.pushwoosh.richmedia.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class RichMediaAnimationSlideTop extends a {
    @Override // com.pushwoosh.richmedia.animation.a
    public Animation getCloseAnimation(View view) {
        return new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-view.getHeight()));
    }

    @Override // com.pushwoosh.richmedia.animation.a
    public Animation getOpenAnimation(View view) {
        return new TranslateAnimation(0.0f, 0.0f, (float) (-view.getHeight()), 0.0f);
    }
}
