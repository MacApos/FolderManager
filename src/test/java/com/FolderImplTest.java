package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FolderImplTest {
    Folder documents;
    Folder januaryDocuments;

    @BeforeEach
    public void initiateFolders() {
        januaryDocuments = new FolderImpl("January", "medium");
        documents = new FolderImpl("Documents", "large", List.of(januaryDocuments));
    }

    @Test
    void givenNullName_whenInitiatingFolderImpl_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new FolderImpl(null, "large", new ArrayList<>()));
    }

    @Test
    void givenBlankName_whenInitiatingFolderImpl_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new FolderImpl("  ", "large", new ArrayList<>()));
    }

    @Test
    void givenInvalidName_whenInitiatingFolderImpl_thanThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new FolderImpl("new/folder", "large", new ArrayList<>()));
    }

    @Test
    void givenNullSize_whenInitiatingFolderImpl_thanThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new FolderImpl("Documents", null, new ArrayList<>()));
    }

    @Test
    void givenBlankSize_whenInitiatingFolderImpl_thanThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new FolderImpl("Documents", "  ", new ArrayList<>()));
    }

    @Test
    void givenInvalidSize_whenInitiatingFolderImpl_thanThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new FolderImpl("Documents", "extra_large", new ArrayList<>()));
    }


    @Test
    void givenValidArguments_whenInitiatingFolderImpl_thanInitiateFolderImpl() {
        new FolderImpl("Documents", "large");
    }

    @Test
    void getName() {
    }

    @Test
    void getSize() {
    }

    @Test
    void getFolders() {
    }
}