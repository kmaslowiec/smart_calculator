package org.example.repository;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class SqlConnection {

    public static ConnectionSource connector() throws SQLException {
        return new JdbcConnectionSource("jdbc:sqlite:users.db");
    }
}
