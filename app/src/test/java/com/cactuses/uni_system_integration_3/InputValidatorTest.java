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

}