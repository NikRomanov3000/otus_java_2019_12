package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import ru.otus.webserviceHW.server.UsersWebServer;
import ru.otus.webserviceHW.server.UsersWebServerWithFilterBasedSecurity;
import ru.otus.webserviceHW.services.TemplateProcessor;
import ru.otus.webserviceHW.services.TemplateProcessorImpl;
import ru.otus.webserviceHW.services.UserAuthService;
import ru.otus.webserviceHW.services.UserAuthServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String HIBERNATE_CFG_XML_FILE_RESOURCE = "hibernate.cfg.xml";
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_XML_FILE_RESOURCE,
                User.class, Address.class, Phone.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);

        UserDao userDao = new UserDaoHibernate(sessionManager);
        DbServiceUser dbServiceUser = new DbServiceUserImpl(userDao);
        inMemoryUsers(dbServiceUser);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(dbServiceUser);
        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, dbServiceUser, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();

        sessionManager.close();
    }

    private static void inMemoryUsers(DbServiceUser dbServiceUser) {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Крис Гир", "user1", "11111"));
        users.add(new User(2L, "Ая Кэш", "user2", "11111"));
        users.add(new User(3L, "Десмин Боргес", "user3", "11111"));
        users.add(new User(4L, "Кетер Донохью", "user4", "11111"));
        users.add(new User(5L, "Стивен Шнайдер", "user5", "11111"));
        users.add(new User(6L, "Джанет Вэрни", "user6", "11111"));
        users.add(new User(7L, "Брэндон Смит", "user7", "11111"));
        users.add(new User(8L, "Джанет Вэрни", "user6", "11111"));
        users.add(new User(9L, "Брэндон Смит", "user7", "11111"));
        users.add(new User(10L, "Никита Романов", "nromanov", "123"));

        for (User user : users) {
            dbServiceUser.saveUser(user);
        }
    }

}
