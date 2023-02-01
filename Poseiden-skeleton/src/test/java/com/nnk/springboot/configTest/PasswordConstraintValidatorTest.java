package com.nnk.springboot.configTest;

import com.nnk.springboot.config.PasswordConstraintValidator;
import com.nnk.springboot.config.ValidPassword;
import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasswordConstraintValidatorTest {

    private ValidPassword validPassword  = mock(ValidPassword.class);

    private ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

    @Test
    public void itShouldBeValid(){

        when(validPassword.message()).thenReturn("Invalid Password");

        PasswordConstraintValidator passwordConstraintValidator =new PasswordConstraintValidator();
        passwordConstraintValidator.initialize(validPassword);

        User user = new User();
        user.setPassword("Password1!");

        boolean result = passwordConstraintValidator.isValid(user.getPassword(),constraintValidatorContext);

        assertTrue(result);

    }


}

