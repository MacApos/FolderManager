package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiFolderImplTest {
    private MultiFolderImpl documents;
    private MultiFolderImpl generalDocuments;

    @BeforeEach
    public void initiateFolders() {
        generalDocuments = new MultiFolderImpl("General", "MEDIUM");
        documents = new MultiFolderImpl("Documents", "LARGE", List.of(generalDocuments));
    }

    @Test
    void givenNullName_whenInitiatingFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new MultiFolderImpl(null, "large", new ArrayList<>()));
    }

    @Test
    void givenBlankName_whenInitiatingFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new MultiFolderImpl(" ", "large", new ArrayList<>()));
    }

    @Test
    void givenInvalidName_whenInitiatingFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new MultiFolderImpl("new/folder", "large", new ArrayList<>()));
    }

    @Test
    void givenNullSize_whenInitiatingFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new MultiFolderImpl("Documents", null, new ArrayList<>()));
    }

    @Test
    void givenBlankSize_whenInitiatingFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new MultiFolderImpl("Documents", " ", new ArrayList<>()));
    }

    @Test
    void givenInvalidSize_whenInitiatingFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new MultiFolderImpl("Documents", "EXTRA_LARGE", new ArrayList<>()));
    }

    @Test
    void givenValidArguments_whenInitiatingFolderImpl_thenInitiateFolderImpl() {
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