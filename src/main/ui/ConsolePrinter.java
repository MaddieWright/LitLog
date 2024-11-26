package ui;

import model.*;

// ConsolePrinter implementation for LogPrinter
class ConsolePrinter implements LogPrinter {
    @Override
    public void printLog(EventLog log) {
        System.out.println("Application Event Log:");
        for (Event event : log) {
            System.out.println(event.toString());
        }
    }
}
