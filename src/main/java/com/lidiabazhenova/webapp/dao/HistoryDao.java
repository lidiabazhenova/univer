package com.lidiabazhenova.webapp.dao;

import com.lidiabazhenova.webapp.dao.connection.ConnectionUtil;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    public static final HistoryDao HOLDER_INSTANCE = new HistoryDao();

    private static final String ALL_HISTORY_QUERY = "SELECT * FROM history";
    private static final String GET_HISTORY_BY_ORDER_ID_QUERY = "SELECT * FROM history WHERE order_id = ?";
    private static final String INSERT_HISTORY_QUERY = "INSERT INTO history(order_id, description, date) VALUES(?, ?, ?)";

    private HistoryDao() {
    }

    public static HistoryDao getInstance() {
        return HOLDER_INSTANCE;
    }

    public List<History> getHistory() throws DataSourceException, ParseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(ALL_HISTORY_QUERY);
            resultSet = preparedStatement.executeQuery();

            List<History> history = new ArrayList<>();
            while (resultSet.next()) {
                history.add(populateHistoryFromResultSet(resultSet));
            }

            return history;

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public List<History> getHistoryByOrderId(final long id) throws DataSourceException, ParseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_HISTORY_BY_ORDER_ID_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            List<History> historyList = new ArrayList<>();
            while (resultSet.next()) {
                historyList.add(populateHistoryFromResultSet(resultSet));
            }
            return historyList;

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public void insertHistory(final History history) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_HISTORY_QUERY);
            preparedStatement.setLong(1, history.getOrderId());
            preparedStatement.setString(2, history.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(history.getDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    private History populateHistoryFromResultSet(final ResultSet resultSet) throws SQLException, ParseException {
        // DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS", Locale.ENGLISH);
        final History history = new History();
        history.setDescription(resultSet.getString("description"));
        history.setOrderId(resultSet.getLong("order_id"));
        history.setDate(resultSet.getDate("date"));
        return history;
    }
}