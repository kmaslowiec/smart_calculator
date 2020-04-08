package org.example.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.entity.History;
import org.example.entity.User;

import java.sql.SQLException;

public class SqlConnection {

    private static Dao<User, Long> userOrmLiteDao;
    private static Dao<History, Long> historyOrmLiteDao;

    public static ConnectionSource connector() throws SQLException {
        return new JdbcConnectionSource("jdbc:sqlite:users.db");
    }

    public static void createDatabase() {
        try {
            ConnectionSource conn = SqlConnection.connector();
            TableUtils.createTableIfNotExists(conn, User.class);
            userOrmLiteDao = DaoManager.createDao(conn, User.class);
            TableUtils.createTableIfNotExists(conn, History.class);
            historyOrmLiteDao = DaoManager.createDao(conn, History.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Dao<User, Long> getUserOrmLiteDao() {
        return userOrmLiteDao;
    }

    public static void setUserOrmLiteDao(Dao<User, Long> userDao) {
        SqlConnection.userOrmLiteDao = userDao;
    }

    public static Dao<History, Long> getHistoryOrmLiteDao() {
        return historyOrmLiteDao;
    }

    public static void setHistoryOrmLiteDao(Dao<History, Long> historyOrmLiteDao) {
        SqlConnection.historyOrmLiteDao = historyOrmLiteDao;
    }
}