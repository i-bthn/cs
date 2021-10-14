package com.pushwoosh.richmedia.animation;

import android.view.View;
import android.view.animation.Animation;

public abstract class a implements RichMediaAnimation {
    public static final int DURATION_MILLIS = 300;

    @Override // com.pushwoosh.richmedia.animation.RichMediaAnimation
    public void closeAnimation(View view, View view2, Animation.AnimationListener animationListener) {
        Animation closeAnimation = getCloseAnimation(view2);
        if (closeAnimation != null) {
            closeAnimation.setDuration(300);
            closeAnimation.setAnimationListener(animationListener);
            view.startAnimation(closeAnimation);
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Animation getCloseAnimation(View view);

    /* access modifiers changed from: package-private */
    public abstract Animation getOpenAnimation(View view);

    @Override // com.pushwoosh.richmedia.animation.RichMediaAnimation
    public void openAnimation(View view, View view2) {
        Animation openAnimation = getOpenAnimation(view2);
        if (openAnimation != null) {
            openAnimation.setDuration(300);
            view.startAnimation(openAnimation);
        }
    }
}
