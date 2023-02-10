package ba.unsa.etf.rpr.exceptions;

import ba.unsa.etf.rpr.domain.CriminalRecord;

public class CriminalRecords extends Exception {
    public CriminalRecords(String message, Exception reason) {
        super(message, reason);
    }

    public CriminalRecords(String message) {
        super(message);
    }
}
