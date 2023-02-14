package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.util.List;

public interface CriminalDao extends Dao<Criminal> {

      public List<Criminal> allCriminals() throws CriminalRecordsException;
}
