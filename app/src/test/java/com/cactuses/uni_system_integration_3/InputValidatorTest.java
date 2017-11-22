package com.cactuses.uni_system_integration_3;

import com.cactuses.uni_system_integration_3.utils.InputValidator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class InputValidatorTest {
    @Test
    public void loginInput_isFilled_1() throws Exception {
        assertThat(InputValidator.isFilledInput("", ""), is(false));
    }
    @Test
    public void loginInput_isFilled_2() throws Exception {
        assertThat(InputValidator.isFilledInput(" ", " "), is(false));
    }
    @Test
    public void loginInput_isFilled_3() throws Exception {
        assertThat(InputValidator.isFilledInput("qwerty", ""), is(false));
    }
    @Test
    public void loginInput_isFilled_4() throws Exception {
        assertThat(InputValidator.isFilledInput("", "qwerty"), is(false));
    }
	 @Test
    public void loginInput_isLengthValid_1() throws Exception {
        assertThat(InputValidator.isLengthValid("1", "123456"), is(false));
    }
    @Test
    public void loginInput_isLengthValid_2() throws Exception {
        assertThat(InputValidator.isLengthValid("1234", "12"), is(false));
    }
    @Test
    public void loginInput_isLengthValid_3() throws Exception {
        assertThat(InputValidator.isLengthValid("abc", "pass"), is(true));
    }
    @Test
    public void loginInput_isUsernameValid() throws Exception {
        assertThat(InputValidator.isUsernameValid("example@mail.com"), is(false));
    }
 @Test
    public void loginInput_isAdmin() throws Exception{
        assertTrue(InputValidator.isAdminCredentials("admin", "pass"));
    }

    @Test
    public void loginInput_testCredentialsLenght() throws Exception{
        assertEquals(InputValidator.getCredentialsLenght("name", "12345678"), 12);
    }
}