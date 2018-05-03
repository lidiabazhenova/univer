package com.lidiabazhenova.webapp.controllers.login;

public class LoginValidation {

    public boolean validateUser(String user, String password) {
        if (user.equals("user") && password.equals("password"))
            return true;

        return false;
    }

}
