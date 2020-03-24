package MyJdbcOrm;

import MyJdbcOrm.core.DbExecutor;
import MyJdbcOrm.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

public class Main {
    private static final String URL = "jdbc:h2:mem:";
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
          Main main = new Main();
        /*List <String> someListOfParam = new ArrayList<>(Arrays.asList("Nikita", "20"));
        JdbcMapper myMapper = new JdbcMapper();

        String sql = myMapper.createSQLSelect(User.class);
        String sqlParam = myMapper.createSQLSelectByParam(Account.class, "type", 2);
        String sqlInsert = myMapper.createSQLInsert(User.class, someListOfParam);
        String sqlUpdate = myMapper.createSQLUpdate(User.class, "name", "Mike");
        System.out.println(sql);
        System.out.println(sqlParam);
        System.out.println(sqlInsert);
        System.out.println(sqlUpdate);
        */

       try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);
            main.createTable(connection);

            DbExecutor<User> executor = new DbExecutor<>();
            long userId = executor.insertRecord(connection, "insert into user(name) values (?)", Collections.singletonList("testUserName"));
            logger.info("created user:{}", userId);
            connection.commit();

            Optional<User> user = executor.selectRecord(connection, "select id, name from user where id  = ?", userId, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("age"));
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
                return null;
            });
            System.out.println(user);
        }
    }

    private void createTable(Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50), age int)")) {
            pst.executeUpdate();
        }
    }
}
