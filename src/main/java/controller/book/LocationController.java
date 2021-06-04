package controller.book;


import model.Location;

import service.location.LocationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LocationController", value = "/location")
public class LocationController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LocationService locationService;

    public void init() {
        locationService = new LocationService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                    deleteLocation(request, response);
                    break;
                default:
                    listLocation(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Location> locationList = locationService.selectAllLocation();
        request.setAttribute("listLocation", locationList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("locationlist.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("locationcreate.jsp");
        dispatcher.forward(request, response);
    }

        private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Location locationList = locationService.selectLocation(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("locationedit.jsp");
        request.setAttribute("location", locationList);

        dispatcher.forward(request, response);
    }

    private void deleteLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        locationService.deleteLocation(id);
        List<Location> locationList = locationService.selectAllLocation();
        request.setAttribute("listLocation", locationList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("locationlist.jsp");
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
                    insertLocation(request, response);
                    break;
                case "edit":
                    updateLocation(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }



    private void insertLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String book_amount = request.getParameter("amount");
        String book_quanity = request.getParameter("quanity");

        Location l = new Location(name, description, book_amount, book_quanity);
        locationService.insertLocation(l);
        System.out.println(l);
        RequestDispatcher dispatcher = request.getRequestDispatcher("locationcreate.jsp");
        dispatcher.forward(request, response);
    }

    private void updateLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String book_amount = request.getParameter("amount");
        String book_quantity = request.getParameter("quantity");

        Location location = new Location(id, name, description, book_amount, book_quantity);
        locationService.updateLocation(location);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-location.jsp");
        dispatcher.forward(request, response);
    }
}
