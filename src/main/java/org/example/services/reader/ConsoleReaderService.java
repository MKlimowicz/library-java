package org.example.services.reader;

public interface ConsoleReaderService {

    int getIdFromUser();
    int getIdFromUser(String comment);

    String getTitleFromUser();
    String getValueTextFromUser(String comment);

}
