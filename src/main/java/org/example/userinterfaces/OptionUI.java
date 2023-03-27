package org.example.userinterfaces;

import org.example.constants.OptionConstants;
import org.example.services.printer.ConsolePrinterService;

import java.util.List;
import java.util.Optional;

public class OptionUI {

    private ConsolePrinterService printer;

    public OptionUI(ConsolePrinterService consolePrinterService) {
        this.printer = consolePrinterService;
    }

    private List<Option> options = List.of(
            new Option(0, OptionConstants.EXIT),
            new Option(1, OptionConstants.ADD_BOOK),
            new Option(2, OptionConstants.ADD_MAGAZINE),
            new Option(3, OptionConstants.SHOW_BOOKS),
            new Option(4, OptionConstants.SHOW_MAGAZINE),
            new Option(5, OptionConstants.DELETE_BOOK),
            new Option(6, OptionConstants.DELETE_MAGAZINE),
            new Option(7, OptionConstants.UPDATE_BOOK),
            new Option(8, OptionConstants.UPDATE_MAGAZINE),
            new Option(9, OptionConstants.FIND_PUBLICATION),
            new Option(10, OptionConstants.ALL_PUBLICATIONS)
    );


    public void printOptions() {
        options.forEach(option -> printer.printLine(option.toString()));
    }


    public Optional<Option> findOptionById(int id) {
        return options.stream().filter(option -> option.getId() == id).findFirst();
    }


    public class Option {
        private int id;
        private String description;
        public Option(int id, String description) {
            this.id = id;
            this.description = description;
        }
        @Override
        public String toString() {
            return String.format("numer: %s czynność: %s", id, description);
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }
}