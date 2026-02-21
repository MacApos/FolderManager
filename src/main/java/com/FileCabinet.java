package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.FolderValidator.checkName;
import static com.FolderValidator.checkSize;

public record FileCabinet(List<Folder> folders) implements Cabinet {

    @Override
    public Optional<Folder> findFolderByName(String name) {
        checkName(name);
        return filterBy(Folder::getName, name).findAny();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        checkSize(size);
        return filterBy(Folder::getSize, size).toList();
    }

    @Override
    public int count() {
        return recursivePass(folders, new ArrayList<>()).size();
    }

    private <T> Stream<Folder> filterBy(Function<Folder, T> extractionFunction, T field) {
        var flatFolders = recursivePass(folders, new ArrayList<>());
        return flatFolders.stream()
                          .parallel()
                          .filter(folder -> Objects.equals(extractionFunction.apply(folder), field));
    }

    private ArrayList<Folder> recursivePass(List<Folder> folders, ArrayList<Folder> foundFolders) {
        for (Folder folder : folders) {
            foundFolders.add(folder);
            if (folder instanceof MultiFolder multiFolder) {
                recursivePass(multiFolder.getFolders(), foundFolders);
            }
        }
        return foundFolders;
    }
}
