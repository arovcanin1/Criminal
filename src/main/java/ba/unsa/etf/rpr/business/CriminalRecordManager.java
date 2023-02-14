package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

public class CriminalRecordManager {

    public static Criminal add(CriminalRecord record) throws CriminalRecordsException {
        try {
            return DaoFactory.criminalRecordsDao().add(record).getCriminal();
        } catch (CriminalRecordsException e) {
            throw e;
        }
    }
}
