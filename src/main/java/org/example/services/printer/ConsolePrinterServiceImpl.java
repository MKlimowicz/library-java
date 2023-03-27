package org.example.services.printer;

import org.example.domain.Publication;

import java.util.List;

public class ConsolePrinterServiceImpl implements ConsolePrinterService {
    @Override
    public void printLine(String line) {
        System.out.println(line);
    }

    @Override
    public <TPublication extends Publication> void printPublications(List<TPublication> tPublications) {
        System.out.println();
        System.out.println("### LISTA PUBLIKACJI ###");
        tPublications.forEach(tPublication -> printLine(tPublication.toString()));
        System.out.println("### KONIEC ###");
        System.out.println();
    }


}
