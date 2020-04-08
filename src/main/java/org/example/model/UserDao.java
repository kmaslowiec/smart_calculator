package org.example.model;

import org.example.entity.User;

import java.sql.SQLException;
import java.util.logging.Logger;

public class UserDao implements UserRepository<User, Long> {

    private final static Logger LOGGER = Logger.getLogger(UserDao.class.getName());

    @Override
    public boolean create(User user) {
        try {
            SqlConnection.getUserOrmLiteDao().createIfNotExists(user);
            return true;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return false;
    }

    @Override
    public User findById(Long id) {
        try {
            return id != null ? SqlConnection.getUserOrmLiteDao().queryForId(id) : null;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return null;
    }

    public User findByEmail(String email) {
        try {
            return SqlConnection.getUserOrmLiteDao().queryBuilder().where().eq("email", email).queryForFirst();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return new User();
    }

    public User findByName(String name) {
        try {
            return SqlConnection.getUserOrmLiteDao().queryBuilder().where().eq("name", name).queryForFirst();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return new User();
    }

    @Override
    public boolean update(User user) {
        try {
            if (SqlConnection.getUserOrmLiteDao().idExists(user.id)) {
                SqlConnection.getUserOrmLiteDao().update(user);
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
            SqlConnection.getUserOrmLiteDao().delete(user);
            return true;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return false;
    }
}