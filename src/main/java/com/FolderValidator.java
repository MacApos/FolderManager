package com;

import java.util.HashSet;
import java.util.List;

public class FolderValidator {
    private enum Size {
        SMALL(0),
        MEDIUM(1),
        LARGE(2);

        private final int order;

        Size(int order) {
            this.order = order;
        }
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

    public static void checkFoldersForDuplicates(List<Folder> folders) {
        HashSet<Folder> hashSet = new HashSet<>();
        for (Folder folder : folders) {
            if (!hashSet.add(folder)) {
                throw new IllegalArgumentException("Duplicate folder name!");
            }
        }
    }

    public static void checkFoldersSize(String size, List<Folder> folders) {
        int order = Size.valueOf(size).order;
        for (Folder folder : folders) {
            if (order <= Size.valueOf(folder.getSize()).order) {
                throw new IllegalArgumentException("Too large folder size!");
            }
        }
    }
}
