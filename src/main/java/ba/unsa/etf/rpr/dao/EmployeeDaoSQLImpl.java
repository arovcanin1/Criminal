package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao {

    public EmployeeDaoSQLImpl() {
        super("Employee");
    }

    @Override
    public Employee getById(int id) throws CriminalRecordsException {
        return null;
    }

    @Override
    public Employee getByUsername(String username) throws CriminalRecordsException {
        return null;
    }


}
