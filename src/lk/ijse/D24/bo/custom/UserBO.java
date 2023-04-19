package lk.ijse.D24.bo.custom;

import lk.ijse.D24.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO{

    List<UserDTO> loadAllUsers() throws SQLException, ClassNotFoundException, IOException;
}
