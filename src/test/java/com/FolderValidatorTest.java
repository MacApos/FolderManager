package com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.FolderValidator.*;

class FolderValidatorTest {
    @Test
    void givenNullOrEmptyName_whenCheckName_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkName(null));
        assertThrows(IllegalArgumentException.class, () -> checkName(""));
        assertThrows(IllegalArgumentException.class, () -> checkName("  "));
    }

    @Test
    void givenInvalidName_whenCheckName_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkName("new/folder"));
        assertDoesNotThrow(() -> checkName("new-folder"));
    }

    @Test
    void givenNullOrEmptySize_whenCheckSize_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkSize(null));
        assertThrows(IllegalArgumentException.class, () -> checkSize(""));
        assertThrows(IllegalArgumentException.class, () -> checkSize("  "));
    }

    @Test
    void givenInvalidName_whenCheckSize_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkSize("extra-large"));
        assertDoesNotThrow(() -> checkSize("medium"));
    }
}