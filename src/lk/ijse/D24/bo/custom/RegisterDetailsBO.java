package lk.ijse.D24.bo.custom;

import lk.ijse.D24.bo.SuperBO;
import lk.ijse.D24.entity.Custom;
import lk.ijse.D24.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RegisterDetailsBO extends SuperBO {
    List loadRoomIDs() throws SQLException, ClassNotFoundException, IOException;
    Room setRoomData(String id) throws SQLException, ClassNotFoundException, IOException;
    List<Custom> loadAllStudentData(String id) throws SQLException, ClassNotFoundException, IOException;

}
