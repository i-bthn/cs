package com.pushwoosh.plugin.pushnotifications;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.pushwoosh.inbox.ui.PushwooshInboxStyle;
import com.pushwoosh.inbox.ui.model.customizing.formatter.InboxDateFormatter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

class InboxUiStyleManager {
    InboxUiStyleManager() {
    }

    private static class StyleParser {
        private Context context;
        private JSONObject style;

        private static Integer parseColor(String str) {
            if (str == null || str.length() == 0 || !str.matches("^(#[0-9A-Fa-f]{3}|(0x|#)([0-9A-Fa-f]{2})?[0-9A-Fa-f]{6})$")) {
                return null;
            }
            if (str.startsWith("#") && str.length() == 4) {
                String substring = str.substring(1, 2);
                String substring2 = str.substring(2, 3);
                String substring3 = str.substring(3, 4);
                str = "#" + substring + substring + substring2 + substring2 + substring3 + substring3;
            }
            return Integer.valueOf(Color.parseColor(str.replace("0x", "#")));
        }

        private Integer optColor(String str, Integer num) {
            Integer parseColor = parseColor(this.style.optString(str));
            return parseColor == null ? num : parseColor;
        }

        private InboxDateFormatter optDateFormatter(String str, InboxDateFormatter inboxDateFormatter) {
            String optString = this.style.optString(str);
            if (optString == null || optString.isEmpty()) {
                return inboxDateFormatter;
            }
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(optString, Locale.getDefault());
            return new InboxDateFormatter() {
                /* class com.pushwoosh.plugin.pushnotifications.InboxUiStyleManager.StyleParser.AnonymousClass1 */

                public String transform(Date date) {
                    return simpleDateFormat.format(date);
                }
            };
        }

        @Nullable
        private Drawable getDrawable(String str) throws IOException {
            Context context2 = this.context;
            if (context2 == null) {
                return null;
            }
            AssetManager assets = context2.getAssets();
            return new BitmapDrawable(this.context.getResources(), BitmapFactory.decodeStream(assets.open("www/" + str)));
        }

        private Drawable optImage(String str, Drawable drawable) {
            String optString = this.style.optString(str);
            if (!(optString == null || optString.length() == 0)) {
                try {
                    return getDrawable(optString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return drawable;
        }

        public StyleParser(Context context2, JSONObject jSONObject) {
            this.context = context2;
            this.style = jSONObject;
        }

        public void parse() {
            PushwooshInboxStyle pushwooshInboxStyle = PushwooshInboxStyle.INSTANCE;
            pushwooshInboxStyle.setBarBackgroundColor(optColor("barBackgroundColor", pushwooshInboxStyle.getBarBackgroundColor()));
            pushwooshInboxStyle.setBarAccentColor(optColor("barAccentColor", pushwooshInboxStyle.getBarAccentColor()));
            pushwooshInboxStyle.setBarTextColor(optColor("barTextColor", pushwooshInboxStyle.getBarTextColor()));
            pushwooshInboxStyle.setBarTitle(this.style.optString("barTitle", pushwooshInboxStyle.getBarTitle()));
            pushwooshInboxStyle.setDateFormatter(optDateFormatter("dateFormat", pushwooshInboxStyle.getDateFormatter()));
            pushwooshInboxStyle.setListErrorMessage(this.style.optString("listErrorMessage", pushwooshInboxStyle.getListErrorMessage() != null ? pushwooshInboxStyle.getListErrorMessage().toString() : ""));
            pushwooshInboxStyle.setListEmptyText(this.style.optString("listEmptyMessage", pushwooshInboxStyle.getListEmptyText() != null ? pushwooshInboxStyle.getListEmptyText().toString() : ""));
            pushwooshInboxStyle.setDefaultImageIconDrawable(optImage("defaultImageIcon", pushwooshInboxStyle.getDefaultImageIconDrawable()));
            pushwooshInboxStyle.setListErrorImageDrawable(optImage("listErrorImage", pushwooshInboxStyle.getListErrorImageDrawable()));
            pushwooshInboxStyle.setListEmptyImageDrawable(optImage("listEmptyImage", pushwooshInboxStyle.getListEmptyImageDrawable()));
            pushwooshInboxStyle.setAccentColor(optColor("accentColor", pushwooshInboxStyle.getAccentColor()));
            pushwooshInboxStyle.setHighlightColor(optColor("highlightColor", pushwooshInboxStyle.getHighlightColor()));
            pushwooshInboxStyle.setBackgroundColor(optColor("backgroundColor", pushwooshInboxStyle.getBackgroundColor()));
            pushwooshInboxStyle.setDividerColor(optColor("dividerColor", pushwooshInboxStyle.getDividerColor()));
            pushwooshInboxStyle.setDateColor(optColor("dateColor", pushwooshInboxStyle.getDateColor()));
            pushwooshInboxStyle.setReadDateColor(optColor("readDateColor", pushwooshInboxStyle.getReadDateColor()));
            pushwooshInboxStyle.setTitleColor(optColor("titleColor", pushwooshInboxStyle.getTitleColor()));
            pushwooshInboxStyle.setReadTitleColor(optColor("readTitleColor", pushwooshInboxStyle.getReadTitleColor()));
            pushwooshInboxStyle.setDescriptionColor(optColor("descriptionColor", pushwooshInboxStyle.getDescriptionColor()));
            pushwooshInboxStyle.setReadDescriptionColor(optColor("readDescriptionColor", pushwooshInboxStyle.getReadDescriptionColor()));
        }
    }

    public static void setStyle(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            new StyleParser(context, jSONObject).parse();
        }
    }
}
