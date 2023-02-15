package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CriminalRecordSQLImpl extends AbstractDao<CriminalRecord> implements CriminalRecordDao {
    public CriminalRecordSQLImpl() {
        super("CriminalRecord");
    }

    @Override
    public CriminalRecord getById(int id) throws CriminalRecordsException {
        String query = "SELECT * FROM CriminalRecord WHERE criminalId = ?";

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


    @Override
    public CriminalRecord row2object(ResultSet rs) throws CriminalRecordsException {
        CriminalRecord criminalRecord = new CriminalRecord();
        try {
            criminalRecord.setId(rs.getInt("id"));
            criminalRecord.setDescription(rs.getString("description"));
            criminalRecord.setPlace(rs.getString("place"));
            criminalRecord.setDate(rs.getDate("date").toLocalDate());
            criminalRecord.setCode(rs.getString("code"));
            criminalRecord.setCriminal(DaoFactory.criminalsDao().getById(rs.getInt("criminalId")));
            return criminalRecord;
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> object2row(CriminalRecord object) {
        Map<String, Object> item = new TreeMap<String, Object>();

        item.put("id", object.getId());
        item.put("description", object.getDescription());
        item.put("place", object.getPlace());
        item.put("date", object.getDate());
        item.put("code", object.getCode());
        item.put("criminalId", object.getCriminal().getId());
        return item;
    }

    public List<CriminalRecord> allCriminalRecords (int criminalId) throws CriminalRecordsException {
        List<CriminalRecord> allRecords = new ArrayList<>();
        String query = "SELECT * FROM CriminalRecords WHERE criminalId = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, criminalId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                CriminalRecord result = row2object(rs);
                allRecords.add(result);
            }
        } catch (Exception e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }

        return allRecords;

    }
}
