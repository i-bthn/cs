package com.google.zxing.client.android.history;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.Result;
import java.util.ArrayList;

final class HistoryItemAdapter extends ArrayAdapter<HistoryItem> {
    private final Context activity;

    HistoryItemAdapter(Context context) {
        super(context, R.layout.history_list_item, new ArrayList());
        this.activity = context;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        String str;
        String str2;
        if (!(view instanceof LinearLayout)) {
            view = LayoutInflater.from(this.activity).inflate(R.layout.history_list_item, viewGroup, false);
        }
        HistoryItem historyItem = (HistoryItem) getItem(i);
        Result result = historyItem.getResult();
        if (result != null) {
            str = result.getText();
            str2 = historyItem.getDisplayAndDetails();
        } else {
            Resources resources = getContext().getResources();
            str = resources.getString(R.string.history_empty);
            str2 = resources.getString(R.string.history_empty_detail);
        }
        ((TextView) view.findViewById(R.id.history_title)).setText(str);
        ((TextView) view.findViewById(R.id.history_detail)).setText(str2);
        return view;
    }
}
