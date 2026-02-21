package com;

import java.util.ArrayList;
import java.util.List;

import static com.FolderValidator.checkName;
import static com.FolderValidator.checkSize;

public record MultiFolderImpl(String name, String size, List<Folder> folders) implements MultiFolder {

    public MultiFolderImpl {
        checkName(name);
        checkSize(size);
    }

    public MultiFolderImpl(String name, String size) {
        this(name, size, new ArrayList<>());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }
}