package org.example.service.impl;

import org.example.controller.RegisterController;
import org.example.entity.User;
import org.example.exception.InvalidEntryException;
import org.example.repository.UserDao;
import org.example.service.RegistrationService;
import org.example.utils.MyRegex;

import java.util.logging.Logger;

public class RegistrationServiceImpl implements RegistrationService {

    private final static Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    private UserDao dao = new UserDao();

    private void emailValidation(String email) throws InvalidEntryException {
        if (!email.matches(MyRegex.EMAIL_IS_VALID)) {
            LOGGER.info("Invalid e-mail address");
            throw new InvalidEntryException("Invalid e-mail address");
        }
    }

    @Override
    public boolean create(User user) throws InvalidEntryException {
        emailValidation(user.getEmail());
        return dao.create(user);
    }

    @Override
    public User read(User user) {
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}