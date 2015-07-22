package com.sigmaukraine.messenger.validation;

import com.sigmaukraine.messenger.domain.Message;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MessageValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return Message.class.isAssignableFrom(aClass);
    }

    /**
     * Validates message for required text
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "required.text", "Text is required.");
    }
}
