package com;

import org.junit.jupiter.api.Test;

import static com.FolderValidator.checkName;
import static com.FolderValidator.checkSize;
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
}