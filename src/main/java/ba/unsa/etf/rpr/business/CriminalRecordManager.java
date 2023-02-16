package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class that is business layer for CriminalRecord
 * This class contains methods for checking all actions connected with adding new record
 */
public class CriminalRecordManager {

    /**
     * Method that adds new record only if chosen date is correct
     * @param record
     * @return
     * @throws CriminalRecordsException
     */
    public static Criminal add(CriminalRecord record) throws CriminalRecordsException {
        if (record.getDate().isAfter(LocalDate.now())) {
            throw new CriminalRecordsException("Date is not correct");
        }

        try {
            return DaoFactory.criminalRecordsDao().add(record).getCriminal();
        } catch (CriminalRecordsException e) {
            throw e;
        }
    }
}
