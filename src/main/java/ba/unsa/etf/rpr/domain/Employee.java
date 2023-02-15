package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Domain JavaBean class for Employee with all attributes such as id, firstName, lastName, email, username and password
 * In this class there are also constructor, getters and setters for each attribute
 */
public class Employee implements Idable {

    /**
     * Atributes
     */
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    /**
     * Constructor with all parameters
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param username
     * @param password
     */
    public Employee(int id, String firstName, String lastName, String email, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor without parameters
     */
    public Employee() {}

    /**
     * Getter for Id
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for attribute firstName
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for attribute firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for attribute lastName
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for attribute lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for attribute email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for attribute email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method for comparing Employees by id, firstName, lastName, username and email
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        Employee employee = (Employee) obj;
        if (obj == null || getClass() != obj.getClass()) return false;
        return id == employee.id && Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(username, employee.username) &&
                Objects.equals(email, employee.email);
    }

    /**
     * Method for hashing attributes of employee class
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, email);
   }

}
