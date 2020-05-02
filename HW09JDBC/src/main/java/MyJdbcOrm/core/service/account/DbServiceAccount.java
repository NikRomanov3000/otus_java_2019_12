package MyJdbcOrm.core.service.account;

import MyJdbcOrm.core.model.Account;

import java.util.Optional;

public interface DbServiceAccount {
    long saveAccount(Account account);
    Optional<Account> getAccount(long id);
    void createOrUpdate(Account account, long id, String fieldForUpdateName, Object valueForUpdate);
}
