package com.sigmaukraine.messenger.validation;

import com.sigmaukraine.messenger.domain.Chat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChatValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return Chat.class.isAssignableFrom(aClass);
    }

    /**
     * Validates chat for required name
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Name is required.");
    }
}
