package org.example.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.entity.History;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.logging.Logger;

public class UserDao implements SqlRepository<User, Long> {

    private final static Logger LOGGER = Logger.getLogger(UserDao.class.getName());
    private Dao<User, Long> userDao;
    private Dao<History, Long> historyDao;

    public UserDao() {
        try {
            ConnectionSource conn = SqlConnection.connector();
            TableUtils.createTableIfNotExists(conn, User.class);
            userDao = DaoManager.createDao(conn, User.class);
            TableUtils.createTableIfNotExists(conn, History.class);
            historyDao = DaoManager.createDao(conn, History.class);

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

    public User findByEmail(String email) {
        try {
            return userDao.queryBuilder().where().eq("email", email).queryForFirst();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return new User();
    }

    public User findByName(String name) {
        try {
            return userDao.queryBuilder().where().eq("name", name).queryForFirst();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return new User();
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