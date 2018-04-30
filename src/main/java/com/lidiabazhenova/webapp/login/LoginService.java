package com.lidiabazhenova.webapp.login;

public class LoginService {

    public boolean validateUser(String user, String password) {
        if (user.equals("user") && password.equals("password"))
            return true;

        return false;
    }

}
