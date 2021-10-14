package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.widget.TextView;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ProductParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

public abstract class SupplementalInfoRetriever extends AsyncTask<Object, Object, Object> {
    private static final String TAG = "SupplementalInfo";
    private final WeakReference<HistoryManager> historyManagerRef;
    private final Collection<Spannable> newContents = new ArrayList();
    private final Collection<String[]> newHistories = new ArrayList();
    private final WeakReference<TextView> textViewRef;

    /* access modifiers changed from: package-private */
    public abstract void retrieveSupplementalInfo() throws IOException;

    public static void maybeInvokeRetrieval(TextView textView, ParsedResult parsedResult, HistoryManager historyManager, Context context) {
        try {
            if (parsedResult instanceof URIParsedResult) {
                new URIResultInfoRetriever(textView, (URIParsedResult) parsedResult, historyManager, context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                new TitleRetriever(textView, (URIParsedResult) parsedResult, historyManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
            } else if (parsedResult instanceof ProductParsedResult) {
                new ProductResultInfoRetriever(textView, ((ProductParsedResult) parsedResult).getProductID(), historyManager, context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
            } else if (parsedResult instanceof ISBNParsedResult) {
                String isbn = ((ISBNParsedResult) parsedResult).getISBN();
                new ProductResultInfoRetriever(textView, isbn, historyManager, context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                new BookResultInfoRetriever(textView, isbn, historyManager, context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
            }
        } catch (RejectedExecutionException unused) {
        }
    }

    SupplementalInfoRetriever(TextView textView, HistoryManager historyManager) {
        this.textViewRef = new WeakReference<>(textView);
        this.historyManagerRef = new WeakReference<>(historyManager);
    }

    @Override // android.os.AsyncTask
    public final Object doInBackground(Object... objArr) {
        try {
            retrieveSupplementalInfo();
            return null;
        } catch (IOException e) {
            Log.w(TAG, e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public final void onPostExecute(Object obj) {
        TextView textView = this.textViewRef.get();
        if (textView != null) {
            for (Spannable spannable : this.newContents) {
                textView.append(spannable);
            }
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        HistoryManager historyManager = this.historyManagerRef.get();
        if (historyManager != null) {
            for (String[] strArr : this.newHistories) {
                historyManager.addHistoryItemDetails(strArr[0], strArr[1]);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void append(String str, String str2, String[] strArr, String str3) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append(str2);
            sb.append(' ');
        }
        int length = sb.length();
        boolean z = true;
        for (String str4 : strArr) {
            if (z) {
                sb.append(str4);
                z = false;
            } else {
                sb.append(" [");
                sb.append(str4);
                sb.append(']');
            }
        }
        int length2 = sb.length();
        String sb2 = sb.toString();
        SpannableString spannableString = new SpannableString(sb2 + "\n\n");
        if (str3 != null) {
            if (str3.startsWith("HTTP://")) {
                str3 = "http" + str3.substring(4);
            } else if (str3.startsWith("HTTPS://")) {
                str3 = "https" + str3.substring(5);
            }
            spannableString.setSpan(new URLSpan(str3), length, length2, 33);
        }
        this.newContents.add(spannableString);
        this.newHistories.add(new String[]{str, sb2});
    }

    static void maybeAddText(String str, Collection<String> collection) {
        if (str != null && !str.isEmpty()) {
            collection.add(str);
        }
    }

    static void maybeAddTextSeries(Collection<String> collection, Collection<String> collection2) {
        if (!(collection == null || collection.isEmpty())) {
            boolean z = true;
            StringBuilder sb = new StringBuilder();
            for (String str : collection) {
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                sb.append(str);
            }
            collection2.add(sb.toString());
        }
    }
}
