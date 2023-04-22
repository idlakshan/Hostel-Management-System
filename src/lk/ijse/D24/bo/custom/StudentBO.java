package lk.ijse.D24.bo.custom;

import lk.ijse.D24.bo.SuperBO;
import lk.ijse.D24.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean add(StudentDTO studentDTO) throws SQLException, ClassNotFoundException, IOException;

    public boolean update(StudentDTO studentDTO) throws SQLException, ClassNotFoundException, IOException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException, IOException;

    List<StudentDTO> loadAllStudents() throws SQLException, ClassNotFoundException, IOException;

    String generateNewRegisterId() throws IOException;

}
