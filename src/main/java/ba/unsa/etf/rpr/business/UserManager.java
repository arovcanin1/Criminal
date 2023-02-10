package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

public class UserManager {

    public static User add(User user, String passwordConfirm) throws CriminalRecordsException {
        if (user.getFirstName().equals(null) ||
                user.getLastName().equals(null) ||
                user.getEmail().equals(null) ||
                user.getUsername().equals(null) ||
                user.getPassword().equals(null) ||
                passwordConfirm.equals(null)) {
            throw new CriminalRecordsException("All fields have to be filled!");
        }

        if (user.getPassword().length() < 8 &&
                !user.getPassword().contains("#") &&
                !user.getPassword().contains("%")) {
            throw new CriminalRecordsException("Password has to be longer than 8 characters and must contain # or %!");
        }

        if (!user.getPassword().equals(passwordConfirm)) {
            throw new CriminalRecordsException("Password does not match!");
        }

        try {
            return DaoFactory.usersDao().add(user);
        } catch(CriminalRecordsException e) {
            if (e.getMessage().contains("email")) {
                throw new CriminalRecordsException("Email already in use!");
            }

            throw e;
        }
    }
}
