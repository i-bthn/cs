package com.pushwoosh.inbox.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.Serializable;
import java.util.Date;

public interface InboxMessage extends Serializable, Comparable<InboxMessage> {
    @Nullable
    String getBannerUrl();

    @NonNull
    String getCode();

    @Nullable
    String getImageUrl();

    @NonNull
    String getMessage();

    @NonNull
    Date getSendDate();

    @Nullable
    String getTitle();

    @NonNull
    InboxMessageType getType();

    boolean isActionPerformed();

    boolean isRead();
}
