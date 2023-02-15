package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;

/**
 * Domain JavaBean class CriminalRecord that contains all information about records such as id, description, place, date
 * code and criminal
 */
public class CriminalRecord implements Idable {
    /**
     * Attributes for class CriminalRecords
     */
    private int id;
    private String description;
    private String place;
    private LocalDate date;
    private String code;
    private Criminal criminal;

    /**
     * Constructor with all parameters
     * @param id
     * @param description
     * @param place
     * @param date
     * @param code
     * @param criminal
     */
    public CriminalRecord(int id, String description, String place, LocalDate date, String code, Criminal criminal) {
        this.id = id;
        this.description = description;
        this.place = place;
        this.date = date;
        this.code = code;
        this.criminal = criminal;
    }

    /**
     * Constructor without parameters
     */
    public CriminalRecord() {}

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
     * Getter for attribute description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for attribute description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for attribute place
     * @return
     */
    public String getPlace() {
        return place;
    }

    /**
     * Setter for attribute place
     * @param place
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Getter for attribute date
     * @return
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter for attribute date
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Getter for attribute code
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for attribute code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for attribute criminal
     * @return
     */
    public Criminal getCriminal() {
        return criminal;
    }

    /**
     * Setter for attribute criminal
     * @param criminal
     */
    public void setCriminal(Criminal criminal) {
        this.criminal = criminal;
    }
}
