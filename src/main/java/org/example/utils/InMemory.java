package org.example.utils;

import org.example.entity.User;
import org.example.model.UserDao;


public class InMemory {

    private static User user = new User();
    private static UserDao dao = new UserDao();

    public User getUser() {
        return user;
    }

    public void setUser(Long id) {
        user = dao.findById(id);
    }
}