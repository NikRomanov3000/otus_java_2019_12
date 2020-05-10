package ru.otus.hibernate.dao;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.core.dao.UserDao;
import ru.otus.core.dao.UserDaoException;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.*;

@Repository
public class UserDaoHibernate implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);
    private final SessionManagerHibernate sessionManager;

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public long saveUser(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            if (user.getId() > 0) {
                hibernateSession.merge(user);
            } else {
                hibernateSession.persist(user);
            }
            return user.getId();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new UserDaoException(ex);
        }
    }

    @Override
    public long saveOrUpdate(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.saveOrUpdate(user);
            return user.getId();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new UserDaoException(ex);
        }
    }

    @Override
    public List<User> findAll() {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            String sql = "From " + User.class.getSimpleName();
            System.out.println("sql = " + sql);

            List<User> users = currentSession.getHibernateSession().createQuery(sql).list();
            return users;

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public Optional<User> findById(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        List<User> users = findAll();
        if(users!=null){
            for(User user : users){
                if(user.getLogin().equals(login)){
                    return Optional.ofNullable(user);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findRandomUser() {
        Random random = new Random();
        long leftLimit = 1L;
        long rightLimit = 10L;
        long randomId = new RandomDataGenerator().nextLong(leftLimit, rightLimit);

        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, randomId));
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Optional.empty();

    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
