package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.util.List;
import java.util.Scanner;

public class App {
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

    public static void showEmployee(int id) throws CriminalRecordsException  {
        System.out.println("You have following options: ");
        System.out.println("Option 1: Show all criminals");
        System.out.println("Option 2: Show records for criminal");
        System.out.println("Option 3: Add new Criminal");
        System.out.println("Option 4: Add new Record");
        System.out.println("Option 5: Delete Record");
        System.out.println("Option 6: Logout");

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
            showAllCriminals(id);
        }

        if (option == 2) {
            showRecordsForCriminal(id);
        }

    }

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
        showEmployee(id);
    }

    private static void showRecordsForCriminal(int id) throws CriminalRecordsException {
        List<Criminal> listOfCriminals = DaoFactory.criminalsDao().allCriminals();

        if (listOfCriminals.isEmpty()) {
            System.out.println("Because there is no criminals, there is no records!");
            showEmployee(id);
            return;
        }


        System.out.println("Chose number for which criminal you want to see records");
        Scanner chooseScanner = new Scanner(System.in);
        int number;
        number = chooseScanner.nextInt();

        System.out.println("List of criminal records");
        System.out.println(DaoFactory.criminalRecordsDao().getByIdNew(listOfCriminals.get(number).getId()).get(number).getCode());
        System.out.println("If you want to see details about criminal record insert given code");
        System.out.println("If you want to get back to options insert back");
        Scanner codeScanner = new Scanner(System.in);
        String code;
        code = codeScanner.next();
        if (code.equals("back")) showEmployee(id);
        else showCriminalRecordDetails(code, id);
    }

    public static void showCriminalRecordDetails(String code, int id) throws CriminalRecordsException {
        System.out.println(DaoFactory.criminalRecordsDao().getByCode(code).toString());
        showEmployee(id);
    }
}
