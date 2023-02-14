package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CriminalRecordSQLImpl extends AbstractDao<CriminalRecord> implements CriminalRecordDao {
    public CriminalRecordSQLImpl() {
        super("CriminalRecord");
    }

    public CriminalRecord getById(int id) throws CriminalRecordsException {
        String query = "SELECT * FROM CriminalRecord WHERE id = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                CriminalRecord result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw  new CriminalRecordsException("Criminal record does not exist!");
            }
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }

    public CriminalRecord row2object(ResultSet rs) throws CriminalRecordsException {
        return null;
    }

    public Map<String, Object> object2row(CriminalRecord object) {
        return null;
    }
}
