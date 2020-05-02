package MyJdbcOrm.core.dao.account;

public class AccountDaoException extends RuntimeException {
    public AccountDaoException(Exception ex){
        super(ex);
    }
}
