package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.*;
import java.util.Properties;



public abstract class AbstractDao<T extends Idable> implements Dao<T> {

   private static Connection connection = null;
   private String tableName;

   public AbstractDao(String tableName) {
      this.tableName = tableName;
      createConnection();
   }

   private static void createConnection() {

      if (AbstractDao.connection == null)
         try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.url");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            AbstractDao.connection = DriverManager.getConnection(url, username, password);
         }
         catch(Exception e) {
            System.out.println("Connection to database failed!");
            e.printStackTrace();
         }
      }

   public Connection getConnection() {
      return AbstractDao.connection;
   }

   public T getById(int id) throws CriminalRecordsException {
      String query = "SELECT * FROM" + this.tableName + "WHERE id=?";

      try {
         PreparedStatement statement = AbstractDao.connection.prepareStatement(query);
         statement.setInt(1, id);
         ResultSet rs = statement.executeQuery();
         if (rs.next()) {
            T result = row2object(rs);
            return result;
         } else {
            throw new CriminalRecordsException("Object not found");
         }
      } catch(SQLException e) {
         throw new CriminalRecordsException(e.getMessage(), e);
      }
   }

   public abstract T row2object(ResultSet rs) throws CriminalRecordsException, SQLException;

}
