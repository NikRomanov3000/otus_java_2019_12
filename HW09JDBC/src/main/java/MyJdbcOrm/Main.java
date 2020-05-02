package MyJdbcOrm;

import MyJdbcOrm.core.DbExecutor;
import MyJdbcOrm.core.dao.account.AccountDaoJdbc;
import MyJdbcOrm.core.dao.user.UserDaoJdbc;
import MyJdbcOrm.core.model.Account;
import MyJdbcOrm.core.model.User;
import MyJdbcOrm.core.service.account.DbServiceAccountImpl;
import MyJdbcOrm.core.service.user.DbServerUserImpl;
import MyJdbcOrm.core.sessionmanager.SessionManager;
import MyJdbcOrm.core.sessionmanager.SessionManagerJdbc;
import MyJdbcOrm.jdbcHelper.JdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:h2:mem:";
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        List<String> someListOfParam = new ArrayList<>(Arrays.asList("Nikita", "20"));
/*

        String sql = myMapper.createSQLSelect(User.class);
        String sqlParam = myMapper.createSQLSelectByParam(Account.class, "type");
        String sqlInsert = myMapper.createSQLInsert(User.class);
        String sqlUpdate = myMapper.createSQLUpdate(User.class, "name", "Mike");
        System.out.println(sql);
        System.out.println(sqlParam);
        System.out.println(sqlInsert);
        System.out.println(sqlUpdate);
*/
        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);
            JdbcMapper myMapper = new JdbcMapper();
            DbExecutor<User> userDbExecutor = new DbExecutor<>();
            DbExecutor<Account> accountDbExecutor = new DbExecutor<>();

            SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource); //не могу понять, как тут указать data source
            DbServerUserImpl dbServerUser = new DbServerUserImpl(new UserDaoJdbc( sessionManager ,userDbExecutor, myMapper));
            DbServiceAccountImpl dbServiceAccount = new DbServiceAccountImpl (new AccountDaoJdbc(sessionManager, accountDbExecutor, myMapper ));

        }
    }
}
