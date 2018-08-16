package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.dao.LoginDao;
import com.lidiabazhenova.webapp.exception.DataSourceException;

public final class LoginService {

    public static final LoginService HOLDER_INSTANCE = new LoginService();

    public LoginService() {
    }

    public static LoginService getInstance() {
        return HOLDER_INSTANCE;
    }

    public boolean userValidation(String username, String password) throws DataSourceException {
        return LoginDao.getInstance().loginCheck(username, password);
    }
}
