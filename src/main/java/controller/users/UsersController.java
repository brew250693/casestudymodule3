package controller.users;

import model.Users;
import service.users.UsersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UsersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsersService service;

    public void init() {
        service = new UsersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "signUp":
                showSignUpForm(req, resp);
                break;
            case "login":
                showLoginForm(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            case "changePass":
                showChangePassForm(req, resp);
                break;
            case "editProfile":
                showEditProfileForm(req, resp);
                break;
            default:
                listBook(req, resp);
                break;
        }
    }

    private void showSignUpForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("lib/index.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void showChangePassForm(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void showEditProfileForm(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "signUp":
                signUp(req, resp);
                break;
            case "login":
                login(req, resp);
                break;
            case "changePass":
                changePass(req, resp);
                break;
            case "editProfile":
                editProfile(req, resp);
                break;
            default:
                listBook(req, resp);
                break;
        }
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("nameRes");
        String email = req.getParameter("emailRes");
        String phone = req.getParameter("phoneRes");
        String password = req.getParameter("passwordRes");

        while (true) {
            String retypePass = req.getParameter("retypePass");
            if (!retypePass.equals(password)) {
                System.out.println("Mật khẩu không trùng khớp!");
            } else {
                Users users = new Users(name, email, phone, password);
                service.signUp(users);
                RequestDispatcher dispatcher = req.getRequestDispatcher("lib/index.jsp");
                try {
                    dispatcher.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void changePass(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void editProfile(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void listBook(HttpServletRequest req, HttpServletResponse resp) {

    }
}
