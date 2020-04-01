package org.example.model;

import org.example.entity.User;

public interface LoginModel {

    User getUserIfAuthorized(User user);
}
