package com.lidiabazhenova.webapp.controllers.login;

import com.lidiabazhenova.webapp.dao.connection.ConnectionUtil;
import com.lidiabazhenova.webapp.exception.DataSourceException;

import java.sql.*;

public class LoginService {

    public static final String GET_PASSWORD_BY_LOGIN = "SELECT login, password FROM users";

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
