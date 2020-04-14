package ru.otus;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.Address;
import ru.otus.core.model.Phone;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUser;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

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

        User nikita = new User(1, "Никита", 20);

        dbServiceUser.saveUser(nikita);
        Optional<User> users = dbServiceUser.getUser(1);

        nikita.addPhone(new Phone("88005553535"));
        nikita.setUserAddress(new Address("Moscow"));
        dbServiceUser.saveOrUpdate(nikita);

        users = dbServiceUser.getUser(1);

        sessionManager.close();

        System.out.println(users.get().getUserAddress().getFull_address());
    }

}
