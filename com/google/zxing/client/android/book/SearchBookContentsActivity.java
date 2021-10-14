package com.google.zxing.client.android.book;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.media.MediaBrowserServiceCompat;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.LocaleManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SearchBookContentsActivity extends Activity {
    private static final Pattern GT_ENTITY_PATTERN = Pattern.compile("&gt;");
    private static final Pattern LT_ENTITY_PATTERN = Pattern.compile("&lt;");
    private static final Pattern QUOTE_ENTITY_PATTERN = Pattern.compile("&#39;");
    private static final Pattern QUOT_ENTITY_PATTERN = Pattern.compile("&quot;");
    private static final String TAG = "SearchBookContentsActivity";
    private static final Pattern TAG_PATTERN = Pattern.compile("\\<.*?\\>");
    private final View.OnClickListener buttonListener = new View.OnClickListener() {
        /* class com.google.zxing.client.android.book.SearchBookContentsActivity.AnonymousClass1 */

        public void onClick(View view) {
            SearchBookContentsActivity.this.launchSearch();
        }
    };
    private TextView headerView;
    private String isbn;
    private final View.OnKeyListener keyListener = new View.OnKeyListener() {
        /* class com.google.zxing.client.android.book.SearchBookContentsActivity.AnonymousClass2 */

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66 || keyEvent.getAction() != 0) {
                return false;
            }
            SearchBookContentsActivity.this.launchSearch();
            return true;
        }
    };
    private AsyncTask<String, ?, ?> networkTask;
    private View queryButton;
    private EditText queryTextView;
    private ListView resultListView;

    /* access modifiers changed from: package-private */
    public String getISBN() {
        return this.isbn;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeExpiredCookie();
        Intent intent = getIntent();
        if (intent == null || !Intents.SearchBookContents.ACTION.equals(intent.getAction())) {
            finish();
            return;
        }
        this.isbn = intent.getStringExtra(Intents.SearchBookContents.ISBN);
        if (LocaleManager.isBookSearchUrl(this.isbn)) {
            setTitle(getString(R.string.sbc_name));
        } else {
            setTitle(getString(R.string.sbc_name) + ": ISBN " + this.isbn);
        }
        setContentView(R.layout.search_book_contents);
        this.queryTextView = (EditText) findViewById(R.id.query_text_view);
        String stringExtra = intent.getStringExtra(Intents.SearchBookContents.QUERY);
        if (stringExtra != null && !stringExtra.isEmpty()) {
            this.queryTextView.setText(stringExtra);
        }
        this.queryTextView.setOnKeyListener(this.keyListener);
        this.queryButton = findViewById(R.id.query_button);
        this.queryButton.setOnClickListener(this.buttonListener);
        this.resultListView = (ListView) findViewById(R.id.result_list_view);
        this.headerView = (TextView) LayoutInflater.from(this).inflate(R.layout.search_book_contents_header, (ViewGroup) this.resultListView, false);
        this.resultListView.addHeaderView(this.headerView);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.queryTextView.selectAll();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        AsyncTask<String, ?, ?> asyncTask = this.networkTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.networkTask = null;
        }
        super.onPause();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void launchSearch() {
        String obj = this.queryTextView.getText().toString();
        if (obj != null && !obj.isEmpty()) {
            AsyncTask<String, ?, ?> asyncTask = this.networkTask;
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            this.networkTask = new NetworkTask();
            this.networkTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, obj, this.isbn);
            this.headerView.setText(R.string.msg_sbc_searching_book);
            this.resultListView.setAdapter((ListAdapter) null);
            this.queryTextView.setEnabled(false);
            this.queryButton.setEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public final class NetworkTask extends AsyncTask<String, Object, JSONObject> {
        private NetworkTask() {
        }

        /* access modifiers changed from: protected */
        public JSONObject doInBackground(String... strArr) {
            String str;
            try {
                String str2 = strArr[0];
                String str3 = strArr[1];
                if (LocaleManager.isBookSearchUrl(str3)) {
                    str = "http://www.google.com/books?id=" + str3.substring(str3.indexOf(61) + 1) + "&jscmd=SearchWithinVolume2&q=" + str2;
                } else {
                    str = "http://www.google.com/books?vid=isbn" + str3 + "&jscmd=SearchWithinVolume2&q=" + str2;
                }
                return new JSONObject(HttpHelper.downloadViaHttp(str, HttpHelper.ContentType.JSON).toString());
            } catch (IOException e) {
                Log.w(SearchBookContentsActivity.TAG, "Error accessing book search", e);
                return null;
            } catch (JSONException e2) {
                Log.w(SearchBookContentsActivity.TAG, "Error accessing book search", e2);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(JSONObject jSONObject) {
            if (jSONObject == null) {
                SearchBookContentsActivity.this.headerView.setText(R.string.msg_sbc_failed);
            } else {
                handleSearchResults(jSONObject);
            }
            SearchBookContentsActivity.this.queryTextView.setEnabled(true);
            SearchBookContentsActivity.this.queryTextView.selectAll();
            SearchBookContentsActivity.this.queryButton.setEnabled(true);
        }

        private void handleSearchResults(JSONObject jSONObject) {
            try {
                int i = jSONObject.getInt("number_of_results");
                TextView textView = SearchBookContentsActivity.this.headerView;
                textView.setText(SearchBookContentsActivity.this.getString(R.string.msg_sbc_results) + " : " + i);
                if (i > 0) {
                    JSONArray jSONArray = jSONObject.getJSONArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
                    SearchBookContentsResult.setQuery(SearchBookContentsActivity.this.queryTextView.getText().toString());
                    ArrayList arrayList = new ArrayList(i);
                    for (int i2 = 0; i2 < i; i2++) {
                        arrayList.add(parseResult(jSONArray.getJSONObject(i2)));
                    }
                    SearchBookContentsActivity.this.resultListView.setOnItemClickListener(new BrowseBookListener(SearchBookContentsActivity.this, arrayList));
                    SearchBookContentsActivity.this.resultListView.setAdapter((ListAdapter) new SearchBookContentsAdapter(SearchBookContentsActivity.this, arrayList));
                    return;
                }
                if ("false".equals(jSONObject.optString("searchable"))) {
                    SearchBookContentsActivity.this.headerView.setText(R.string.msg_sbc_book_not_searchable);
                }
                SearchBookContentsActivity.this.resultListView.setAdapter((ListAdapter) null);
            } catch (JSONException e) {
                Log.w(SearchBookContentsActivity.TAG, "Bad JSON from book search", e);
                SearchBookContentsActivity.this.resultListView.setAdapter((ListAdapter) null);
                SearchBookContentsActivity.this.headerView.setText(R.string.msg_sbc_failed);
            }
        }

        private SearchBookContentsResult parseResult(JSONObject jSONObject) {
            String str;
            String str2;
            boolean z = false;
            try {
                String string = jSONObject.getString("page_id");
                String optString = jSONObject.optString("page_number");
                String optString2 = jSONObject.optString("snippet_text");
                if (optString == null || optString.isEmpty()) {
                    str = "";
                } else {
                    str = SearchBookContentsActivity.this.getString(R.string.msg_sbc_page) + ' ' + optString;
                }
                if (optString2 != null && !optString2.isEmpty()) {
                    z = true;
                }
                if (z) {
                    str2 = SearchBookContentsActivity.QUOT_ENTITY_PATTERN.matcher(SearchBookContentsActivity.QUOTE_ENTITY_PATTERN.matcher(SearchBookContentsActivity.GT_ENTITY_PATTERN.matcher(SearchBookContentsActivity.LT_ENTITY_PATTERN.matcher(SearchBookContentsActivity.TAG_PATTERN.matcher(optString2).replaceAll("")).replaceAll("<")).replaceAll(">")).replaceAll("'")).replaceAll("\"");
                } else {
                    str2 = '(' + SearchBookContentsActivity.this.getString(R.string.msg_sbc_snippet_unavailable) + ')';
                }
                return new SearchBookContentsResult(string, str, str2, z);
            } catch (JSONException e) {
                Log.w(SearchBookContentsActivity.TAG, e);
                return new SearchBookContentsResult(SearchBookContentsActivity.this.getString(R.string.msg_sbc_no_page_returned), "", "", false);
            }
        }
    }
}
