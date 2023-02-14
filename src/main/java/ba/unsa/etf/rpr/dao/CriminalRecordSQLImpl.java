package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.ResultSet;
import java.util.Map;

public class CriminalRecordSQLImpl extends AbstractDao<CriminalRecord> implements CriminalRecordDao {
    public CriminalRecordSQLImpl() {
        super("CriminalRecord");
    }

    public CriminalRecord getById(int id) throws CriminalRecordsException {
        return null;
    }

    public CriminalRecord row2object(ResultSet rs) throws CriminalRecordsException {
        return null;
    }

    public Map<String, Object> object2row(CriminalRecord object) {
        return null;
    }
}
