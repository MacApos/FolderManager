package com;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = new ArrayList<>();
    }

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

    private void sortMultiFolderLast(List<Folder> folders) {
        folders.sort(Comparator.comparing(folder -> folder instanceof MultiFolder ? 0 : 1));
    }

    private void recursion(List<Folder> folders) {
        sortMultiFolderLast(folders);
        for (Folder folder : folders) {
            System.out.println(folder.getName());
            if (folder instanceof MultiFolder multiFolder) {
                recursion(multiFolder.getFolders());
            }
        }
    }

    private <T> Stream<Folder> filterBy(Function<Folder, T> extractionFunction, String field) {
        folders.sort(Comparator.comparing(folder -> folder instanceof MultiFolder ? 0 : 1));

        for (Folder folder : folders) {
            if (folder instanceof MultiFolder) {

            }
        }

        return folders.stream().filter(f -> Objects.equals(extractionFunction.apply(f), field));
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        recursion(folders);
        return filterBy(Folder::getName, name).findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return filterBy(Folder::getSize, size).toList();
    }

    @Override
    public int count() {
        return folders.size();
    }
}
