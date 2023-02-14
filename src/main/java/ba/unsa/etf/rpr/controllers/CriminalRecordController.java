package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;

public class CriminalRecordController {

    private Criminal criminal;

    public CriminalRecordController() {
        criminal = new Criminal();
    }

    public  CriminalRecordController(Criminal criminal) {
        this.criminal = criminal;
    }
}
