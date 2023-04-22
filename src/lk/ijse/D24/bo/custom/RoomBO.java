package lk.ijse.D24.bo.custom;

import lk.ijse.D24.bo.SuperBO;
import lk.ijse.D24.dto.RoomDTO;
import lk.ijse.D24.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    public boolean add(RoomDTO roomDTO) throws SQLException, ClassNotFoundException, IOException;

    public boolean update(RoomDTO roomDTO) throws SQLException, ClassNotFoundException, IOException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException, IOException;

    List<RoomDTO> loadAllRooms() throws SQLException, ClassNotFoundException, IOException;

    public Room getRoom(String id) throws SQLException, ClassNotFoundException, IOException;

    String generateNewRegisterId() throws IOException;

}
