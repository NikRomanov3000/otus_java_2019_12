package ru.otus.hw10.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw10.core.dao.UserDao;
import ru.otus.hw10.core.model.User;
import ru.otus.hw10.core.sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceUserImpl implements DbServiceUser {
    private static Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);
    private final UserDao userDao;

    public DbServiceUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getUser(long id) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> userOptional = userDao.findById(id);
                logger.info("user: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public long saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.saveUser(user);
                sessionManager.commitSession();

                logger.info("created user: {}", userId);
                return userId;
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DbServiceException(ex);
            }
        }
    }
    @Override
    public long saveOrUpdate(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.saveOrUpdate(user);
                sessionManager.commitSession();

                logger.info("updated user: {}", userId);
                return userId;
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DbServiceException(ex);
            }
        }
    }
}
