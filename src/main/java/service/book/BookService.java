package service.book;

import model.Book;
import model.Category;
import model.Location;
import model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {

    private String jdbcUrl = "jdbc:mysql://localhost:3306/book-management";
    private String jdbcUsername = "root";
    private String jdbcPassword = "250693";
    private static final String INSERT_BOOK_SQL = "insert into book (id, name, description, picture, status_id, category_id, author, location_id) value" +
            " (?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_BOOK_BY_ID = "select * from book where id =?";
    public static final String SELECT_ALL_BOOK = "select b.id, b.name, b.description, b.author, s.name, c.name as category_name, l.name, l.description, b.picture  from book b\n" +
            "    join status s on b.status_id = s.id " +
            "join category c on c.id = b.category_id " +
            "join location l on l.id = b.location_id order by b.id";

    public static final String SELECT_ALL_STATUS_BOOK ="select b.name, s.name from book b join status s on s.id = b.status_id;";

    private static final String DELETE_BOOK_SQL = "delete from book where id = ?;";
    private static final String UPDATE_BOOK_SQL = "update book set name = ?, description = ?, status_id = ?, category_id = ?, author = ?, location_id = ? where id = ?;";

    private static final String SELECT_ALL_STATUS = "select * from status";
    private static final String SELECT_ALL_CATEGORY = "select * from category";
    private static final String SELECT_ALL_LOCATION = "select * from location";

    private static final String SELECT_STATUS_BY_ID = "select * from status where id = ?";
    private static final String SELECT_CATEGORY_BY_ID = "select * from category where id = ?";
    private static final String SELECT_LOCATION_BY_ID = "select * from location where id = ?";
    private static final String SEARCH_BY_NAME = "select * from book where name like ?";


    public BookService() {
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
    public void insertBook(Book book) {
        System.out.println(INSERT_BOOK_SQL);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into book ( name, description, picture, status_id, category_id, author, location_id) values(?,?,?,?,?,?,?);");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setString(3, book.getPicture());
            preparedStatement.setInt(4, book.getStatus().getId());
            preparedStatement.setInt(5, book.getCategory().getId());
            preparedStatement.setString(6, book.getAuthor());
            preparedStatement.setInt(7, book.getLocation().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book selectBook(int id) {
        Book book = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);
            {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");

                    int status_id = rs.getInt("status_id");
                    Status status = getStatusById(status_id);

                    int category_id = rs.getInt("category_id");
                    Category category = getCategoryById(category_id);

                    String author = rs.getString("author");

                    int location_id = rs.getInt("location_id");
                    Location location = getLocationById(location_id);
                    book = new Book(name, description, status, author, category, location);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> selectAllBook() {
        List<Book> books = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOK);
            {
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String picture = rs.getString("picture");
                    String author = rs.getString("author");
                    Book b = new Book(id, name, description, picture, author);

                    String category_name = rs.getString("category_name");
                    Category category = new Category(category_name);
                    b.setCategory(category);

                    String status_name = rs.getString("s.name");
                    Status status = new Status(status_name);
                    b.setStatus(status);

                    String location_name = rs.getString("l.name");
                    Location location = new Location(location_name);
                    b.setLocation(location);
                    books.add(b);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> selectAllStatusBook() {
        List<Book> books = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATUS_BOOK);
            {
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Book b = new Book(id, name);

                    String status_name = rs.getString("s.name");
                    Status status = new Status(status_name);
                    b.setStatus(status);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean deleteBook(int id) throws SQLException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_SQL);
        {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_SQL);
        {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setInt(3, book.getStatus().getId());
            preparedStatement.setInt(4, book.getCategory().getId());
            preparedStatement.setString(5, book.getAuthor());
            preparedStatement.setInt(6, book.getLocation().getId());
            preparedStatement.setInt(7, book.getId());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }

    @Override
    public List<Status> selectAllStatus() {
        List<Status> statusList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STATUS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Status status = new Status(id, name);
                statusList.add(status);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return statusList;
    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categoryList.add(category);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categoryList;
    }

    @Override
    public List<Location> selectAllLocation() {
        List<Location> locationList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_LOCATION);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Location location = new Location(id, name);
                locationList.add(location);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return locationList;
    }

    @Override
    public Status getStatusById(int statusId) {
        Status status = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATUS_BY_ID);
            {
                preparedStatement.setInt(1, statusId);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    status = new Status(statusId, name);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Category category = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
            {
                preparedStatement.setInt(1, categoryId);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    category = new Category(categoryId, name);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Book>  searchByName(String name) {
        List<Book> bookList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME);
            {
                preparedStatement.setString(1, "%" + name + "%");
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name1 = rs.getString("name");
                    String description = rs.getString("description");
                    String picture = rs.getString("picture");

                    int status_id = rs.getInt("status_id");
                    Status status = getStatusById(status_id);

                    int category_id = rs.getInt("category_id");
                    Category category = getCategoryById(category_id);

                    String author = rs.getString("author");

                    int location_id = rs.getInt("location_id");
                    Location location = getLocationById(location_id);
                    Book book = new Book(id, name1, description, picture, status, category, author, location);
                    bookList.add(book);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookList;
    }

    @Override
    public Book searhByLocation(String locationName) {
        return null;
    }

    @Override
    public Location getLocationById(int locationId) {
        Location location = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOCATION_BY_ID);
            {
                preparedStatement.setInt(1, locationId);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    location = new Location(locationId, name);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return location;
    }
}
