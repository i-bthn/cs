package com.pushwoosh.internal.event;

import java.util.List;

public class PermissionEvent implements Event {
    private final List<String> a;
    private final List<String> b;

    public PermissionEvent(List<String> list, List<String> list2) {
        this.a = list;
        this.b = list2;
    }

    public List<String> getDeniedPermissions() {
        return this.b;
    }

    public List<String> getGrantedPermissions() {
        return this.a;
    }
}
