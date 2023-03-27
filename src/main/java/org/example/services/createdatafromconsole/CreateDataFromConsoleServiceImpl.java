package org.example.services.createdatafromconsole;

import org.example.domain.Book;
import org.example.domain.Magazine;

import java.util.Scanner;

public class CreateDataFromConsoleServiceImpl implements CreateDataFromConsoleService {

    private Scanner scanner;

    public CreateDataFromConsoleServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Book createBook() {
        System.out.println("Podaj tytuł");
        var title = scanner.nextLine();
        System.out.println("Podaj authora");
        var author = scanner.nextLine();
        System.out.println("Podaj wydawnictwo");
        var publisher = scanner.nextLine();
        System.out.println("Podaj numer ISBN:");
        var isbn = scanner.nextLine();
        System.out.println("Podaj rok wydania:");
        var year = scanner.nextLine();
        System.out.println("Podaj liczbe strone:");
        var pages = scanner.nextLine();

        return new Book(title, publisher, Integer.parseInt(year), author, pages, isbn);
    }

    @Override
    public Magazine createMagazine() {
        System.out.println("Podaj tytuł");
        var title = scanner.nextLine();
        System.out.println("Podaj wydawnictwo");
        var publisher = scanner.nextLine();
        System.out.println("Podaj jezyk:");
        var language = scanner.nextLine();
        System.out.println("Podaj rok:");
        var year = scanner.nextLine();
        System.out.println("Podaj dokladna date publikacji:");
        var publicationDate = scanner.nextLine();

        return new Magazine(title, publisher, Integer.parseInt(year), language, publicationDate);
    }

}
