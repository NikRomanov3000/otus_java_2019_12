package MyJdbcOrm.core.service.user;

import MyJdbcOrm.core.dao.user.UserDao;
import MyJdbcOrm.core.model.User;
import MyJdbcOrm.core.service.DbServiceException;
import MyJdbcOrm.core.sessionmanager.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DbServerUserImpl implements DbServiceUser {
    private static Logger logger = LoggerFactory.getLogger(DbServerUserImpl.class);

    private final UserDao userDao;

    public DbServerUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {
        try(SessionManager sessionManager = userDao.getSessionManager()){
            sessionManager.beginSession();
            try{
                long userId = userDao.saveUser(user);
                sessionManager.commitSession();

                logger.info("created appuser: {}", userId);
                return userId;
            } catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DbServiceException(ex);
            }
        }
    }

    @Override
    public Optional<User> getUser(long id) {
        try(SessionManager sessionManager = userDao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<User> userOptional = userDao.findById(id);

                logger.info("user: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }
}
