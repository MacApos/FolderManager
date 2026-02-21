package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class FileCabinetTest {
    private FileCabinet fileCabinet;

    private MultiFolder documents;
    private MultiFolder publicDocuments;
    private MultiFolder topSecreteDocuments;
    private MultiFolder otherDocuments;

    private MultiFolder invoices;
    private MultiFolder projectInvoices;
    private MultiFolder standardInvoices;

    private MultiFolder complains;

    @BeforeEach
    void initiateFolders() {
        publicDocuments = new MultiFolderImpl("PublicDocuments", "MEDIUM");
        topSecreteDocuments = new MultiFolderImpl("TopSecreteDocuments", "SMALL");
        documents = new MultiFolderImpl("Documents", "LARGE", List.of(topSecreteDocuments, publicDocuments));
        otherDocuments = new MultiFolderImpl("Documents", "SMALL");

        standardInvoices = new MultiFolderImpl("StandardInvoices", "SMALL");
        projectInvoices = new MultiFolderImpl("ProjectInvoices", "MEDIUM");
        invoices = new MultiFolderImpl("Invoices", "LARGE", List.of(projectInvoices, standardInvoices));

        complains = new MultiFolderImpl("Complains", "SMALL");

        fileCabinet = new FileCabinet(List.of(documents, invoices, complains, otherDocuments));
    }

    @Test
    void givenNullName_whenFindFolderByName_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> fileCabinet.findFolderByName(null));
    }

    @Test
    void givenBlankName_whenFindFolderByName_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> fileCabinet.findFolderByName(" "));
    }

    @Test
    void givenInvalidName_whenFindFolderByName_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> fileCabinet.findFolderByName("new/folder"));
    }

    @Test
    void givenValidName_whenFindFolderByName_thanReturnFolder() {
        Optional<Folder> folderByName = fileCabinet.findFolderByName("Invoices");
        assertTrue(folderByName.isPresent());
        assertEquals(folderByName.get(), invoices);
    }

    @Test
    void givenRecurringName_whenFindFolderByName_thanReturnAnyFolder() {
        Optional<Folder> folderByName = fileCabinet.findFolderByName("Documents");
        assertTrue(folderByName.isPresent());
        assertThat(folderByName.get(), anyOf(is(documents), is(otherDocuments)));
    }

    @Test
    void givenNullSize_whenFindFoldersBySize_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> fileCabinet.findFoldersBySize(null));
    }

    @Test
    void givenBlankSize_whenFindFoldersBySize_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> fileCabinet.findFoldersBySize(" "));
    }

    @Test
    void givenInvalidSize_whenFindFoldersBySize_thanThrowException() {
        assertThrows(IllegalArgumentException.class, () -> fileCabinet.findFoldersBySize("EXTRA_LARGE"));
    }

    @Test
    void givenValidSize_whenFindFoldersBySize_thanReturnFolders() {
        List<Folder> smallFolders = fileCabinet.findFoldersBySize("SMALL");
        assertIterableEquals(List.of(topSecreteDocuments, standardInvoices, complains, otherDocuments), smallFolders);

        List<Folder> foldersBySize = fileCabinet.findFoldersBySize("MEDIUM");
        assertIterableEquals(List.of(publicDocuments, projectInvoices), foldersBySize);
    }

    @Test
    void whenCount_thanReturnFolders() {
        assertEquals(8, fileCabinet.count());
    }
}