package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.*;
import java.util.*;


public abstract class AbstractDao<T extends Idable> implements Dao<T> {

    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        createConnection();
    }

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

    public static Connection getConnection() {
        return AbstractDao.connection;
    }

    public abstract T row2object(ResultSet rs) throws CriminalRecordsException, SQLException;

    public abstract Map<String, Object> object2row (T Object);

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


}
