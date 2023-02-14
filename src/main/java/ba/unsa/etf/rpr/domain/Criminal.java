package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Criminal implements Idable {
       private int id;
       private String firstName;
       private String lastName;
       private String jmbg;
       private LocalDate date;
       private Gender gender;

    public Criminal(int id, String firstName, String lastName, String jmbg, LocalDate birthDate, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
        this.date = birthDate;
        this.gender = gender;
    }

    public Criminal() {}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public LocalDate getBirthDate() {
        return date;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.date = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        Criminal criminal = (Criminal) obj;
        if (obj == null || getClass() != obj.getClass()) return false;
        return id == criminal.id && Objects.equals(firstName, criminal.firstName) &&
                Objects.equals(lastName, criminal.lastName) &&
                Objects.equals(jmbg, criminal.jmbg) &&
                Objects.equals(date, criminal.date) && Objects.equals(gender, criminal.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, jmbg, date, gender);
    }

}
