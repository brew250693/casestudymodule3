package controller.book;

import model.Book;
import model.Category;
import model.Location;
import model.Status;
import service.book.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookController", value = "/book")
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;

    public void init() {
        bookService = new BookService();
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showCreateForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteBook(request, response);
                    break;
                case "statusbook":
                    statusBook(request, response);
                    break;
                default:
                    listBook(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");

        List<Status> statusList = bookService.selectAllStatus();
        request.setAttribute("status", statusList);
        List<Category> categoryList = bookService.selectAllCategory();
        request.setAttribute("category", categoryList);
        List<Location> locationList = bookService.selectAllLocation();
        request.setAttribute("location", locationList);
        dispatcher.forward(request, response);
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = bookService.selectAllBook();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    private void statusBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = bookService.selectAllBook();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookstatus.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Category> categoryList = bookService.selectAllCategory();
        List<Status> statusList = bookService.selectAllStatus();
        List<Location> locationList = bookService.selectAllLocation();

        Book existingBook = bookService.selectBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("book", existingBook);
        request.setAttribute("category", categoryList);
        request.setAttribute("status", statusList);
        request.setAttribute("location", locationList);

        dispatcher.forward(request, response);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookService.deleteBook(id);

        List<Book> listBook = bookService.selectAllBook();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertBook(request, response);
                    break;
                case "edit":
                    updateBook(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }



    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String picture = request.getParameter("picture");
        String author = request.getParameter("author");

        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        Category category = bookService.getCategoryById(categoryId);

        int statusId = Integer.parseInt(request.getParameter("status_id"));
        Status status = bookService.getStatusById(statusId);

        int locationId = Integer.parseInt(request.getParameter("location_id"));
        Location location = bookService.getLocationById(locationId);

        Book b = new Book(name, description, picture, status, category, author, location);
        bookService.insertBook(b);
        System.out.println(b);
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        dispatcher.forward(request, response);
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String author = request.getParameter("author");

        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        Category category = bookService.getCategoryById(categoryId);

        int statusId = Integer.parseInt(request.getParameter("status_id"));
        Status status = bookService.getStatusById(statusId);

        int locationId = Integer.parseInt(request.getParameter("location_id"));
        Location location = bookService.getLocationById(locationId);

        Book b = new Book(id, name, description, status, author, category, location);
        bookService.updateBook(b);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        dispatcher.forward(request, response);
    }
}
