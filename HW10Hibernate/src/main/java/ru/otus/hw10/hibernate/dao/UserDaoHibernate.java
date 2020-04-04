package ru.otus.hw10.hibernate.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw10.core.dao.UserDao;
import ru.otus.hw10.core.dao.UserDaoException;
import ru.otus.hw10.core.model.User;
import ru.otus.hw10.core.sessionmanager.SessionManager;
import ru.otus.hw10.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.hw10.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Optional;



public class UserDaoHibernate implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);
    private final SessionManagerHibernate sessionManager;

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return Optional.empty();
    }

    @Override
    public long saveUser(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
       try{
           Session hibernateSession = currentSession.getHibernateSession();
           if(user.getId()>0){
               hibernateSession.merge(user);
           } else {
               hibernateSession.persist(user);
           }
           return user.getId();
       } catch (Exception ex){
           logger.error(ex.getMessage(), ex);
           throw new UserDaoException(ex);
       }
    }

    @Override
    public long saveOrUpdate(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try{
            currentSession.getHibernateSession().saveOrUpdate(user);
            return user.getId();
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new UserDaoException(ex);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return null;
    }
}
