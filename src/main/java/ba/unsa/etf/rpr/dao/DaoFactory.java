package ba.unsa.etf.rpr.dao;

public class DaoFactory {

    private static final UserDao usersDao = new UserDaoSQLImpl();

    private DaoFactory() {}

    public static UserDao usersDao() {
        return usersDao;
    }
}
