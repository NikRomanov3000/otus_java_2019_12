package MyJdbcOrm.core.dao.account;

import MyJdbcOrm.core.model.Account;
import MyJdbcOrm.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface AccountDao {
    Optional<Account> findById(long id);
    long saveAccount(Account account);
    void createOrUpdate(Account account, long id, String fieldForUpdateName, Object valueForUpdate);
    SessionManager getSessionManager();
}
