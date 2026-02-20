package com;

import java.util.ArrayList;
import java.util.List;

import static com.FolderValidator.*;

public record FolderImpl(String name, String size, List<Folder> folders) implements MultiFolder {

    public FolderImpl(String name, String size) {
        this(name, size, new ArrayList<>());
    }

    public FolderImpl(String name, String size, List<Folder> folders) {
        checkName(name);
        checkSize(size);
        this.name = name;
        this.size = size;
        this.folders = folders;
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
