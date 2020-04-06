package ru.otus.hw10;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw10.core.dao.UserDao;
import ru.otus.hw10.core.model.Address;
import ru.otus.hw10.core.model.Phone;
import ru.otus.hw10.core.model.User;
import ru.otus.hw10.core.service.DbServiceUser;
import ru.otus.hw10.core.service.DbServiceUserImpl;
import ru.otus.hw10.hibernate.HibernateUtils;
import ru.otus.hw10.hibernate.dao.UserDaoHibernate;
import ru.otus.hw10.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Optional;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String HIBERNATE_CFG_XML_FILE_RESOURCE = "hibernate.cfg.xml";

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_XML_FILE_RESOURCE,
                User.class, Address.class, Phone.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DbServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        User nikita = new User(1,"Никита", 20);
        nikita.addUserPhones(new Phone("88005553535"));
        nikita.setUserAddress(new Address("Moscow"));

        dbServiceUser.saveUser(nikita);
        Optional<User> users = dbServiceUser.getUser(1);
/*
        nikita.addUserPhones(new Phone("88005553535"));
        nikita.setUserAddress(new Address("Moscow"));
        dbServiceUser.saveOrUpdate(nikita);

        users = dbServiceUser.getUser(1);
*/
        sessionManager.close();

        System.out.println(users);
    }

}
