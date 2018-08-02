package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.dao.HistoryDao;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.History;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class HistoryService {
    public static final HistoryService HOLDER_INSTANCE = new HistoryService();

    private HistoryService() {
    }

    public static HistoryService getInstance() {
        return HOLDER_INSTANCE;
    }

    public List<History> getHistoryByOrderId(final long id) throws DataSourceException, ParseException {
        return Collections.unmodifiableList(HistoryDao.getInstance().getHistoryByOrderId(id));
    }

    public List<History> getHistory(final long id) throws DataSourceException, ParseException {
        return Collections.unmodifiableList(HistoryDao.getInstance().getHistory());
    }

    public void addHistory(final History history) throws DataSourceException {
        HistoryDao.getInstance().insertHistory(history);
    }
}
