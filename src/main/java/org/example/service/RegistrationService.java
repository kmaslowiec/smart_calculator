package org.example.service;

import org.example.entity.User;
import org.example.exception.InvalidEntryException;

public interface RegistrationService {

    boolean create(User user) throws InvalidEntryException;

    User read(User user);

    boolean update(User user);

    boolean delete(User user);
}