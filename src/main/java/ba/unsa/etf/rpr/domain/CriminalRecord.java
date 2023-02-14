package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Date;

public class CriminalRecord implements Idable {
    private int id;
    private String description;
    private String place;
    private LocalDate date;
    private String code;
    private Criminal criminal;

    public CriminalRecord(int id, String description, String place, LocalDate date, String code, Criminal criminal) {
        this.id = id;
        this.description = description;
        this.place = place;
        this.date = date;
        this.code = code;
        this.criminal = criminal;
    }

    public CriminalRecord() {}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public Criminal getCriminal() {
        return criminal;
    }

    public void setCriminal(Criminal criminal) {
        this.criminal = criminal;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
