package MyJdbcOrm.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DbExecutor<T> {
    private static Logger logger = LoggerFactory.getLogger(DbExecutor.class);

    public long insertRecord(Connection connection, String sql, List<String> params) throws SQLException {
        Savepoint savepoint = connection.setSavepoint("savePointName");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, params.get(i));
            }
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            connection.rollback(savepoint);
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    public Optional<T> selectRecord(Connection connection, String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return Optional.ofNullable(rsHandler.apply(resultSet));
            }
        }
    }

    public long updateField(Connection connection, String sql, long id) throws SQLException {
        Savepoint savepoint = connection.setSavepoint("savePointName");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            connection.rollback(savepoint);
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }
}
