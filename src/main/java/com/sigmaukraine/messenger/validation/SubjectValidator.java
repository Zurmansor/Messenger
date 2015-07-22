package com.sigmaukraine.messenger.validation;

import com.sigmaukraine.messenger.domain.Subject;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SubjectValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return Subject.class.isAssignableFrom(aClass);
    }

    /**
     * Validates subject. Name can not be less then 2 symbols
     * Name can not be more then 45 symbols.
     * Description can not be more then 500 symbols.
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Name is required.");

        Subject subject = (Subject) o;
        if (subject.getName().length() < 2) {
            errors.rejectValue("name", "invalid.name", "Name can not be less then 2 symbols");
        }
        if (subject.getName().length() > 45) {
            errors.rejectValue("name", "invalid.name", "Name can not be more then 45 symbols");
        }

        if (subject.getDescription().length() > 500) {
            errors.rejectValue("description", "invalid.description", "Description can not be more then 500 symbols");
        }
    }
}
