package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

/**
 * Interface that extends Dao<T> and also has methods only for EmployeeDao
 */
public interface EmployeeDao extends Dao<Employee> {

    /**
     * Method for getting Employee by username
     * @param username
     * @return
     * @throws CriminalRecordsException
     */
    Employee getByUsername(String username) throws CriminalRecordsException;
}
