package org.example.model;

import org.example.entity.User;


public class InMemory {

    private static User user = new User();
    private static UserDao dao = new UserDao();

    public User getUser() {
        return user;
    }

    public void setUser(Long id) {
        user = dao.findById(id);
    }

    public void clearUser() {
        user = null;
    }
}