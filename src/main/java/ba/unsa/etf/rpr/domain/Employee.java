package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Employee implements Idable {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public Employee(int id, String firstName, String lastName, String email, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Employee() {}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

   @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, email);
   }

}
