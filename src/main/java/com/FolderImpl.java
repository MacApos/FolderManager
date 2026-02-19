package com;

import java.util.ArrayList;
import java.util.List;

public class FolderImpl implements MultiFolder {
    private String name;
    private String size;
    private List<Folder> folders;

    public FolderImpl(String name, String size) {
        Size.checkSize(size);
        this.name = name;
        this.size = size;
        this.folders = new ArrayList<>();
    }

    public FolderImpl(String name, String size, List<Folder> folders) {
        this(name, size);
        this.folders = new ArrayList<>(folders);
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
