package com;

import java.util.List;

import static java.lang.System.out;

public class Main {
    static FileCabinet fileCabinet;

    static MultiFolder documents;
    static MultiFolder publicDocuments;
    static MultiFolder topSecreteDocuments;
    static MultiFolder otherDocuments;

    static MultiFolder invoices;
    static MultiFolder projectInvoices;
    static MultiFolder standardInvoices;

    static MultiFolder complains;

    static {
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

    public static void main(String[] args) {
        out.println("Cabinet structure: ");
        recursivePass(fileCabinet.folders(), "");

        out.println("\nFind folder with name of \"Invoices\":");
        fileCabinet.findFolderByName("Invoices").ifPresentOrElse(out::println,
                                                                 () -> out.println("Folder not found"));
        out.println("\nFind folder with name of \"Documents\":");
        fileCabinet.findFolderByName("Documents").ifPresentOrElse(out::println,
                                                                  () -> out.println("Folder not found"));
        out.println("\nFind folders with size SMALL:");
        fileCabinet.findFoldersBySize("SMALL").forEach(folder -> out.println(folder.getName()));

        out.println("\nFind folders with size MEDIUM:");
        fileCabinet.findFoldersBySize("MEDIUM").forEach(folder -> out.println(folder.getName()));

        out.println("\nCount folders:");
        out.println(fileCabinet.count());
    }

    private static void recursivePass(List<Folder> folders, String indent) {
        for (Folder folder : folders) {
            out.println(indent + "| " + folder.getName());
            if (folder instanceof MultiFolder multiFolder) {
                recursivePass(multiFolder.getFolders(), indent + "| ");
            }
        }
    }
}
