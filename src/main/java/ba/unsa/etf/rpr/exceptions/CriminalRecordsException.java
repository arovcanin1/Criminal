package ba.unsa.etf.rpr.exceptions;

public class CriminalRecordsException extends Exception {
    public CriminalRecordsException(String message, Exception reason) {
        super(message, reason);
    }

    public CriminalRecordsException(String message) {
        super(message);
    }
}
