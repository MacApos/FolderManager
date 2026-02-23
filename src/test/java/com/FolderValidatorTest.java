package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.FolderValidator.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FolderValidatorTest {
    private MultiFolder publicDocuments;
    private MultiFolder topSecreteDocuments;

    @BeforeEach
    void initiateFolders() {
        publicDocuments = new MultiFolderImpl("PublicDocuments", "MEDIUM");
        topSecreteDocuments = new MultiFolderImpl("TopSecreteDocuments", "SMALL");
    }

    @Test
    void givenNullName_whenCheckName_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkName(null));
    }

    @Test
    void givenBlankName_whenCheckName_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkName(""));
        assertThrows(IllegalArgumentException.class, () -> checkName(" "));
    }

    @Test
    void givenInvalidName_whenCheckName_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkName("new/folder"));
    }

    @Test
    void givenValidName_whenCheckName_thenDontThrowException() {
        assertDoesNotThrow(() -> checkName("new-folder"));
    }

    @Test
    void givenNullSize_whenCheckSize_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkSize(null));
    }

    @Test
    void givenBlankSize_whenCheckSize_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkSize(""));
        assertThrows(IllegalArgumentException.class, () -> checkSize(" "));
    }

    @Test
    void givenInvalidName_whenCheckSize_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkSize("EXTRA_LARGE"));
        assertDoesNotThrow(() -> checkSize("MEDIUM"));
    }

    @Test
    void givenFoldersWithDuplicates_whenCheckFolderForDuplicates_thenThrowException() {
        List<Folder> folders = List.of(publicDocuments, publicDocuments, topSecreteDocuments);
        assertThrows(IllegalArgumentException.class, () -> checkFoldersForDuplicates(folders));
    }

    @Test
    void givenFoldersWithoutDuplicates_whenCheckFolderForDuplicates_thenDontThrowException() {
        List<Folder> folders = List.of(publicDocuments, topSecreteDocuments);
        assertDoesNotThrow(() -> checkFoldersForDuplicates(folders));
    }

    @Test
    void givenTooLargeOrEqualSizeFolder_whenCheckFolder_thenThrowException() {
        List<Folder> folders = List.of(publicDocuments, topSecreteDocuments);
        assertThrows(IllegalArgumentException.class, () -> checkFoldersSize("SMALL", folders));
        assertThrows(IllegalArgumentException.class, () -> checkFoldersSize("MEDIUM", folders));
    }

    @Test
    void givenSmallerFolders_whenCheckFolder_thenDontThrowException() {
        assertDoesNotThrow(() -> checkFoldersSize("LARGE", List.of(publicDocuments, topSecreteDocuments)));
    }
}