package org.example.model;

import org.example.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface HistoryRepository<History, Long> {

    boolean create(History history);

    List<History> findByUser(User user);

    List<History> findByDate(Timestamp date);
}
