package org.example.service.impl;

import org.example.entity.History;
import org.example.entity.User;
import org.example.model.HistoryDao;
import org.example.model.InMemory;
import org.example.service.HistoryService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class HistoryServiceImpl implements HistoryService {

    private static final Logger LOGGER = Logger.getLogger(HistoryServiceImpl.class.getName());
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

    @Override
    public List<History> readByDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            Date dateValue = sdf.parse(date);
            System.out.println(dateValue.getTime());
        } catch (ParseException e) {
            LOGGER.info(e.getMessage());
        }

        return null;
    }
}
