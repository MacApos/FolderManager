package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiFolderImplTest {
    private MultiFolderImpl documents;
    private MultiFolder publicDocuments;
    private MultiFolder topSecreteDocuments;

    @BeforeEach public void initiateFolders() {
        publicDocuments = new MultiFolderImpl("PublicDocuments", "MEDIUM");
        topSecreteDocuments = new MultiFolderImpl("TopSecreteDocuments", "SMALL");
        documents = new MultiFolderImpl("Documents", "LARGE", List.of(publicDocuments, topSecreteDocuments));
    }

    @Test void givenNullName_whenInitiatingMultiFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new MultiFolderImpl(null, "LARGE", new ArrayList<>()));
    }

    @Test void givenBlankName_whenInitiatingMultiFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new MultiFolderImpl(" ", "LARGE", new ArrayList<>()));
    }

    @Test void givenInvalidName_whenInitiatingMultiFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new MultiFolderImpl("new/folder", "LARGE", new ArrayList<>()));
    }

    @Test void givenNullSize_whenInitiatingMultiFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new MultiFolderImpl("Documents", null, new ArrayList<>()));
    }

    @Test void givenBlankSize_whenInitiatingMultiFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new MultiFolderImpl("Documents", " ", new ArrayList<>()));
    }

    @Test void givenInvalidSize_whenInitiatingMultiFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new MultiFolderImpl("Documents", "EXTRA_LARGE", new ArrayList<>()));
    }

    @Test void givenFoldersWithDuplicates_whenInitiatingMultiFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new MultiFolderImpl("Documents", "LARGE",
                                               List.of(publicDocuments, publicDocuments,
                                                       topSecreteDocuments)));
    }

    @Test void givenTooLargeOrEqualSizeFolder_whenInitiatingMultiFolderImpl_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                     () -> new MultiFolderImpl("Documents", "SMALL", List.of(publicDocuments,
                                                                             topSecreteDocuments)));
    }

    @Test void givenValidArguments_whenInitiatingMultiFolderImpl_thenInitiateMultiFolderImpl() {
        assertNotNull(documents);
    }

    @Test void getName() {
        assertEquals("PublicDocuments", publicDocuments.getName());
    }

    @Test void name() {
        assertEquals("Documents", documents.name());
    }

    @Test void getSize() {
        assertEquals("MEDIUM", publicDocuments.getSize());
    }

    @Test void size() {
        assertEquals("LARGE", documents.getSize());
    }

    @Test void getFolders() {
        assertIterableEquals(new ArrayList<>(), publicDocuments.getFolders());
    }

    @Test void folders() {
        assertIterableEquals(List.of(publicDocuments, topSecreteDocuments), documents.getFolders());
    }
}