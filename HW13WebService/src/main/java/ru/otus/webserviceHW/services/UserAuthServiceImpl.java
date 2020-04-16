package ru.otus.webserviceHW.services;

import ru.otus.core.dao.UserDao;
import ru.otus.core.service.DbServiceUser;

public class UserAuthServiceImpl implements UserAuthService {
    private final DbServiceUser dbServiceUser;

    public UserAuthServiceImpl(DbServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return dbServiceUser.getUserByLogin(login).get().getPassword().equals(password);
        //return dbServiceUser.getUserByLogin(login).map(user -> user.getPassword().equals(password)).orElse(false);


    }
}
