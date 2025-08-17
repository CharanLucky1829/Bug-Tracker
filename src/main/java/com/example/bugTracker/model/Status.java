package com.example.bugTracker.model;

public enum Status {
    NEW,
    OPEN,
    IN_PROGRESS,
    CLOSED;

    public static Status fromString(String value) {
        try {
            return Status.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return OPEN; // default fallback
        }
    }
}
