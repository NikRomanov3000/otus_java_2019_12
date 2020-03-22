package MyJdbcOrm.core.dao;

import MyJdbcOrm.core.model.User;
import MyJdbcOrm.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);
    long saveUser(User user);
    SessionManager getSessionManager();
}
