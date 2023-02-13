package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.ResultSet;
import java.util.Map;

public class CriminalDaoSQLImpl extends AbstractDao<Criminal> {

    public CriminalDaoSQLImpl() { super("Criminal"); }

    public Criminal getById(int id) throws CriminalRecordsException {
        return null;
    }

    public Criminal row2object(ResultSet rs) throws CriminalRecordsException {
        return null;
    }

    public Map<String, Object> object2row (Criminal object) {
        return null;
    }
}
