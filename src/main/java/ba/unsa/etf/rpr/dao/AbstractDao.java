package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



public abstract class AbstractDao<T extends Idable> implements Dao<T> {

   private Connection connection = null;
   private String table;

   public AbstractDao(String table) {
      this.table = table;
      if (connection == null) {
         try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.url");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            this.connection = DriverManager.getConnection(url, username, password);
         }
         catch(Exception e) {
            System.out.println("Connection to database failed!");
            e.printStackTrace();
         }
      }
   }

   public Connection getConnection() {
      return this.connection;
   }

   public T getById(int id) throws CriminalRecordsException {
      return null;
   }




}
