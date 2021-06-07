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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        try {
            HttpSession session = req.getSession();
            session.invalidate();

            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showChangePassForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("changePass.jsp");

        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("emailLog");
        Users users = usersService.selectUserByEmail(email);
        req.setAttribute("user", users);

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditProfileForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("editProfile.jsp");

        HttpSession session = req.getSession();
        String email1 = (String) session.getAttribute("emailLog");
        Users users = usersService.selectUserByEmail(email1);
        req.setAttribute("user", users);

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

                RequestDispatcher dispatcher = req.getRequestDispatcher("index2.jsp");
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
        try {
            if (!validateEmail(emailRes)) {
                System.out.println("Email không đúng định dạng!");

                RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
                dispatcher.forward(req, resp);
            } else {
                String phone = req.getParameter("phoneRes");

                if (!validatePhone(phone)) {
                    System.out.println("Số điện thoại không đúng định dạng!");

                    RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    String passwordRes = req.getParameter("passwordRes");
                    String retypePass = req.getParameter("retypePass");


                    if (!retypePass.equals(passwordRes)) {
                        System.out.println("Mật khẩu không trùng khớp!");

                        RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        Users users = new Users(name, birth, emailRes, phone, passwordRes);
                        System.out.println(users);
                        usersService.signUp(users);

                        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                        dispatcher.forward(req, resp);
                    }
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void changePass(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String oldPass = req.getParameter("currentPass");
        String newPass = req.getParameter("newPass");
        String reptypePass = req.getParameter("confirmPass");

        if (oldPass.equals(newPass)) {
            System.out.println("Trùng mật khẩu cũ!");
        } else {
            try {
                if (!newPass.equals(reptypePass)) {
                    System.out.println("Mật khẩu không trùng khớp!");
                } else {
                    Users users = new Users(id, newPass);
                    usersService.changePassword(users);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("changePass.jsp");
                    dispatcher.forward(req, resp);
                }
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void editProfile(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("nameEdit");
        String birth = req.getParameter("birthEdit");
        String email = req.getParameter("emailEdit");
        try {
            if (!validateEmail(email)) {
                System.out.println("Email không đúng định dạng!");

                RequestDispatcher dispatcher = req.getRequestDispatcher("editProfile.jsp");
                dispatcher.forward(req, resp);
            } else {
                String phone = req.getParameter("phoneEdit");

                if (!validatePhone(phone)) {
                    System.out.println("Số điện thoại không đúng định dạng!");

                    RequestDispatcher dispatcher = req.getRequestDispatcher("editProfile.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    Users users = new Users(id, name, birth, email, phone);
                    usersService.updateProfile(users);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("editProfile.jsp");
                    dispatcher.forward(req, resp);
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index2.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateEmail(String regex) {
        String email_regex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z]+(\\.[A-Za-z]+)$";
        Pattern pattern = Pattern.compile(email_regex);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    private boolean validatePhone(String regex) {
        String email_regex = "^(84|0[3|5|7|8|9])+([0-9]{8})$";
        Pattern pattern = Pattern.compile(email_regex);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
