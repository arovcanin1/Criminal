package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implements all methods from Dao<T> and also methods from CriminalRecordDao, extends AbstractDao
 */
public class CriminalRecordSQLImpl extends AbstractDao<CriminalRecord> implements CriminalRecordDao {
    /**
     * Constructor for specific table
     */
    public CriminalRecordSQLImpl() {
        super("CriminalRecord");
    }

    /**
     * Method for getting criminal record by id
     * @param id - record id
     * @return
     * @throws CriminalRecordsException
     */
    public CriminalRecord getById(int id) throws CriminalRecordsException {
        return null;
    }

    /**
     * Method for getting list of criminal records based on id
     * @param id
     * @return
     * @throws CriminalRecordsException
     */
    public List<CriminalRecord> getByIdNew(int id) throws CriminalRecordsException {
        String query = "SELECT * FROM CriminalRecord WHERE criminalId = ?";

        List<CriminalRecord> allCriminalRecords = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CriminalRecord result = row2object(rs);
                allCriminalRecords.add(result);
            }
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
        return allCriminalRecords;
    }

    /**
     * Method for getting criminal record based on code
     * @param code
     * @return
     * @throws CriminalRecordsException
     */
    public CriminalRecord getByCode(String code) throws CriminalRecordsException{
        String query = "SELECT * FROM CriminalRecord WHERE code = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                CriminalRecord result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new CriminalRecordsException("Criminal record not found!");
            }
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }


    }
    /**
     * Method for mapping ResultSet into Object
     * @param rs
     * @return a Bean object for specific table
     * @throws CriminalRecordsException if there is no requested data
     * @throws SQLException in case if there is error with db
     */
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


    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
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

    /**
     * Method for getting all criminal Records based on criminalId
     * @param criminalId
     * @return
     * @throws CriminalRecordsException
     */
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
