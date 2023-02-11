package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

public interface EmployeeDao extends Dao<Employee> {

    Employee getByUsername(String username) throws CriminalRecordsException;
}
