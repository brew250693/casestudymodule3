package controller.users;

import model.Users;
import service.users.UsersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Servlet", urlPatterns = "/users")
public class UsersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsersService usersService;

    public void init() {
        usersService = new UsersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "signup":
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
                index(req, resp);
                break;
        }
    }

    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showSignUpForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showChangePassForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("my-account.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditProfileForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("my-account.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "signup":
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
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        String emailLog = req.getParameter("emailLog");
        String passwordLog = req.getParameter("passwordLog");
        try {
            if (usersService.login(emailLog, passwordLog)) {
                HttpSession session = req.getSession();
                session.setAttribute("emailLog", emailLog);
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher1 = req.getRequestDispatcher("login.jsp");
                dispatcher1.forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("nameRes");
        String birth = req.getParameter("birthRes");
        String emailRes = req.getParameter("emailRes");
        String phone = req.getParameter("phoneRes");
        String passwordRes = req.getParameter("passwordRes");
        String retypePass = req.getParameter("retypePass");

        if (!retypePass.equals(passwordRes)) {
            System.out.println("Mật khẩu không trùng khớp!");
        } else {
            Users users = new Users(name, birth, emailRes, phone, passwordRes);
            System.out.println(users);
            usersService.signUp(users);
            RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void changePass(HttpServletRequest req, HttpServletResponse resp) {
        String oldPass = req.getParameter("oldPass");
        String newPass = req.getParameter("newPass");
        String reptypePass = req.getParameter("reptypePass");

        if (oldPass.equals(newPass)) {
            System.out.println("Trùng mật khẩu cũ!");
        } else {
            if (!newPass.equals(reptypePass)) {
                System.out.println("Mật khẩu không trùng khớp!");
            } else {
                Users users = new Users(newPass);
                usersService.changePassword(users);
                RequestDispatcher dispatcher = req.getRequestDispatcher("my-account.jsp");
                try {
                    dispatcher.forward(req, resp);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void editProfile(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String birth = req.getParameter("birth");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        Users users = new Users(name, birth, email, phone);
        usersService.updateProfile(users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("my-account.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) {
        String abc = "login-signup";

        HttpSession session = req.getSession();
        String email = (String)session.getAttribute("emailLog");
        if (email != null) {
           abc = "Xin chào" + email;
        }
        req.setAttribute("abc", abc);

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
