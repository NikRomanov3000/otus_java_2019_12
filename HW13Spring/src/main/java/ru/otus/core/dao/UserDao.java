package ru.otus.core.dao;

import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);
    long saveUser(User user);
    long saveOrUpdate(User user);
    SessionManager getSessionManager();
    Optional<User> findByLogin(String login);
    Optional<User> findRandomUser();
    List<User> findAll();
}
