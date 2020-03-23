package MyJdbcOrm.core.dao;

import MyJdbcOrm.core.DbExecutor;
import MyJdbcOrm.core.model.User;
import MyJdbcOrm.core.sessionmanager.SessionManager;
import MyJdbcOrm.core.sessionmanager.SessionManagerJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoJdbc.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<User> dbExecutor;

    public UserDaoJdbc(SessionManagerJdbc sessionManager, DbExecutor<User> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return dbExecutor.selectRecord(getConnection(), "select id, name from user where id  = ?", id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("age"));
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
                return null;
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public long saveUser(User appuser) {
        try{
            return dbExecutor.insertRecord(getConnection(),"insert into appuser(name) values (?)", Collections.singletonList(appuser.getName()));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new UserDaoException(ex);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

}
