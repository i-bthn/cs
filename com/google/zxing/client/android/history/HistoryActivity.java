package com.google.zxing.client.android.history;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.core.net.MailTo;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import java.util.Iterator;
import java.util.List;

public final class HistoryActivity extends ListActivity {
    private static final String TAG = "HistoryActivity";
    private ArrayAdapter<HistoryItem> adapter;
    private HistoryManager historyManager;
    private CharSequence originalTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.historyManager = new HistoryManager(this);
        this.adapter = new HistoryItemAdapter(this);
        setListAdapter(this.adapter);
        registerForContextMenu(getListView());
        this.originalTitle = getTitle();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        reloadHistoryItems();
    }

    private void reloadHistoryItems() {
        List<HistoryItem> buildHistoryItems = this.historyManager.buildHistoryItems();
        this.adapter.clear();
        Iterator<T> it = buildHistoryItems.iterator();
        while (it.hasNext()) {
            this.adapter.add(it.next());
        }
        setTitle(((Object) this.originalTitle) + " (" + this.adapter.getCount() + ')');
        if (this.adapter.isEmpty()) {
            this.adapter.add(new HistoryItem(null, null, null));
        }
    }

    /* access modifiers changed from: protected */
    public void onListItemClick(ListView listView, View view, int i, long j) {
        if (this.adapter.getItem(i).getResult() != null) {
            Intent intent = new Intent(this, CaptureActivity.class);
            intent.putExtra(Intents.History.ITEM_NUMBER, i);
            setResult(-1, intent);
            finish();
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        int i = ((AdapterView.AdapterContextMenuInfo) contextMenuInfo).position;
        if (i >= this.adapter.getCount() || this.adapter.getItem(i).getResult() != null) {
            contextMenu.add(0, i, i, R.string.history_clear_one_history_text);
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        this.historyManager.deleteHistoryItem(menuItem.getItemId());
        reloadHistoryItems();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.historyManager.hasHistoryItems()) {
            getMenuInflater().inflate(R.menu.history, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_history_send) {
            Uri saveHistory = HistoryManager.saveHistory(this.historyManager.buildHistory().toString());
            if (saveHistory == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.msg_unmount_usb);
                builder.setPositiveButton(R.string.button_ok, (DialogInterface.OnClickListener) null);
                builder.show();
            } else {
                Intent intent = new Intent("android.intent.action.SEND", Uri.parse(MailTo.MAILTO_SCHEME));
                intent.addFlags(524288);
                String string = getResources().getString(R.string.history_email_title);
                intent.putExtra("android.intent.extra.SUBJECT", string);
                intent.putExtra("android.intent.extra.TEXT", string);
                intent.putExtra("android.intent.extra.STREAM", saveHistory);
                intent.setType("text/csv");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Log.w(TAG, e.toString());
                }
            }
        } else if (itemId != R.id.menu_history_clear_text) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setMessage(R.string.msg_sure);
            builder2.setCancelable(true);
            builder2.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                /* class com.google.zxing.client.android.history.HistoryActivity.AnonymousClass1 */

                public void onClick(DialogInterface dialogInterface, int i) {
                    HistoryActivity.this.historyManager.clearHistory();
                    dialogInterface.dismiss();
                    HistoryActivity.this.finish();
                }
            });
            builder2.setNegativeButton(R.string.button_cancel, (DialogInterface.OnClickListener) null);
            builder2.show();
        }
        return true;
    }
}
