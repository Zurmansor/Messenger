package com.sigmaukraine.messenger.validation;

import com.sigmaukraine.messenger.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "required.login", "Login is required.");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required.confirmPassword", "Confirm password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required.firstName", "First name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required.lastName", "Last name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Email is required.");

        User user = (User) o;
        if (user.getLogin().length() < 4) {
            errors.rejectValue("login", "invalid.login", "Login can not be less then 4 symbols");
        }
        if (user.getLogin().length() > 20) {
            errors.rejectValue("login", "invalid.login", "Login can not be more then 20 symbols");
        }


        if (user.getFirstName().length() > 30) {
            errors.rejectValue("firstName", "invalid.firstName", "FirstName can not be more then 30 symbols");
        }
        if (user.getLastName().length() > 30) {
            errors.rejectValue("lastName", "invalid.lastName", "LastName can not be more then 30 symbols");
        }
        if (user.getEmail().length() > 45) {
            errors.rejectValue("email", "invalid.email", "Email can not be more then 45 symbols");
        }
    }

    public void validatePasswords(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
        User user = (User) o;


        if (user.getPassword().length() < 4) {
            errors.rejectValue("password", "invalid.password", "Password can not be less then 4 symbols");
        }
        if (user.getPassword().length() > 20) {
            errors.rejectValue("password", "invalid.password", "Password can not be more then 20 symbols");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "invalid.confirmPassword", "Password and confirm password should be same");
        }
    }
}
