package com.pushwoosh.richmedia;

import android.view.View;
import com.pushwoosh.richmedia.animation.RichMediaAnimation;

public class RichMediaStyle {
    private int backgroundColor;
    private a loadingViewCreator;
    private RichMediaAnimation richMediaAnimation;
    private long timeOutBackButtonEnable;

    public interface a {
        View a();
    }

    public RichMediaStyle(int i, RichMediaAnimation richMediaAnimation2) {
        this.backgroundColor = i;
        this.richMediaAnimation = richMediaAnimation2;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public a getLoadingViewCreator() {
        return this.loadingViewCreator;
    }

    public RichMediaAnimation getRichMediaAnimation() {
        return this.richMediaAnimation;
    }

    public long getTimeOutBackButtonEnable() {
        return this.timeOutBackButtonEnable;
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public void setLoadingViewCreator(a aVar) {
        this.loadingViewCreator = aVar;
    }

    public void setRichMediaAnimation(RichMediaAnimation richMediaAnimation2) {
        this.richMediaAnimation = richMediaAnimation2;
    }

    public void setTimeOutBackButtonEnable(long j) {
        this.timeOutBackButtonEnable = j;
    }
}
