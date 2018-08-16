package com.lidiabazhenova.webapp.dao;

import com.lidiabazhenova.webapp.dao.connection.ConnectionUtil;
import com.lidiabazhenova.webapp.exception.DataSourceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public static final LoginDao HOLDER_INSTANCE = new LoginDao();

    public static final String GET_PASSWORD_BY_LOGIN = "SELECT login, password FROM users";

    public LoginDao() {
    }

    public static LoginDao getInstance() {
        return HOLDER_INSTANCE;
    }

    public boolean loginCheck(String username, String password) throws DataSourceException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String dbUsername, dbPassword;
        boolean login = false;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_PASSWORD_BY_LOGIN);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dbUsername = resultSet.getString("login");
                dbPassword = resultSet.getString("password");

                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    login = true;
                }
            }
            ConnectionUtil.close(resultSet, preparedStatement, connection);

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);

        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }

        return login;
    }
}
