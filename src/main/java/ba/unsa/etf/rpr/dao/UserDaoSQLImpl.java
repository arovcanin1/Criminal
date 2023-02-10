package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {

    public UserDaoSQLImpl() {
        super("users");
    }


    @Override
    public User getById(int id) throws CriminalRecordsException {
        return null;
    }

    @Override
    public User getByUsername(String username) throws CriminalRecordsException {
        return null;
    }

    public User row2object(ResultSet rs) throws CriminalRecordsException, SQLException {
        return null;
    }
}
