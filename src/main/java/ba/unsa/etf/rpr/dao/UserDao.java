package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

public interface UserDao extends Dao<User> {

    User getByUsername(String username) throws CriminalRecordsException;
}
