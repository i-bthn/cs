package com.google.zxing.client.android.book;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import barcodescanner.xservices.nl.barcodescanner.R;
import java.util.Locale;

public final class SearchBookContentsListItem extends LinearLayout {
    private TextView pageNumberView;
    private TextView snippetView;

    SearchBookContentsListItem(Context context) {
        super(context);
    }

    public SearchBookContentsListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.pageNumberView = (TextView) findViewById(R.id.page_number_view);
        this.snippetView = (TextView) findViewById(R.id.snippet_view);
    }

    public void set(SearchBookContentsResult searchBookContentsResult) {
        this.pageNumberView.setText(searchBookContentsResult.getPageNumber());
        String snippet = searchBookContentsResult.getSnippet();
        if (snippet.isEmpty()) {
            this.snippetView.setText("");
        } else if (searchBookContentsResult.getValidSnippet()) {
            String lowerCase = SearchBookContentsResult.getQuery().toLowerCase(Locale.getDefault());
            String lowerCase2 = snippet.toLowerCase(Locale.getDefault());
            SpannableString spannableString = new SpannableString(snippet);
            StyleSpan styleSpan = new StyleSpan(1);
            int length = lowerCase.length();
            int i = 0;
            while (true) {
                int indexOf = lowerCase2.indexOf(lowerCase, i);
                if (indexOf < 0) {
                    this.snippetView.setText(spannableString);
                    return;
                }
                int i2 = indexOf + length;
                spannableString.setSpan(styleSpan, indexOf, i2, 0);
                i = i2;
            }
        } else {
            this.snippetView.setText(snippet);
        }
    }
}
