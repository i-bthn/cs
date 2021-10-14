package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.widget.TextView;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.URIParsedResult;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* access modifiers changed from: package-private */
public final class URIResultInfoRetriever extends SupplementalInfoRetriever {
    private static final int MAX_REDIRECTS = 5;
    private final String redirectString;
    private final URIParsedResult result;

    URIResultInfoRetriever(TextView textView, URIParsedResult uRIParsedResult, HistoryManager historyManager, Context context) {
        super(textView, historyManager);
        this.redirectString = context.getString(R.string.msg_redirect);
        this.result = uRIParsedResult;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.zxing.client.android.result.supplement.SupplementalInfoRetriever
    public void retrieveSupplementalInfo() throws IOException {
        try {
            URI uri = new URI(this.result.getURI());
            URI unredirect = HttpHelper.unredirect(uri);
            URI uri2 = uri;
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i < 5 && !uri2.equals(unredirect)) {
                    String displayResult = this.result.getDisplayResult();
                    append(displayResult, null, new String[]{this.redirectString + " : " + unredirect}, unredirect.toString());
                    uri2 = unredirect;
                    unredirect = HttpHelper.unredirect(unredirect);
                    i = i2;
                } else {
                    return;
                }
            }
        } catch (URISyntaxException unused) {
        }
    }
}
