package ru.otus.core.service;

import ru.otus.core.model.User;

import java.util.List;
import java.util.Optional;

public interface DbServiceUser {
    long saveUser(User user);
    Optional<User> getUserById(long id);
    long saveOrUpdate(User user);
    Optional<User> getUserByLogin (String login);
    Optional<User> getRandomUser();
    List<User> getAllUsers();
}
