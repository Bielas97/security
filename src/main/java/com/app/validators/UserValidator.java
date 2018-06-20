package com.app.validators;

import com.app.model.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto)o;

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            errors.rejectValue("password", "Wrong password");
            errors.rejectValue("confirmPassword", "Wrong password");
        }

        if (!userDto.getUsername().matches(".+@\\w+\\.\\w+")) {
            errors.rejectValue("username", "USERNAME MUST BE EMAIL");
        }
    }
}
