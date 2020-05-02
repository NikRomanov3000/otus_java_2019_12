package MyJdbcOrm.core.dao.account;

import MyJdbcOrm.core.DbExecutor;
import MyJdbcOrm.core.dao.user.UserDaoJdbc;
import MyJdbcOrm.core.model.Account;
import MyJdbcOrm.core.sessionmanager.SessionManager;
import MyJdbcOrm.core.sessionmanager.SessionManagerJdbc;
import MyJdbcOrm.jdbcHelper.JdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AccountDaoJdbc implements AccountDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoJdbc.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<Account> dbExecutor;
    private final JdbcMapper jdbcMapper;

    public AccountDaoJdbc(SessionManagerJdbc sessionManager, DbExecutor<Account> dbExecutor, JdbcMapper jdbcMapper) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.jdbcMapper = jdbcMapper;
    }

    @Override
    public Optional<Account> findById(long id) {
        try {
            return dbExecutor.selectRecord(getConnection(), jdbcMapper.createSQLSelectByParam(Account.class, "id"), id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new Account(resultSet.getLong("id"), resultSet.getString("type"), resultSet.getInt("n_attribute_value"), resultSet.getString("s_attribute_value"));
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
    public long saveAccount(Account account) {

        try{
            return dbExecutor.insertRecord(getConnection(),jdbcMapper.createSQLInsert(Account.class), jdbcMapper.getListFiledValue(account));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new AccountDaoException(ex);
        }
    }

    @Override
    public void createOrUpdate(Account account, long id, String fieldForUpdateName, Object valueForUpdate) {
        if(findById(id)!=null){
          UpdateField(account, id, fieldForUpdateName, valueForUpdate);
        } else saveAccount(account);
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    private long UpdateField(Account account, long id, String fieldForUpdateName, Object valueForUpdate){
        try{
            return dbExecutor.updateField(getConnection(), jdbcMapper.createSQLUpdate(Account.class, fieldForUpdateName, valueForUpdate), id);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new AccountDaoException(ex);
        }
    }
}
