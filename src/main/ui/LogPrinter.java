package ui;

import model.*;
import exception.*;

/**
 * Defines behaviours that event log printers must support.
 */
public interface LogPrinter {
	/**
	 * Prints the log
	 * @param el  the event log to be printed
	 * @throws LogException when printing fails for any reason
	 */
    void printLog(EventLog el) throws LogException;
}

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