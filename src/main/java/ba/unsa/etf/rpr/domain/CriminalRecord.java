package ba.unsa.etf.rpr.domain;

import java.util.Date;

public class CriminalRecord implements Idable {
    private int id;
    private String description;
    private String place;
    private Date date;
    private int code;

    public CriminalRecord(int id, String description, String place, Date date, int code) {
        this.id = id;
        this.description = description;
        this.place = place;
        this.date = date;
        this.code = code;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
