package com.lidiabazhenova.login;

public class LoginService {

    public boolean isUserValid(String user, String password) {
        if (user.equals("user") && password.equals("password"))
            return true;

        return false;
    }

}
