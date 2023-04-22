package lk.ijse.D24.bo.custom.impl;

import lk.ijse.D24.bo.custom.ReservationBO;
import lk.ijse.D24.dao.custom.ReservationDAO;
import lk.ijse.D24.dao.custom.RoomDAO;
import lk.ijse.D24.dao.custom.StudentDAO;
import lk.ijse.D24.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.D24.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24.dao.custom.impl.StudentDAOImpl;
import lk.ijse.D24.dto.ReservationDTO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    StudentDAO studentDAO=new StudentDAOImpl();
    RoomDAO roomDAO=new RoomDAOImpl();
    ReservationDAO reservationDAO=new ReservationDAOImpl();



    @Override
    public List setStudentIDs() throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.setRoomIDs();
    }

    @Override
    public List setRoomIDs() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.setRoomIDs();
    }

    @Override
    public Student setStudentsData(String id) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.find(id);
    }

    @Override
    public Room setRoomsData(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.find(id);
    }

    @Override
    public String generateNewRegisterId() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.generateNewId();
    }

    @Override
    public boolean registerStudent(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.add(new Reservation(
                reservationDTO.getRegisterID(),
                reservationDTO.getDate(),
                new Student(reservationDTO.getStudentID()),
                new Room(reservationDTO.getRoomID()),
                reservationDTO.getStatus()
        ));
    }

    @Override
    public List<ReservationDTO> reservedRoomByTD(String id) throws SQLException, ClassNotFoundException, IOException {
        List<Reservation> reserves = reservationDAO.searchReservedRoomById(id);

        List<ReservationDTO> reserveDTOS=new ArrayList<>();

        for (Reservation reserve : reserves) {
            reserveDTOS.add(new ReservationDTO(
                    reserve.getRegisterID(),
                    reserve.getDate(),
                    reserve.getStudent().getStudentId(),
                    reserve.getRoom().getRoomId(),
                    reserve.getStatus()
            ));

        }
        return reserveDTOS;
    }

    @Override
    public String lastStudentID() throws IOException {
        return studentDAO.lastStudentID();
    }
}
