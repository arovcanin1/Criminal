package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.util.List;

public interface Dao<T> {

    T getById(int id) throws CriminalRecordsException;

    T add(T item) throws CriminalRecordsException;
    List<T> getAll() throws CriminalRecordsException;


}
