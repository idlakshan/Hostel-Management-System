package lk.ijse.D24.dao.custom;

import lk.ijse.D24.entity.Custom;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RegisterDetailsDAO {
    List<Custom> loadAllStudentData(String id) throws SQLException, ClassNotFoundException, IOException;
}
