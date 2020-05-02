package MyJdbcOrm.core.dao.user;

import MyJdbcOrm.core.DbExecutor;
import MyJdbcOrm.core.model.User;
import MyJdbcOrm.core.sessionmanager.SessionManager;
import MyJdbcOrm.core.sessionmanager.SessionManagerJdbc;
import MyJdbcOrm.jdbcHelper.JdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoJdbc.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<User> dbExecutor;
    private final JdbcMapper jdbcMapper;

    public UserDaoJdbc(SessionManagerJdbc sessionManager, DbExecutor<User> dbExecutor,JdbcMapper jdbcMapper) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.jdbcMapper = jdbcMapper;
    }

    @Override
    public Optional<User> findById(long id) {
        //"select id, name from user where id  = ?"
        try {
            return dbExecutor.selectRecord(getConnection(), jdbcMapper.createSQLSelectByParam(User.class, "id"), id, resultSet -> {
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
    public long saveUser(User user) {

        try{
            return dbExecutor.insertRecord(getConnection(),jdbcMapper.createSQLInsert(User.class), jdbcMapper.getListFiledValue(user));
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
