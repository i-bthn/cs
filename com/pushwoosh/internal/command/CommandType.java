package com.pushwoosh.internal.command;

import androidx.annotation.NonNull;

@FunctionalInterface
public interface CommandType {
    @NonNull
    String getType();
}
