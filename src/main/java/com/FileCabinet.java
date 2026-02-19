package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = new ArrayList<>(folders);
    }

    public List<Folder> getFolders() {
        return folders;
    }

    private <T> Stream<Folder> filterBy(List<Folder> folders, Function<Folder, T> extractionFunction, String field) {
        return folders.stream().filter(f -> Objects.equals(extractionFunction.apply(f), field));
    }

    public ArrayList<Folder> recursivePass(List<Folder> folders, ArrayList<Folder> flatFolders) {
        for (Folder folder : folders) {
            flatFolders.add(folder);
            if (folder instanceof MultiFolder multiFolder) {
                recursivePass(multiFolder.getFolders(), flatFolders);
            }
        }
        return flatFolders;
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return filterBy(folders, Folder::getName, name).findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        Size.checkSize(size);
        return filterBy(recursivePass(folders, new ArrayList<>()), Folder::getSize, size).toList();
    }

    @Override
    public int count() {
        return recursivePass(folders, new ArrayList<>()).size();
    }
}
