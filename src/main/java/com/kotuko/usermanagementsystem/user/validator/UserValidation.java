package com.kotuko.usermanagementsystem.user.validator;


import com.kotuko.usermanagementsystem.config.KotukoException;
import com.kotuko.usermanagementsystem.user.dal.IUserDAL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class UserValidation {
    IUserDAL iUserDAL;
    private static final Pattern EMAIL = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    public UserValidation(IUserDAL iUserDAL) {
        this.iUserDAL = iUserDAL;
    }

    public void dateOfBirthValidation(String dateOfBirth) throws KotukoException {
        if (dateOfBirth==null || dateOfBirth.isEmpty()) {
            throw new KotukoException(HttpStatus.BAD_REQUEST, "Date of Birth cannot be empty.");
        }
        LocalDate localDate = LocalDate.parse(dateOfBirth);
        if (localDate.isAfter(LocalDate.now()))
            throw new KotukoException(HttpStatus.BAD_REQUEST, "Date of Birth should not be in future.");
    }

    public void emailValidation(String email) throws KotukoException {
        if (email == null)
            throw new KotukoException(HttpStatus.BAD_REQUEST, "Email cannot be null.");

        if (iUserDAL.findByEmailIgnoreCase(email) != null)
            throw new KotukoException(HttpStatus.BAD_REQUEST, "Email already exists.");
        if (!EMAIL.matcher(email).matches())
            throw new KotukoException(HttpStatus.BAD_REQUEST, "Invalid email format.");
    }

    public void usernameValidation(String username) throws KotukoException {
        if (username == null)
            throw new KotukoException(HttpStatus.BAD_REQUEST, "Username cannot be null.");

        if (iUserDAL.findByUsernameIgnoreCase(username) != null)
            throw new KotukoException(HttpStatus.BAD_REQUEST, "Username already exists.");
    }
}
