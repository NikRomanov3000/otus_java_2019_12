package ru.otus.hw12.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;
import ru.otus.hw10.core.dao.UserDao;
import ru.otus.hw10.core.model.User;

import java.util.Optional;

public class InMemoryLoginServiceImpl extends AbstractLoginService {
    private final UserDao userDao;

    public InMemoryLoginServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal){
        return new String[] {"user"};
    }

    @Override
    protected UserPrincipal loadUserInfo(String login){
        System.out.println(String.format("InMemoryLoginService#loadUserInfo(%s)", login));
        Optional<User> dbUser = userDao.findByLogin(login);
        return dbUser.map(user -> new UserPrincipal(user.getLogin(), new Password(user.getPassword())))
                .orElse(null);
    }

}
