package com.pushwoosh.inbox;

import com.pushwoosh.internal.event.AppIdChangedEvent;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;

/* renamed from: com.pushwoosh.inbox.-$$Lambda$PushwooshInboxPlugin$qbqKLhN2d0EpFjsAZmiNWPiOeXQ  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$PushwooshInboxPlugin$qbqKLhN2d0EpFjsAZmiNWPiOeXQ implements EventListener {
    public static final /* synthetic */ $$Lambda$PushwooshInboxPlugin$qbqKLhN2d0EpFjsAZmiNWPiOeXQ INSTANCE = new $$Lambda$PushwooshInboxPlugin$qbqKLhN2d0EpFjsAZmiNWPiOeXQ();

    private /* synthetic */ $$Lambda$PushwooshInboxPlugin$qbqKLhN2d0EpFjsAZmiNWPiOeXQ() {
    }

    @Override // com.pushwoosh.internal.event.EventListener
    public final void onReceive(Event event) {
        PushwooshInboxPlugin.a((AppIdChangedEvent) event);
    }
}
