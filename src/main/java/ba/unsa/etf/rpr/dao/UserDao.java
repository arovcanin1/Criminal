package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.CriminalRecords;

public interface UserDao extends Dao<User> {

    User getByUsername(String username) throws CriminalRecords;
}
