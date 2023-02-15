package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.util.List;

/**
 * Dao interface extend by EmployeeDao, CriminalDao and CriminalRecordDao
 * @param <T>
 */
public interface Dao<T> {

    /**
     * Method for getting entity from database based on id
     * @param id
     * @return
     * @throws CriminalRecordsException
     */
    T getById(int id) throws CriminalRecordsException;

    /**
     * Method for saving entity into database
     * @param item - saving to db
     * @return saved item
     * @throws CriminalRecordsException
     */
    T add(T item) throws CriminalRecordsException;

    /**
     * Method for getting all entities from database.
     * @return list of entities
     * @throws CriminalRecordsException
     */
    List<T> getAll() throws CriminalRecordsException;

    /**
     * Method for delete from database based on id
     * @param id - primary key
     * @throws CriminalRecordsException
     */
    void delete(int id) throws CriminalRecordsException;
}
