package com;

public class FolderValidator {
    private enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    private FolderValidator() {}

    private static void isNullOrEmpty(String field, RuntimeException exception) {
        if (field == null || field.isBlank()) {
            throw exception;
        }
    }

    public static void checkName(String name) {
        var exception = new IllegalArgumentException("Invalid folder name!");
        isNullOrEmpty(name, exception);
        if (name.contains("/")) {
            throw exception;
        }
    }

    public static void checkSize(String size) {
        var exception = new IllegalArgumentException("Invalid folder size!");
        isNullOrEmpty(size, exception);
        try {
            Size.valueOf(size.toUpperCase());
        } catch (Exception e) {
            throw exception;
        }
    }
}
