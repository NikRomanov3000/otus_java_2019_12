package ru.otus.webserviceHW.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUser;

import java.util.Optional;

public class InMemoryLoginServiceImpl extends AbstractLoginService {
    private final DbServiceUser dbServiceUser;

    public InMemoryLoginServiceImpl(DbServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal){
        return new String[] {"user"};
    }

    @Override
    protected UserPrincipal loadUserInfo(String login){
        System.out.println(String.format("InMemoryLoginService#loadUserInfo(%s)", login));
        Optional<User> dbUser = dbServiceUser.getUserByLogin(login);//findByLogin(login);
        return dbUser.map(user -> new UserPrincipal(user.getLogin(), new Password(user.getPassword())))
                .orElse(null);
    }

}
