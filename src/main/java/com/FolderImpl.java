package com;

import java.util.ArrayList;
import java.util.List;

public class FolderImpl implements MultiFolder{
    private String name;
    private String size;
    private List<Folder> folders;
    private final List<String> availableSize = List.of("SMALL","MEDIUM","LARGE");

    public FolderImpl(String name, String size) {
        checkSize(size);
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

    public void addFolder(Folder folder) {
        assert folder != null;
        folders.add(folder);
    }

    public void removeFolder(Folder folder) {
        assert folder != null;
        folders.remove(folder);
    }

    private void checkSize(String size){
        if(!availableSize.contains(size.toUpperCase())){
            throw new RuntimeException("Invalid folder size!");
        }
    }
}
