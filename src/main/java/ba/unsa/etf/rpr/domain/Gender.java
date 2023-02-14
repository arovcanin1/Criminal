package ba.unsa.etf.rpr.domain;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE");

    public String string;

    Gender(String string) {
        this.string = string;
    }

    public String toString() { return string; }
}
