package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

public interface Dao<T> {

    T getById(int id) throws CriminalRecordsException;


}
