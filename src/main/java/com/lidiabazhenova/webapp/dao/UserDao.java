package com.lidiabazhenova.webapp.dao;

import com.lidiabazhenova.webapp.dao.connection.ConnectionUtil;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UserDao {

    public static final UserDao HOLDER_INSTANCE = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return HOLDER_INSTANCE;
    }

    private static final String ALL_USERS_QUERY = "SELECT * FROM users";

    private static final String GET_USER_QUERY = "SELECT * FROM users WHERE id = ?";

    private static final String GET_USER_BY_LOGIN_QUERY = "SELECT * FROM users WHERE login = ?";

    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    private static final String INSERT_USER_QUERY = "INSERT INTO users(login, first_name, last_name, password) " +
            "VALUES(?, ?, ?, ?)";

    private static final String UPDATE_USER_QUERY = "UPDATE users SET login = ?, password = ? WHERE id = ?";

    public List<User> getUsers() throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(ALL_USERS_QUERY);
            resultSet = preparedStatement.executeQuery();

            final List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(populateUserFromResultSet(resultSet));
            }

            return users;

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public User getUser(final long id) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_USER_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return populateUserFromResultSet(resultSet);
            } else {
                return null;
            }

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public User getUserByLogin(final String login) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_QUERY);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return populateUserFromResultSet(resultSet);
            } else {
                return null;
            }

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public void deleteUser(final long id) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    public void insertUser(final User user) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER_QUERY);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    public void updateUser(final User user) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    private User populateUserFromResultSet(final ResultSet resultSet) throws SQLException {
        final User user = new User.UserBuilder()
        .setUserId(resultSet.getLong("id"))
        .setLogin(resultSet.getString("login"))
        .setFirstName(resultSet.getString("first_name"))
        .setLastName(resultSet.getString("last_name"))
        .setPassword(resultSet.getString("password")).build();

        return user;
    }
}
