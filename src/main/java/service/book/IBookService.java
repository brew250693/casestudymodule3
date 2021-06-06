package service.book;

import model.Book;
import model.Category;
import model.Location;
import model.Status;

import java.sql.SQLException;
import java.util.List;

public interface IBookService {
    void insertBook(Book book);

    Book selectBook(int id);

    List<Book> selectAllBook();

    List<Book> selectAllStatusBook();

    List<Status> selectAllStatus();

    List<Category> selectAllCategory();

    List<Location> selectAllLocation();

    boolean deleteBook(int id) throws SQLException;

    boolean updateBook(Book book) throws SQLException;

    Status getStatusById(int statusId);
    Category getCategoryById(int categoryId);
    Location getLocationById(int locationId);

    List<Book> searchByName(String name);
    List<Book> searchByStatus(String Status);

    Book searhByLocation(String locationName);
}
