package com.cactuses.uni_system_integration_3.utils;


import com.cactuses.uni_system_integration_3.R;

public final class InputValidator {
    public static int errResource;

    public InputValidator() {
    }

    public static boolean isValid(String username, String password) {
        if (isFilledInput(username, password)) {
            return true;
        }
        return false;
    }

    public static boolean isFilledInput(String username, String password) {
        if (username.trim().equals("") || password.trim().equals("")) {
            errResource = R.string.empty_err;
            return false;
        }
        return true;
    }
	public static boolean isLengthValid(String username, String password) {
        if (username.length() < 3 || password.length() < 4 ) {
            errResource = R.string.length_err;
            return false;
        }
        return true;
    }
}
