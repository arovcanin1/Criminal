package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Class that implements all methods from Dao<T> and also methods from CriminalDao
 * Extends AbstractDao
 */
public class CriminalDaoSQLImpl extends AbstractDao<Criminal> implements CriminalDao {

    /**
     * Constructor for table, calls parent class
     */
    public CriminalDaoSQLImpl() {
        super("Criminal");
    }

    /**
     * Method that gets criminal based on id
     * @param id - primary key
     * @return
     * @throws CriminalRecordsException
     */
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

    /**
     * Method for getting criminal based on jmbg
     * @param jmbg
     * @return
     * @throws CriminalRecordsException
     */
    public Criminal getByJMBG(String jmbg) throws CriminalRecordsException {
        String query = "SELECT * FROM Criminal WHERE jmbg = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, jmbg);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Criminal result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new CriminalRecordsException("Criminal not found!");
            }
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs
     * @return
     * @throws CriminalRecordsException if there is no requested data
     * @throws SQLException in case if there is error with db
     */
    @Override
    public Criminal row2object(ResultSet rs) throws CriminalRecordsException {
        try {
            Criminal criminal = new Criminal();
            criminal.setId(rs.getInt("id"));
            criminal.setFirstName(rs.getString("firstName"));
            criminal.setLastName(rs.getString("lastName"));
            criminal.setJmbg(rs.getString("jmbg"));
            criminal.setBirthDate(rs.getDate("date").toLocalDate());
            //criminal.setGender(rs.getString("gender"));
            return criminal;
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage());
        }
    }

    /**
     * Method for mapping Object into Map
     * @param object
     * @return
     */
    @Override
    public Map<String, Object> object2row (Criminal object) {
        Map<String, Object> item = new TreeMap<>();

        item.put("id", object.getId());
        item.put("firstName", object.getFirstName());
        item.put("lastName", object.getLastName());
        item.put("jmbg", object.getJmbg());
        item.put("date", object.getBirthDate());
        //item.put("gender", object.getGender());
        return item;
    }

    /**
     * Method for getting all criminals
     * @return
     * @throws CriminalRecordsException
     */
    public List<Criminal> allCriminals() throws CriminalRecordsException {
        String query = "SELECT * FROM Criminal";
        List<Criminal> allCriminalsList = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Criminal result = row2object(rs);
                allCriminalsList.add(result);
            }


        } catch (Exception e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
        return allCriminalsList;
    }
}
