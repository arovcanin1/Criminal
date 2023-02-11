package ba.unsa.etf.rpr.dao;

public class DaoFactory {

    private static final EmployeeDao usersDao = new EmployeeDaoSQLImpl();

    private DaoFactory() {}

    public static EmployeeDao usersDao() {
        return usersDao;
    }
}
