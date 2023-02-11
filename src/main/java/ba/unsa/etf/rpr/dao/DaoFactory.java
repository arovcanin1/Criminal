package ba.unsa.etf.rpr.dao;

public class DaoFactory {

    private static final EmployeeDao employeesDao = new EmployeeDaoSQLImpl();

    private DaoFactory() {}

    public static EmployeeDao employeesDao() {
        return employeesDao;
    }
}
