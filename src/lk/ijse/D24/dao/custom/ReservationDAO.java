package lk.ijse.D24.dao.custom;

import lk.ijse.D24.dao.SuperDAO;
import lk.ijse.D24.entity.Reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO extends SuperDAO<Reservation,String> {
    String generateNewId() throws SQLException, ClassNotFoundException, IOException;

    List<Reservation> searchReservedRoomById(String id) throws IOException;

}
