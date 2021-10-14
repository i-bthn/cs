package com.google.zxing.client.android.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import barcodescanner.xservices.nl.barcodescanner.R;
import java.util.List;

final class SearchBookContentsAdapter extends ArrayAdapter<SearchBookContentsResult> {
    SearchBookContentsAdapter(Context context, List<SearchBookContentsResult> list) {
        super(context, R.layout.search_book_contents_list_item, 0, list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SearchBookContentsListItem searchBookContentsListItem;
        if (view == null) {
            searchBookContentsListItem = (SearchBookContentsListItem) LayoutInflater.from(getContext()).inflate(R.layout.search_book_contents_list_item, viewGroup, false);
        } else if (!(view instanceof SearchBookContentsListItem)) {
            return view;
        } else {
            searchBookContentsListItem = (SearchBookContentsListItem) view;
        }
        searchBookContentsListItem.set((SearchBookContentsResult) getItem(i));
        return searchBookContentsListItem;
    }
}
