package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.util.List;

/**
 * Interface that extends Dao<T> and also has methods only for CriminalRecordDao
 */
public interface CriminalRecordDao extends Dao<CriminalRecord> {

    /**
     * Method for getting all records based on criminal id
     * @param id
     * @return list of all criminal records with specified id
     * @throws CriminalRecordsException
     */
    public List<CriminalRecord> getByIdNew(int id) throws CriminalRecordsException;

    /**
     * Method for getting all records based on code
     * @param code
     * @return criminal record with specified code
     * @throws CriminalRecordsException
     */
    public CriminalRecord getByCode(String code) throws CriminalRecordsException;
}
