package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FolderImplTest {
    private FolderImpl documents;
    private FolderImpl generalDocuments;

    @BeforeEach
    public void initiateFolders() {
        generalDocuments = new FolderImpl("General", "MEDIUM");
        documents = new FolderImpl("Documents", "LARGE", List.of(generalDocuments));
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
                     () -> new FolderImpl("Documents", "EXTRA_LARGE", new ArrayList<>()));
    }

    @Test
    void givenValidArguments_whenInitiatingFolderImpl_thanInitiateFolderImpl() {
        assertNotNull(documents);
    }

    @Test
    void getName() {
        assertEquals("General", generalDocuments.getName());
    }

    @Test
    void name() {
        assertEquals("Documents", documents.name());
    }

    @Test
    void getSize() {
        assertEquals("LARGE", documents.getSize());
    }

    @Test
    void size() {
        assertEquals("MEDIUM", generalDocuments.getSize());
    }

    @Test
    void getFolders() {
        assertIterableEquals(List.of(generalDocuments), documents.getFolders());
    }

    @Test
    void folders() {
        assertIterableEquals(new ArrayList<>(), generalDocuments.getFolders());
    }


}