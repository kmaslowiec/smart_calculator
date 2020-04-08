package org.example.model;

import org.example.entity.History;

import java.sql.SQLException;
import java.util.logging.Logger;

public class HistoryDao implements HistoryRepository<History, Long> {

    private final static Logger LOGGER = Logger.getLogger(HistoryDao.class.getName());

    @Override
    public boolean create(History history) {
        try {
            SqlConnection.getHistoryOrmLiteDao().createIfNotExists(history);
            return true;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return false;
    }
}
