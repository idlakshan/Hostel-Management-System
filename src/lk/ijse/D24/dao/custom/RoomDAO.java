package lk.ijse.D24.dao.custom;

import lk.ijse.D24.dao.SuperDAO;
import lk.ijse.D24.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface RoomDAO extends SuperDAO<Room,String> {
    String generateNewId() throws IOException;

    List setRoomIDs() throws SQLException, ClassNotFoundException, IOException;

}
