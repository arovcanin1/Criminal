package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Domain JavaBean class Criminal that contains all information about criminal such as id, firsName, lastName, jmbg and date
 * This class contains constructor, getters and setters for all attributes
 */
public class Criminal implements Idable {
    /**
     * Attributes for class Criminal
     */
       private int id;
       private String firstName;
       private String lastName;
       private String jmbg;
       private LocalDate date;

    /**
     * Constructor with all parameters
      * @param id
     * @param firstName
     * @param lastName
     * @param jmbg
     * @param birthDate
     */
    public Criminal(int id, String firstName, String lastName, String jmbg, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
        this.date = birthDate;
    }


    /**
     * Constructor without parameters
     */
    public Criminal() {}

    /**
     * Getter for attribute id
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Setter for attribute id
     * @param id
     */
    @Override
    public void setId(int id) {
        this.id = id;
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
     * Getter for attribute jmbg
     * @return
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Setter for attribute jmbg
     * @param jmbg
     */
    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    /**
     * Getter for attribute date
     * @return
     */
    public LocalDate getBirthDate() {
        return date;
    }

    /**
     * Setter for attribute date
     * @param birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.date = birthDate;
    }

    /**
     * Method for comparing attributes by id, firsName, lastName, jmbg, and date
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        Criminal criminal = (Criminal) obj;
        if (obj == null || getClass() != obj.getClass()) return false;
        return id == criminal.id && Objects.equals(firstName, criminal.firstName) &&
                Objects.equals(lastName, criminal.lastName) &&
                Objects.equals(jmbg, criminal.jmbg) &&
                Objects.equals(date, criminal.date);
    }

    /**
     * Method for hashing attributes of Criminal class
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, jmbg, date);
    }

    /**
     * Method for printing values of Criminal class attributes
     * @return
     */
    @Override
    public String toString() {
        return "First name: " + this.firstName + "\nLast name: " + this.lastName + "\nJMBG: " + this.jmbg + "\nBirth date: " + this.date;
    }

}
