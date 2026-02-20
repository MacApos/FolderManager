package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileCabinetTest {
    private FileCabinet fileCabinet;

    private FolderImpl documents;
    private FolderImpl generalDocuments;
    private FolderImpl topSecreteDocuments;

    private FolderImpl invoices;
    private FolderImpl generalInvoices;
    private FolderImpl standardInvoices;

    private FolderImpl complains;
    private FolderImpl general;

    @BeforeEach
    void initiateDocuments() {
        generalDocuments = new FolderImpl("General", "MEDIUM");
        topSecreteDocuments = new FolderImpl("TopSecrete", "SMALL");
        documents = new FolderImpl("Documents", "LARGE", List.of(topSecreteDocuments, generalDocuments));
    }

    @BeforeEach
    void initiateInvoices() {
        generalInvoices = new FolderImpl("General", "SMALL");
        generalInvoices = new FolderImpl("General", "SMALL");
        invoices = new FolderImpl("Invoices", "LARGE", List.of(generalInvoices));
    }

    @BeforeEach
    void initiateOtherFolders(){
        complains = new FolderImpl("Complains", "SMALL");
        general = new FolderImpl("General", "LARGE");
    }

    @BeforeEach
    void initiateFileCabinet(){
        fileCabinet = new FileCabinet(List.of(documents, invoices, complains));
    }

    @Test
    void givenNullName_whenFindFolderByName_thanThrowException() {}

    @Test
    void givenBlankName_whenFindFolderByName_thanThrowException() {}

    @Test
    void givenNullSize_whenFindFoldersBySize_thanThrowException() {}

    @Test
    void givenBlankSize_whenFindFoldersBySize_thanThrowException() {}

    @Test
    void count() {
    }
}