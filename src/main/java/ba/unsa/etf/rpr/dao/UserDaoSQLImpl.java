package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import ba.unsa.etf.rpr.domain.Idable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {



    @Override
    public User getById(int id) throws CriminalRecordsException {
        return null;
    }

    @Override
    public User getByUsername(String username) throws CriminalRecordsException {
        return null;
    }


}
