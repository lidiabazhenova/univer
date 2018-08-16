package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.dao.UserDao;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.User;

import java.util.List;

public final class UserService {

    public static final UserService HOLDER_INSTANCE = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return HOLDER_INSTANCE;
    }

    public List<User> getUsers() throws DataSourceException {
        return UserDao.getInstance().getUsers();
    }

    public User getUser(final long id) throws DataSourceException {
        return UserDao.getInstance().getUser(id);
    }

    public void deleteUser(final long id) throws DataSourceException {
        UserDao.getInstance().deleteUser(id);
    }

    public void addUser(final User user) throws DataSourceException {
        UserDao.getInstance().insertUser(user);
    }

    public void updateUser(final User user) throws DataSourceException {
        UserDao.getInstance().updateUser(user);
    }
}
