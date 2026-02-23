package com;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.FolderValidator.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FolderValidatorTest {
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
        assertDoesNotThrow(() -> checkName("new-folder"));
    }

    @Test
    void givenNullSize_whenCheckSize_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkSize(null));
    }

    @Test
    void givenNullOrBlankSize_whenCheckSize_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkSize(""));
        assertThrows(IllegalArgumentException.class, () -> checkSize(" "));
    }

    @Test
    void givenInvalidName_whenCheckSize_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkSize("EXTRA_LARGE"));
        assertDoesNotThrow(() -> checkSize("MEDIUM"));
    }

    @Test
    void givenInvalidFolders_whenCheckFolder_thenThrowException(){
        MultiFolderImpl topSecreteDocuments = new MultiFolderImpl("TopSecreteDocuments", "MEDIUM");

        MultiFolderImpl documents = new MultiFolderImpl("Documents", "MEDIUM", List.of(topSecreteDocuments));

        checkFoldersSize(documents.size(), documents.folders());
    }
}