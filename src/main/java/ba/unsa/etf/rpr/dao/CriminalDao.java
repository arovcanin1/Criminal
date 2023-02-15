package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.util.List;

/**
 * Interface that extends Dao<T> and also has methods for CriminalDao
 */
public interface CriminalDao extends Dao<Criminal> {

      /**
       * Method that find all criminals
       * @return list of all criminals
       * @throws CriminalRecordsException
       */
      public List<Criminal> allCriminals() throws CriminalRecordsException;

      /**
       * Method that find all criminals based on jmbg
       * @param jmbg
       * @return
       * @throws CriminalRecordsException
       */
      public Criminal getByJMBG(String jmbg) throws CriminalRecordsException;
}
