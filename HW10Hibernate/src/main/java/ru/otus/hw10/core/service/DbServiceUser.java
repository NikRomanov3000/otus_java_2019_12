package ru.otus.hw10.core.service;

import ru.otus.hw10.core.model.User;

import java.util.Optional;

public interface DbServiceUser {
    long saveUser(User user);
    Optional<User> getUser(long id);
}
