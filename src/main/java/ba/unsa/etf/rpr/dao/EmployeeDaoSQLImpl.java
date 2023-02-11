package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao {

    public EmployeeDaoSQLImpl() {
        super("Employee");
    }

    @Override
    public Employee getById(int id) throws CriminalRecordsException {
        return null;
    }

    @Override
    public Employee getByUsername(String username) throws CriminalRecordsException {
        String query = "SELECT * FROM Employee WHERE username=?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Employee result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new CriminalRecordsException("Employee does not exist!");
            }
        } catch (SQLException e) {
            throw new CriminalRecordsException(e.getMessage(), e);
        }
    }

    public Employee row2object(ResultSet rs) throws CriminalRecordsException, SQLException {

        try {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFirstName(rs.getString("firstName"));
            employee.setLastName(rs.getString("lastName"));
            employee.setEmail(rs.getString("email"));
            employee.setUsername(rs.getString("username"));
            employee.setPassword(rs.getString("password"));
            return employee;
        } catch(SQLException e) {
            throw new CriminalRecordsException(e.getMessage());
        }
    }

    public Map<String, Object> object2row (Employee object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("firstName", object.getFirstName());
        item.put("lastName", object.getLastName());
        item.put("email", object.getEmail());
        item.put("username", object.getUsername());
        item.put("password", object.getPassword());
        return item;
    }


}
