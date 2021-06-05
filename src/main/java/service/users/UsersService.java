package service.users;

import model.Users;

import java.sql.*;

public class UsersService implements IUsersService {
    private static final String URL = "jdbc:mysql://localhost:3306/book-management";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private static final String SIGN_UP = "INSERT INTO `users` (`name`, `birth`, `email`, `phone`, `password`) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_USER = "UPDATE `users` SET `name` = ?, `birth` = ?, `email`, `phone` = ? WHERE `id` = ?;";
    private static final String UPDATE_PASSWORD = "UPDATE `users` SET `password` = ? WHERE `id` = ?;";
    private static final String LOGIN = "SELECT * FROM `users` WHERE `email` = ? AND `password` = ?;";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM `users` WHERE `id` = ?;";


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
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SIGN_UP)) {
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getBirth());
            preparedStatement.setString(3, users.getEmail());
            preparedStatement.setString(4, users.getPhone());
            preparedStatement.setString(5, users.getPassword());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public boolean login(String email, String password) {
        System.out.println(LOGIN);
        boolean status = false;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(LOGIN)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                status = true;
            }
            System.out.println(status);
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
    public boolean updateProfile(Users users) {
        System.out.println(UPDATE_USER);
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getBirth());
            preparedStatement.setString(3, users.getEmail());
            preparedStatement.setString(4, users.getPhone());
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
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1, users.getPassword());
            preparedStatement.setInt(2, users.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public Users selectUserById(int id) {
        System.out.println(SELECT_USER_BY_ID);
        Users users = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, users.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String birth = resultSet.getString("birth");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                users = new Users(id, name, birth, email, phone);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
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
