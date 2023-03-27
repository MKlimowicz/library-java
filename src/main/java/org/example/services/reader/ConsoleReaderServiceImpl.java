package org.example.services.reader;

import org.example.exception.ConsoleReaderValidateException;

import java.util.Scanner;

public class ConsoleReaderServiceImpl implements ConsoleReaderService {
    private Scanner scanner;

    public ConsoleReaderServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int getIdFromUser() {
        System.out.println("Podaj która czynność chcesz wykonac: ");
        return getNumberValue();
    }

    @Override
    public int getIdFromUser(String comment) {
        System.out.println(comment);
        return getNumberValue();
    }
    private int getNumberValue() {
        try {
            var next = scanner.next();
            return Integer.parseInt(next);
        } catch (NumberFormatException numberValue) {
            throw new ConsoleReaderValidateException(
                    "Wystąpił błąd. Musisz przekazać liczbe calkowita."
            );
        }
    }

    @Override
    public String getTitleFromUser() {
        System.out.println("Podaj tytuł ksiazki ktorej szukasz: ");
        scanner.nextLine();
        var value = scanner.nextLine();
        return value;
    }

    @Override
    public String getValueTextFromUser(String comment) {
        System.out.println(comment);
        scanner.nextLine();
        var value = scanner.nextLine();
        return value;
    }

}
