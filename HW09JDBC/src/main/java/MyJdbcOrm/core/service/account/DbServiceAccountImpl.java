package MyJdbcOrm.core.service.account;

import MyJdbcOrm.core.dao.account.AccountDao;
import MyJdbcOrm.core.model.Account;
import MyJdbcOrm.core.service.DbServiceException;
import MyJdbcOrm.core.sessionmanager.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DbServiceAccountImpl implements DbServiceAccount {
    private static Logger logger = LoggerFactory.getLogger(DbServiceAccountImpl.class);
    private final AccountDao accountDao;

    public DbServiceAccountImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public long saveAccount(Account account) {
        try(SessionManager sessionManager = accountDao.getSessionManager()){
            sessionManager.beginSession();
            try{
                long accountId = accountDao.saveAccount(account);
                sessionManager.commitSession();
                logger.info("created account: {}", accountId);
                return accountId;
            } catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DbServiceException(ex);
            }
        }
    }

    @Override
    public Optional<Account> getAccount(long id) {
        try(SessionManager sessionManager = accountDao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<Account> accountOptional = accountDao.findById(id);
                logger.info("account: {}", accountOptional.orElse(null));
                return accountOptional;
            } catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public void createOrUpdate(Account account, long id, String fieldForUpdateName, Object valueForUpdate) {
        try(SessionManager sessionManager = accountDao.getSessionManager()){
            sessionManager.beginSession();
            try{
                accountDao.createOrUpdate(account, id, fieldForUpdateName, valueForUpdate);
                sessionManager.commitSession();
                logger.info("Updated account field: " +fieldForUpdateName+" new field value: "+String.valueOf(valueForUpdate)+": {}", account);
            } catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
            }
        }
    }
}
