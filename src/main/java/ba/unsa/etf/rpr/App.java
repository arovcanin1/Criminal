package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CriminalManager;
import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * This is terminal user interface
 */
public class App {
    /**
     * Main method
     * @param args
     * @throws CriminalRecordsException
     */
    public static void main(String[] args) throws CriminalRecordsException {

        System.out.println("Welcome to CR!");

        System.out.println("For login type L, For registration type R");
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equals("L")) {
            String username;
            String password;
            System.out.println("Enter username: ");
            Scanner newScaner = new Scanner(System.in);
            username = newScaner.next();
            System.out.println("Enter password: ");
            password = scanner.next();
            Employee employee = DaoFactory.employeesDao().getByUsername(username);

            for (;;) {
                // If login is successful
                if (employee != null && employee.getPassword().equals(password)) {
                    System.out.println("Success!");
                    break;
                }

                if (employee == null) {
                    System.out.println("Enter username again: ");
                    String repeatUsername;
                    Scanner repeatScaner = new Scanner(System.in);
                    repeatUsername = repeatScaner.next();
                }

                if (!employee.getPassword().equals(password)) {
                    System.out.println("Enter password again: ");
                    String repeatUsername;
                    Scanner repeatScaner = new Scanner(System.in);
                    repeatUsername = repeatScaner.next();
                    if (password.equals(employee.getPassword())) break;
                }
            }

            showEmployee(employee.getId());
        }

        if (scanner.next().equals("R")) {
            String firstName;
            String lastName;
            String username;
            String email;
            String password;
            String confirmPassword;
            System.out.println("Enter name: ");
            Scanner enterName = new Scanner(System.in);
            firstName = enterName.next();
            System.out.println("Enter surname: ");
            Scanner enterSurname = new Scanner(System.in);
            lastName = enterSurname.next();
            Scanner enterUsername = new Scanner(System.in);
            username = enterUsername.next();
            Scanner enterEmail = new Scanner(System.in);
            email = enterEmail.next();
            Scanner enterPassword = new Scanner(System.in);
            password = enterPassword.next();
            Scanner enterPasswordAgain = new Scanner(System.in);
            confirmPassword = enterPasswordAgain.next();

            Employee e = new Employee();
            e.setFirstName(firstName);
            e.setLastName(lastName);
            e.setUsername(username);
            e.setPassword(password);
            EmployeeManager.add(e, confirmPassword);
            showEmployee(e.getId());
        }
    }

    /**
     * Method that shows options for Employee
     * @param id
     * @throws CriminalRecordsException
     */
    public static void showEmployee(int id) throws CriminalRecordsException  {
        System.out.println("You have following options: ");
        System.out.println("Option 1: Show details about criminal");
        System.out.println("Option 2: Add new Criminal");
        System.out.println("Option 3: Add new Record");
        System.out.println("Option 4: Delete Record");
        System.out.println("Option 5: Logout");

        Scanner scanner = new Scanner(System.in);
        int option;

        for(;;) {
            System.out.println("Choose option: ");
            option = scanner.nextInt();
            // Check if options are in correct range
            if (option > 1 || option < 6) break;
            else System.out.println("Incorrect option! Enter option again: ");
        }

        if (option == 1) {
            System.out.println("List of all criminals");
            showAllCriminals(id);
            showDetailsForCriminal(id);
        }

        if (option == 1) {
            showDetailsForCriminal(id);
        }

        if (option == 2) {
            showAddCriminal(id);
        }

        if (option == 3) {
            showAddRecord(id);
        }
    }

    /**
     * Method that shows list of all existing criminals
     * @param id
     * @throws CriminalRecordsException
     */
    private static void showAllCriminals(int id) throws CriminalRecordsException {
        List<Criminal> listOfCriminals = DaoFactory.criminalsDao().allCriminals();
        if (listOfCriminals.isEmpty()) {
            System.out.println("No criminals :)");
            showEmployee(id);
            return;
        }
        System.out.println("All criminals");
        for (int i = 0; i < listOfCriminals.size(); i++) {
            System.out.println(i + " " + listOfCriminals.get(i).getJmbg());
        }
    }

    /**
     * Method that shows all details about chosen criminal such as firstName, lastName, jmbg, birth date, records
     * @param id
     * @throws CriminalRecordsException
     */
    public static void showDetailsForCriminal(int id) throws CriminalRecordsException {
        System.out.println("If you want to see details about criminal insert number!");
        Scanner choseNumber = new Scanner(System.in);
        int number = choseNumber.nextInt();
        System.out.println(DaoFactory.criminalsDao().allCriminals().get(number).toString());
        System.out.println("Criminal records");

        List<CriminalRecord> criminalRecords = DaoFactory.criminalRecordsDao().getByIdNew(DaoFactory.criminalsDao().allCriminals().get(number).getId());

        for (int i = 0; i < criminalRecords.size(); i++) {
            System.out.println("Code: " + criminalRecords.get(i).getCode());
        }

        System.out.println("Do you want to see details about specific criminal record? Y(YES)/N(NO)?");
        String confirm;
        Scanner confirmScanner = new Scanner(System.in);
        confirm = confirmScanner.next();

        if (confirm.equals("YES")) {
            String code;
            Scanner insertCode = new Scanner(System.in);
            System.out.println("Please insert code for specific criminal record!");
            code = insertCode.next();
            System.out.println(DaoFactory.criminalRecordsDao().getByCode(code).toString());
            showEmployee(id);
        }

        if (confirm.equals("NO")) showEmployee(id);
    }

    /**
     * Method for adding new criminal
     * @param id
     * @throws CriminalRecordsException
     */
    public static void showAddCriminal(int id) throws CriminalRecordsException {
            String firstName;
            String lastName;
            String jmbg;
            LocalDate date;

        System.out.println("Enter first name: ");
        Scanner firstNameScanner = new Scanner(System.in);
        firstName = firstNameScanner.next();

        System.out.println("Enter last name: ");
        Scanner lastNameScanner = new Scanner(System.in);
        lastName = lastNameScanner.next();

        System.out.println("Enter JMBG: ");
        Scanner jmbgScanner = new Scanner(System.in);
        jmbg = jmbgScanner.next();

        date = birthDate();

        Criminal criminal = new Criminal();
        criminal.setFirstName(firstName);
        criminal.setLastName(lastName);
        criminal.setJmbg(jmbg);
        criminal.setBirthDate(date);

        CriminalManager.add(criminal);
        System.out.println("Criminal successfully added!");
        showEmployee(id);
    }

    /**
     * Method for entering date of birth, when Emplyoee enters data for new criminal
     * @return
     */
    public static LocalDate birthDate() {
        Scanner dateScanner = new Scanner(System.in);
        System.out.println("Enter birth date [dd/MM/yyyy]: ");
        String help = dateScanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(help, formatter);
    }

    public static void showAddRecord(int id) throws CriminalRecordsException {
        showAllCriminals(id);
        System.out.println("Please insert number for criminal you want add record");
        Scanner addScanner = new Scanner(System.in);
        int add = addScanner.nextInt();
    }

}
