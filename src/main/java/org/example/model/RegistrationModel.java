package org.example.model;

import org.example.entity.User;
import org.example.exception.InvalidEntryException;

public interface RegistrationModel {

    boolean create(User user) throws InvalidEntryException;
}