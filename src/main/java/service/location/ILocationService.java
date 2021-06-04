package service.location;

import model.Book;
import model.Location;

import java.sql.SQLException;
import java.util.List;

public interface ILocationService {
    void insertLocation(Location location);

    Location selectLocation(int id);

    boolean deleteLocation(int id) throws SQLException;

    boolean updateLocation(Location location) throws SQLException;

    List<Location> selectAllLocation();
}
