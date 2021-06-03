package service.users;

import model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersService implements IUsersService{
    private static final String URL = "jdbc:mysql://localhost:3306/book-management";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private static final String SIGN_UP = "INSERT INTO `users` (`name`, `birth`, `email`, `phone`, `avatar`, `password`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_USER = "UPDATE `users` SET `name` = ?, `birth` = ?, `phone` = ?, `avatar` = ? WHERE `id` = ?;";
    private static final String UPDATE_PASSWORD = "UPDATE `users` SET `password` = ? WHERE `id` = ?;";
    private static final String LOGIN = "SELECT * FROM `users` WHERE `email` = ? AND `password` = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void signUp(Users users) {
        System.out.println(SIGN_UP);
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SIGN_UP)) {
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getBirth());
            preparedStatement.setString(3, users.getEmail());
            preparedStatement.setString(4, users.getPhone());
            preparedStatement.setString(5, users.getAvatar());
            preparedStatement.setString(6, users.getPassword());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public boolean login(Users users) {
        System.out.println(LOGIN);
        boolean status = false;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(LOGIN)) {
            preparedStatement.setString(1, users.getEmail());
            preparedStatement.setString(2, users.getPassword());

            System.out.println(preparedStatement);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public boolean updateUser(Users users) {
        System.out.println(UPDATE_USER);
        boolean rowUpdated = false;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getBirth());
            preparedStatement.setString(3, users.getPhone());
            preparedStatement.setString(4, users.getAvatar());
            preparedStatement.setInt(5, users.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public boolean changePassword(Users users) {
        System.out.println(UPDATE_PASSWORD);
        boolean rowUpdated = false;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1, users.getPassword());
            preparedStatement.setInt(2, users.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException e) {
        for (Throwable throwable : e) {
            if (throwable instanceof SQLException) {
                throwable.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) throwable).getSQLState());
                System.err.println("Error Code: " + ((SQLException) throwable).getErrorCode());
                System.err.println("Message: " + throwable.getMessage());
                Throwable throwable1 = e.getCause();
                while (throwable1 != null) {
                    System.out.println("Cause: " + throwable1);
                    throwable1 = throwable1.getCause();
                }
            }
        }
    }
}
