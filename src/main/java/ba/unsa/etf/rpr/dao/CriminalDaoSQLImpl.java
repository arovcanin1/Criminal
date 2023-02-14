package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Gender;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class CriminalDaoSQLImpl extends AbstractDao<Criminal> implements CriminalDao {

    public CriminalDaoSQLImpl() { super("Criminal"); }

    @Override
    public Criminal getById(int id) throws CriminalRecordsException {
        String query = "SELECT * FROM Criminal WHERE id = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Criminal result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new CriminalRecordsException("Criminal does not exist!");
            }
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }

    public Criminal row2object(ResultSet rs) throws CriminalRecordsException {
        try {
            Criminal criminal = new Criminal();
            criminal.setId(rs.getInt("id"));
            criminal.setFirstName(rs.getString("firstName"));
            criminal.setLastName(rs.getString("lastName"));
            criminal.setJmbg(rs.getString("jmbg"));
            criminal.setBirthDate(rs.getDate("date").toLocalDate());
            criminal.setGender(Gender.valueOf(rs.getString("gender")));
            return criminal;
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage());
        }
    }

    public Map<String, Object> object2row (Criminal object) {
        Map<String, Object> item = new TreeMap<>();

        item.put("id", object.getId());
        item.put("firstName", object.getFirstName());
        item.put("lastName", object.getLastName());
        item.put("jmbg", object.getJmbg());
        item.put("date", object.getBirthDate());
        item.put("gender", object.getGender());
        return item;
    }
}
