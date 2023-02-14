package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;

public class DaoFactory {

    private static final EmployeeDao employeesDao = new EmployeeDaoSQLImpl();
    private static  final CriminalDao criminalsDao = new CriminalDaoSQLImpl();

    private static final CriminalRecordDao criminalRecordsDao = new CriminalRecordSQLImpl();

    private DaoFactory() {}

    public static EmployeeDao employeesDao() {
        return employeesDao;
    }
    public static CriminalDao criminalsDao() { return criminalsDao; }

    public static CriminalRecordDao criminalRecordsDao() {return criminalRecordsDao; }
}
