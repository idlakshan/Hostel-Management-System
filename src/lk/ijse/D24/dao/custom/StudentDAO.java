package lk.ijse.D24.dao.custom;

import lk.ijse.D24.dao.SuperDAO;
import lk.ijse.D24.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends SuperDAO<Student,String> {
    List setRoomIDs() throws SQLException, ClassNotFoundException, IOException;

    String lastStudentID()throws IOException;

    String generateNewId() throws IOException;

}
