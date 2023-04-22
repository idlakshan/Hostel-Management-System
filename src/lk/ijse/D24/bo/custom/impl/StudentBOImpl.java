package lk.ijse.D24.bo.custom.impl;

import lk.ijse.D24.bo.custom.StudentBO;
import lk.ijse.D24.dao.custom.StudentDAO;
import lk.ijse.D24.dao.custom.impl.StudentDAOImpl;
import lk.ijse.D24.dto.StudentDTO;
import lk.ijse.D24.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO=new StudentDAOImpl();

    @Override
    public boolean add(StudentDTO studentDTO) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.add(new Student(
                studentDTO.getStudentId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getDob(),
                studentDTO.getGender()
        ));

    }

    @Override
    public boolean update(StudentDTO studentDTO) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.update(
                new Student(
                        studentDTO.getStudentId(),
                        studentDTO.getName(),
                        studentDTO.getAddress(),
                        studentDTO.getContactNo(),
                        studentDTO.getDob(),
                        studentDTO.getGender()
                ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.delete(id);
    }

    @Override
    public List<StudentDTO> loadAllStudents() throws SQLException, ClassNotFoundException, IOException {
        List<Student> students = studentDAO.loadAll();
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        for (Student student : students) {
            arrayList.add(new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getContactNo(),
                    student.getDob(),
                    student.getGender()
            ));
        }
        return arrayList;
    }

    @Override
    public String generateNewRegisterId() throws IOException {
        return studentDAO.generateNewId();
    }
}
