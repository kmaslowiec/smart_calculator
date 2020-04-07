package org.example.model.impl;

import org.example.entity.User;
import org.example.model.LoginModel;
import org.example.model.UserDao;

public class LoginModelImpl implements LoginModel {

    UserDao dao = new UserDao();

    @Override
    public User getUserIfAuthorized(User user) {
        User inDb = user.getName() == null ? dao.findByEmail(user.getEmail()) : dao.findByName(user.getName());
        if (inDb != null && isAuthorized(user, inDb)) {
            return inDb;
        }
        return null;
    }

    public boolean isAuthorized(User enteredUser, User inDb) {
        return dao.findById(inDb.getId()).getPassword().equals(enteredUser.getPassword());
    }

    public boolean isAuthorized(String enteredPassword, User inDb) {
        return dao.findById(inDb.getId()).getPassword().equals(enteredPassword);
    }
}