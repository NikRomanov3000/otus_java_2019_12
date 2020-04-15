package ru.otus.hw11.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.cache.MyCacheImpl;
import ru.otus.hw11.cache.interfaceForHw.MyCache;
import ru.otus.hw11.core.dao.UserDao;
import ru.otus.hw11.core.model.User;
import ru.otus.hw11.core.sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceUserImpl implements DbServiceUser {
    private static Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);
    private final UserDao userDao;
    private MyCache<Long, Optional<User>> cache = new MyCacheImpl<>();

    public DbServiceUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getUser(long id) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> userOptional;
                if (checkCache(id) == null) {
                    userOptional = userDao.findById(id);
                    cache.put(id, userOptional);
                } else {
                    userOptional = cache.get(id);
                }
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
                if (checkCache(userId) == null) {
                    cache.put(userId, Optional.ofNullable(user));
                }
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
                if (checkCache(userId) == null) {
                    cache.put(userId, Optional.ofNullable(user));
                } else {
                    cache.remove(userId);
                    cache.put(userId, Optional.ofNullable(user));
                }
                logger.info("updated user: {}", userId);
                return userId;
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DbServiceException(ex);
            }
        }
    }

    private Optional<User> checkCache(long id) {
        Optional<User> cacheValue = (Optional<User>) cache.get(id);
        if (cacheValue != null) {
            return cacheValue;
        } else return null;
    }
}
