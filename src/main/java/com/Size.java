package com;

public enum Size {
    SMALL,
    MEDIUM,
    LARGE;

    public static void checkSize(String size) {
        try {
            Size.valueOf(size);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid folder size!");
        }
    }
}
