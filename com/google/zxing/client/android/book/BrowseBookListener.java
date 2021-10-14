package com.google.zxing.client.android.book;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import com.google.zxing.client.android.LocaleManager;
import java.util.List;

final class BrowseBookListener implements AdapterView.OnItemClickListener {
    private final SearchBookContentsActivity activity;
    private final List<SearchBookContentsResult> items;

    BrowseBookListener(SearchBookContentsActivity searchBookContentsActivity, List<SearchBookContentsResult> list) {
        this.activity = searchBookContentsActivity;
        this.items = list;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2;
        if (i >= 1 && (i2 = i - 1) < this.items.size()) {
            String pageId = this.items.get(i2).getPageId();
            String query = SearchBookContentsResult.getQuery();
            if (LocaleManager.isBookSearchUrl(this.activity.getISBN()) && !pageId.isEmpty()) {
                String isbn = this.activity.getISBN();
                String substring = isbn.substring(isbn.indexOf(61) + 1);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://books.google." + LocaleManager.getBookSearchCountryTLD(this.activity) + "/books?id=" + substring + "&pg=" + pageId + "&vq=" + query));
                intent.addFlags(524288);
                this.activity.startActivity(intent);
            }
        }
    }
}
