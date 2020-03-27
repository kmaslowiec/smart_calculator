package org.example.repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.logging.Logger;

public class RegistrationDao implements SqlRepository<User, Long> {

    private final static Logger LOGGER = Logger.getLogger(RegistrationDao.class.getName());
    private Dao<User, Long> userDao;

    public RegistrationDao() {
        try {
            ConnectionSource conn = SqlConnection.connector();
            TableUtils.createTableIfNotExists(conn, User.class);
            userDao = DaoManager.createDao(conn, User.class);

        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public boolean create(User user) {
        try {
            userDao.createIfNotExists(user);
            return true;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return false;
    }

    @Override
    public User findById(Long id) {
        try {
            return id != null ? userDao.queryForId(id) : null;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(User user) {
        try {
            if (userDao.idExists(user.id)) {
                userDao.update(user);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        try {
            userDao.delete(user);
            return true;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return false;
    }
}