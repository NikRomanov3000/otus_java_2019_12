package ru.otus.webserviceHW.services;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUser;

import java.util.Optional;

public class UserAuthServiceImpl implements UserAuthService {
    private final DbServiceUser dbServiceUser;

    public UserAuthServiceImpl(DbServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public boolean authenticate(String login, String password) {
        Optional<User> user = dbServiceUser.getUserByLogin(login);
        if (user != null && !user.isEmpty()) {
            return dbServiceUser.getUserByLogin(login).get().getPassword().equals(password);
        } else {
            return false;
        }

        //return dbServiceUser.getUserByLogin(login).map(user -> user.getPassword().equals(password)).orElse(false);


    }
}
