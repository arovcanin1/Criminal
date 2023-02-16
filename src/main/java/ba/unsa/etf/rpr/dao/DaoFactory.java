package ba.unsa.etf.rpr.dao;


/**
 * DaoFactory class that make instances of dao classes
 */
public class DaoFactory {

    /**
     * Static methods for DaoFactory
     */
    private static final EmployeeDao employeesDao = new EmployeeDaoSQLImpl();
    private static  final CriminalDao criminalsDao = new CriminalDaoSQLImpl();
    private static final CriminalRecordDao criminalRecordsDao = new CriminalRecordSQLImpl();

    /**
     * Constructor without parameters
     */
    private DaoFactory() {}

    /**
     * Method for access EmployeeDao instance
     * @return
     */
    public static EmployeeDao employeesDao() {
        return employeesDao;
    }

    /**
     * Method for access CriminalDao instance
     * @return
     */
    public static CriminalDao criminalsDao() { return criminalsDao; }

    /**
     * Method for access CriminalRecordDao instance
     * @return
     */
    public static CriminalRecordDao criminalRecordsDao() {return criminalRecordsDao; }
}
