package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;

public class DaoFactory {

    private static final EmployeeDao employeesDao = new EmployeeDaoSQLImpl();
    private static  final CriminalDao criminalsDao = new CriminalDaoSQLImpl();

    private DaoFactory() {}

    public static EmployeeDao employeesDao() {
        return employeesDao;
    }
    public static CriminalDao criminalsDao() { return criminalsDao; }
}
