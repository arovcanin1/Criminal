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

    public UserDaoSQLImpl() {
        super("User");
    }

    public User row2object(ResultSet rs) throws CriminalRecordsException, SQLException {
        User user = new User();

        try {
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage());
        }

    }

    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("firstName", object.getFirstName());
        item.put("lastName", object.getLastName());
        item.put("email", object.getEmail());
        item.put("username", object.getUsername());
        item.put("password", object.getPassword());
        return item;
    }
    @Override
    public User getById(int id) throws CriminalRecordsException {
        return null;
    }

    @Override
    public User getByUsername(String username) throws CriminalRecordsException {
        String query = "SELECT * FROM User WHERE username=?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new CriminalRecordsException("There is no User!");
            }
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }


}
