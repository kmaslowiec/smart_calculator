package org.example.service.impl;

import org.example.entity.History;
import org.example.entity.User;
import org.example.model.HistoryDao;
import org.example.model.InMemory;
import org.example.service.HistoryService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class HistoryServiceImpl implements HistoryService {

    private HistoryDao dao;
    private User user;

    public HistoryServiceImpl() {
        InMemory memory = new InMemory();
        dao = new HistoryDao();
        user = memory.getUser();
    }

    @Override
    public boolean add(History history) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTime = new Timestamp(now.getTime());
        history.setDate(currentTime);
        history.setUser(user);
        return dao.create(history);
    }
}
