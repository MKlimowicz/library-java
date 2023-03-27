package org.example;

import org.example.filemanager.LibraryFileManagerImpl;
import org.example.services.createdatafromconsole.CreateDataFromConsoleServiceImpl;
import org.example.services.library.LibraryServiceImpl;
import org.example.services.printer.ConsolePrinterServiceImpl;
import org.example.services.reader.ConsoleReaderServiceImpl;
import org.example.userinterfaces.LibraryUI;

public class Main {
    public static void main(String[] args) {
        var consolePrinterService = new ConsolePrinterServiceImpl();
        var createDataFromConsoleService = new CreateDataFromConsoleServiceImpl();
        var libraryFileManager = new LibraryFileManagerImpl();
        var libraryService = new LibraryServiceImpl(libraryFileManager);
        var consoleReaderService = new ConsoleReaderServiceImpl();
        var libraryUI = new LibraryUI(consolePrinterService, createDataFromConsoleService, libraryService, consoleReaderService);
        libraryUI.start();
    }
}