package com;

import java.util.List;

import static java.lang.System.out;

public class Main {
    public static MultiFolder publicDocuments = new MultiFolderImpl("PublicDocuments", "MEDIUM");
    public static MultiFolder topSecreteDocuments = new MultiFolderImpl("TopSecreteDocuments", "SMALL");
    public static MultiFolder documents = new MultiFolderImpl("Documents", "LARGE",
                                                              List.of(topSecreteDocuments, publicDocuments));
    public static MultiFolder otherDocuments = new MultiFolderImpl("Documents", "SMALL");

    public static MultiFolder standardInvoices = new MultiFolderImpl("StandardInvoices", "SMALL");
    public static MultiFolder projectInvoices = new MultiFolderImpl("ProjectInvoices", "MEDIUM");
    public static MultiFolder invoices = new MultiFolderImpl("Invoices", "LARGE",
                                                             List.of(projectInvoices, standardInvoices));

    public static MultiFolder complains = new MultiFolderImpl("Complains", "SMALL");

    public static FileCabinet fileCabinet = new FileCabinet(List.of(documents, invoices, complains, otherDocuments));

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
