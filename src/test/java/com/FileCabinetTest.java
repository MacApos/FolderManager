package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileCabinetTest {
    FileCabinet fileCabinet;

    //    parent folder no. 1
    Folder documents;
    Folder januaryDocuments;
    Folder topSecreteDocuments;

    //    parent folder no. 2
    Folder invoices;
    Folder februaryInvoices;

    //    empty folder
    Folder complains = new FolderImpl("Complains", "small");

    @BeforeEach
    void initiateFolders() {
        topSecreteDocuments = new FolderImpl("February", "medium");
        januaryDocuments = new FolderImpl("January", "medium");
        documents = new FolderImpl("Documents", "large", List.of(topSecreteDocuments, januaryDocuments));

        februaryInvoices = new FolderImpl("February", "medium");
        invoices = new FolderImpl("Invoices", "large", List.of(februaryInvoices));

        complains = new FolderImpl("Complains", "small");

        fileCabinet = new FileCabinet(List.of(documents, invoices, complains));
    }

    @Test
    void findFolderByName() {
        fileCabinet.findFolderByName("Invoices");
    }

    @Test
    void findFoldersBySize() {
    }

    @Test
    void count() {
    }
}