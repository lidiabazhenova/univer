package com.lidiabazhenova.webapp.dao.connection;

import java.sql.*;

public final class ConnectionUtil {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost/automation";
    private static final String USER = "automation_user";
    private static final String PASSWORD = "password";

    private ConnectionUtil() {
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // TODO : logger
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static void close(final PreparedStatement statement, final Connection conn) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (final SQLException ex) {
            // TODO : logger
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (final SQLException ex) {
            // TODO : logger
        }
    }

    public static void close(final ResultSet resultSet, final PreparedStatement statement,
                             final Connection conn) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (final SQLException ex) {
            // TODO : logger
        }

        close(statement, conn);
    }
}
