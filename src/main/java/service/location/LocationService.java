package service.location;

import model.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationService implements ILocationService {

    private String jdbcUrl = "jdbc:mysql://localhost:3306/book-management";
    private String jdbcUsername = "root";
    private String jdbcPassword = "250693";

    private static final String INSERT_LOCATION_SQL = "insert into location (id, name, description, book_amount, book_quanity) values (?,?,?,?,?)\")";
    private static final String SELECT_LOCATION_BY_ID = "select * from location where id = ?";
    private static final String DELETE_LOCATION_SQL = "delete from location where id = ?;";
    public static final String SELECT_ALL_LOCATION = "select * from location";
    private static final String UPDATE_LOCATION_SQL = "update location set name = ?, description = ?, book_amount = ?, book_quanity = ? where id = ?";

    public LocationService() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            System.out.println("ket noi thanh cong");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertLocation(Location location) {
        System.out.println(INSERT_LOCATION_SQL);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into location ( name, description, book_amount, book_quanity) values (?,?,?,?)");
            preparedStatement.setString(1, location.getName());
            preparedStatement.setString(2, location.getDescription());
            preparedStatement.setString(3, location.getBook_amount());
            preparedStatement.setString(4, location.getBook_quanity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Location selectLocation(int id) {
        Location location = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOCATION_BY_ID);
            {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String book_amount = rs.getString("book_amount");
                    String book_quanity = rs.getString("book_quanity");
                    location = new Location(name,description,book_amount,book_quanity);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return location;
    }

    @Override
    public boolean deleteLocation(int id) throws SQLException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LOCATION_SQL);
        {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public List<Location> selectAllLocation() {
        List<Location> locationList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOCATION);
            {
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String bookamount = rs.getString("book_amount");
                    String bookquanity = rs.getString("book_quanity");
                    Location l = new Location(id,name,description,bookamount,bookquanity);
                    locationList.add(l);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return locationList;
    }

    @Override
    public boolean updateLocation(Location location) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOCATION_SQL);
        {
            preparedStatement.setString(1, location.getName());
            preparedStatement.setString(2, location.getDescription());
            preparedStatement.setString(3, location.getBook_amount());
            preparedStatement.setString(4, location.getBook_quanity());
            preparedStatement.setInt(5, location.getId());


            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
}
