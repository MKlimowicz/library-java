package org.example.services.printer;

import org.example.domain.Publication;

import java.util.List;

public interface ConsolePrinterService {

     void printLine(String line);
      <TPublication extends Publication> void printPublications(List<TPublication> publications);

}
