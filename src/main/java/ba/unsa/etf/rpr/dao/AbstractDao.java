package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.*;
import java.util.*;

/**
 * Class AbstractDao that contains methods that are needed for every class
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    /**
     * Attributes for connection to db
     * Singleton patern
     */
    private static Connection connection = null;
    private String tableName;

    /**
     * Constructor with one parameter
     * @param tableName
     */
    public AbstractDao(String tableName) {
        this.tableName = tableName;
        createConnection();
    }

    /**
     * Method for creating connection to database
     */
    private static void createConnection() {
        if (AbstractDao.connection == null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties").openStream());
                String url = p.getProperty("db.url");
                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");
                AbstractDao.connection = DriverManager.getConnection(url, username, password);
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {
                        try {
                            connection.close();
                        } catch(SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    /**
     * Constructor for connection
     * @return
     */
    public static Connection getConnection() {
        return AbstractDao.connection;
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs
     * @return a Bean object for specific table
     * @throws CriminalRecordsException if there is no requested data
     * @throws SQLException in case if there is error with db
     */
    public abstract T row2object(ResultSet rs) throws CriminalRecordsException, SQLException;

    /**
     * Method for mapping Object into Map
     * @param Object object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row (T Object);

    /**
     * Accepts KV storage of column names and return CSV of columnes and question marks for insert statement
     * @param row
     * @return
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;   // skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }

        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    /**
     * Method for preparing columns for update statement id=?, ...
     * @param row
     * @return
     */
    private String prepareUpdateParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("=?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }

        return columns.toString();
    }

    /**
     * Method for adding data into database
     * @param item - bean for saving to database
     * @return
     * @throws CriminalRecordsException
     */
    public T add(T item) throws CriminalRecordsException {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(") ");
        try {
            int counter = 1;
            PreparedStatement statement = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("id")) continue;
                statement.setObject(counter, entry.getValue());
                counter++;
            }
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }

    /**
     * Method for getting all data from specific table
     * @return
     * @throws CriminalRecordsException
     */
    public List<T> getAll() throws CriminalRecordsException {
        String query = "SELECT * FROM " + tableName;
        List<T> results = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                T object = row2object(rs);
                results.add(object);
            }

            rs.close();
            return results;
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }

    /**
     * Method for deleting data from database based on id
     * @param id - primary key
     * @throws CriminalRecordsException
     */
    public void delete (int id) throws CriminalRecordsException {
        String query = "DELETE FROM " + tableName + " WHERE id = ? ";

        try {
            PreparedStatement statement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }

    /**
     * Method that updates data
     * @param item - bean to be updated, id must be populated
     * @return
     * @throws CriminalRecordsException
     */
    public T update(T item) throws CriminalRecordsException {
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try {
            PreparedStatement statement = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                statement.setObject(counter, entry.getValue());
                counter++;
            }

            statement.setObject(counter, item.getId());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }
}
