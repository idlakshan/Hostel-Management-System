package lk.ijse.D24.bo.custom.impl;

import lk.ijse.D24.bo.custom.RegisterDetailsBO;
import lk.ijse.D24.dao.custom.RegisterDetailsDAO;
import lk.ijse.D24.dao.custom.RoomDAO;
import lk.ijse.D24.dao.custom.impl.RegisterDetailsDAOImpl;
import lk.ijse.D24.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24.entity.Custom;
import lk.ijse.D24.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDetailsBOImpl implements RegisterDetailsBO {

    RoomDAO roomDAO = new RoomDAOImpl();
    RegisterDetailsDAO registerDetailsDAO=new RegisterDetailsDAOImpl();

    @Override
    public List loadRoomIDs() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.setRoomIDs();
    }

    @Override
    public Room setRoomData(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.find(id);
    }

    @Override
    public List<Custom> loadAllStudentData(String id) throws SQLException, ClassNotFoundException, IOException {
        List<Custom> list = registerDetailsDAO.loadAllStudentData(id);
        ArrayList<Custom> customArrayList = new ArrayList<>();

        for (Custom studentDetails : list) {
            customArrayList.add(studentDetails);
        }
        return customArrayList;
    }
}
