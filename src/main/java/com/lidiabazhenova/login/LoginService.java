package com.lidiabazhenova.login;

public class LoginService {

    public boolean validateUser(String user, String password) {
        if (user.equals("user") && password.equals("password"))
            return true;

        return false;
    }

}
