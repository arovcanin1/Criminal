package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.Gender;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class CriminalDaoSQLImpl extends AbstractDao<Criminal> {

    public CriminalDaoSQLImpl() { super("Criminal"); }

    public Criminal getById(int id) throws CriminalRecordsException {
        return null;
    }

    public Criminal row2object(ResultSet rs) throws CriminalRecordsException {
        try {
            Criminal criminal = new Criminal();
            criminal.setId(rs.getInt("id"));
            criminal.setFirstName(rs.getString("firstName"));
            criminal.setLastName(rs.getString("lastName"));
            criminal.setJmbg(rs.getString("jmbg"));
            criminal.setBirthDate(rs.getDate("date"));
            criminal.setGender(Gender.valueOf(rs.getString("gender")));
            return criminal;
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage());
        }
    }

    public Map<String, Object> object2row (Criminal object) {
        return null;
    }
}
