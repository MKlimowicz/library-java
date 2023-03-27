package org.example.userinterfaces;

import org.example.constants.OptionConstants;
import org.example.domain.Book;
import org.example.domain.BookFields;
import org.example.domain.MagazineFields;
import org.example.domain.Publication;
import org.example.services.createdatafromconsole.CreateDataFromConsoleService;
import org.example.services.library.LibraryService;
import org.example.services.printer.ConsolePrinterService;
import org.example.services.reader.ConsoleReaderService;

import java.util.Collection;
import java.util.List;


public class LibraryUI {
    private ConsolePrinterService consolePrinterService;
    private CreateDataFromConsoleService createData;
    private LibraryService libraryService;
    private ConsoleReaderService consoleReaderService;
    private OptionUI optionUI;

    public LibraryUI(ConsolePrinterService consolePrinterService,
                     CreateDataFromConsoleService createDataFromConsoleService,
                     LibraryService libraryService,
                     ConsoleReaderService consoleReaderService) {
        this.consolePrinterService = consolePrinterService;
        this.createData = createDataFromConsoleService;
        this.libraryService = libraryService;
        this.consoleReaderService = consoleReaderService;
        this.optionUI = new OptionUI(consolePrinterService);
    }


    public void start() {
        var flag = true;

        while (flag) {
            optionUI.printOptions();
            var idFromUser = consoleReaderService.getIdFromUser();
            var optionOpt = optionUI.findOptionById(idFromUser);

            if (optionOpt.isEmpty()) {
                System.out.println("Opcja ktora wybrales nie istnieje");
                break;
            }

            switch (optionOpt.get().getDescription()) {
                case OptionConstants.EXIT -> flag = false;
                case OptionConstants.ADD_BOOK -> addBook();
                case OptionConstants.SHOW_BOOKS -> showBooks();
                case OptionConstants.ADD_MAGAZINE -> addMagazine();
                case OptionConstants.SHOW_MAGAZINE -> showMagazines();
                case OptionConstants.DELETE_BOOK -> deleteBook();
                case OptionConstants.DELETE_MAGAZINE -> deleteMagazine();
                case OptionConstants.UPDATE_BOOK -> updateBook();
                case OptionConstants.UPDATE_MAGAZINE -> updateMagazine();
                case OptionConstants.FIND_PUBLICATION -> findPublicationByTitle();
                case OptionConstants.ALL_PUBLICATIONS -> findAllPublications();
                default -> System.out.println("Opcja ktora wybrales nie zostala jeszcze zaimplementowana");
            }
        }

    }

    private void findAllPublications() {
        var publications = List.of(libraryService.findAllBook(), libraryService.findAllMagazine()).stream().flatMap(Collection::stream).toList();
        consolePrinterService.printPublications(publications);
    }

    private void updateBook() {
        showBooks();
        int idBook = consoleReaderService.getIdFromUser("### Podaj id ksiazki ktora chcesz zaktualizowac: ");
        System.out.println();
        System.out.println("xx Mozliwe opcje do edycji: ");
        var bookFields = BookFields.values();
        for (int i = 0; i < bookFields.length; i++) {
            var bookField = bookFields[i];
            System.out.printf("ID: %s, Nazwa pola: %s%n", i, bookField);
        }
        int idField = consoleReaderService.getIdFromUser("### Podaj id pola ktora chcesz zaktualizowac: ");
        var newValue = consoleReaderService.getValueTextFromUser("### Podaj nowa wartosc: ");
        var bookField = bookFields[idField];
        libraryService.updateBook(bookField, newValue, (long) idBook);

    }

    private void updateMagazine() {
        showMagazines();
        int magazineId = consoleReaderService.getIdFromUser("### Podaj id magazynu ktora chcesz zaktualizowac: ");
        System.out.println();
        System.out.println("xx Mozliwe opcje do edycji: ");
        var magazineFields = MagazineFields.values();
        for (int i = 0; i < magazineFields.length; i++) {
            var magazineField = magazineFields[i];
            System.out.printf("ID: %s, Nazwa pola: %s%n", i, magazineField);
        }
        int idField = consoleReaderService.getIdFromUser("### Podaj id pola ktora chcesz zaktualizowac: ");
        var newValue = consoleReaderService.getValueTextFromUser("### Podaj nowa wartosc: ");
        var bookField = magazineFields[idField];
        libraryService.updateMagazine(bookField, newValue, (long) magazineId);

    }

    private void findPublicationByTitle() {
        var title = consoleReaderService.getTitleFromUser();
        consolePrinterService.printPublications(libraryService.findPublicationByTitle(title));
    }

    private void showBooks() {
        consolePrinterService.printPublications(libraryService.findAllBook());
    }

    private void addBook() {
        System.out.println("### Dodawanie ksiazki ###");
        var book = createData.createBook();
        libraryService.addBook(book);
        System.out.println("### Ksiazka zostala dodana poprawnie ###");
    }

    private void showMagazines() {
        consolePrinterService.printPublications(libraryService.findAllMagazine());
    }

    private void addMagazine() {
        System.out.println("### Dodawanie magazynu ###");
        var magazine = createData.createMagazine();
        libraryService.addMagazine(magazine);
        System.out.println("### Magazyn zostal dodany poprawnie ###");
    }


    private void deleteBook() {
        showBooks();
        System.out.println("### Podaj numer publikacja ktora chcesz usunac ###");
        int publicationId = consoleReaderService.getIdFromUser();
        libraryService.deleteBook((long) publicationId);
    }

    private void deleteMagazine() {
        showMagazines();
        System.out.println("### Podaj numer publikacja ktora chcesz usunac ###");
        int publicationId = consoleReaderService.getIdFromUser();
        libraryService.deleteMagazine((long) publicationId);
    }



}
