package service.book;

import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookService {
    void createBook(Book book) throws SQLException;

    Book selectBook(int id);

    List<Book> selectAllBook();

    boolean deleteBook(int id) throws SQLException;

    boolean editBook(int id) throws SQLException;
}
