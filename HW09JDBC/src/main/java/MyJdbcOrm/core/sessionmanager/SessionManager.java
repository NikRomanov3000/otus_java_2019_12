package MyJdbcOrm.core.sessionmanager;

public interface SessionManager {
    void beginSession();

    void commitSession();

    void rollbackSession();

    void close();

    DatabaseSession getCurrentSession();
}
