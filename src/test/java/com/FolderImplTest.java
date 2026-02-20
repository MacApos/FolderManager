package com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FolderImplTest {

    @Test
    void initiateFolderImpl() {
        new FolderImpl("Documents", "large");
        assertThrows(IllegalArgumentException.class, ()->new FolderImpl("", "", new ArrayList<>()));
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