package org.example.model;

import org.example.entity.History;
import org.example.entity.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
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

    @Override
    public List<History> findByUser(User user) {
        return null;
    }

    @Override
    public List<History> findByDate(Timestamp date) {
        return null;
    }
}
