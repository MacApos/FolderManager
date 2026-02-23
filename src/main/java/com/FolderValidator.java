package com;

import java.util.List;

public class FolderValidator {
    private enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    private FolderValidator() {}

    private static void isNullOrBlank(String field, RuntimeException exception) {
        if (field == null || field.isBlank()) {
            throw exception;
        }
    }

    public static void checkName(String name) {
        var exception = new IllegalArgumentException("Invalid folder name!");
        isNullOrBlank(name, exception);
        if (name.contains("/")) {
            throw exception;
        }
    }

    public static void checkSize(String size) {
        var exception = new IllegalArgumentException("Invalid folder size!");
        isNullOrBlank(size, exception);
        try {
            Size.valueOf(size);
        } catch (Exception e) {
            throw exception;
        }
    }
}
