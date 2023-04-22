package lk.ijse.D24.controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lk.ijse.D24.bo.custom.ReservationBO;
import lk.ijse.D24.bo.custom.RoomBO;
import lk.ijse.D24.bo.custom.StudentBO;
import lk.ijse.D24.bo.custom.impl.ReservationBOImpl;
import lk.ijse.D24.bo.custom.impl.RoomBOImpl;
import lk.ijse.D24.bo.custom.impl.StudentBOImpl;
import lk.ijse.D24.dto.ReservationDTO;
import lk.ijse.D24.dto.StudentDTO;
import lk.ijse.D24.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class HomePageFormController {
    public Label lblStudentCount;
    public ComboBox<String> cmbRoomIds;
    public Label lblAllRooms;
    public Label lblRoomReceivedCount;
    public Label lblAvailableCount;

    StudentBO studentBO=new StudentBOImpl();
    RoomBO roomBO=new RoomBOImpl();
    ReservationBO reservationBO=new ReservationBOImpl();


    public void initialize(){
        try {
            loadStudentCount();
            loadAllDashBoardLabels();
            setRoomIDs();

        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbRoomIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){
                Room room = null;

                try {

                    room = roomBO.getRoom(newValue);

                } catch (SQLException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                int roomQTY = room.getQty();
                lblAllRooms.setText(String.valueOf(roomQTY));

                try {

                    List<ReservationDTO> reservationDTOS = reservationBO.reservedRoomByTD(newValue);

                    int registerCount = 0;
                    for (ReservationDTO reservationDTO : reservationDTOS) {
                        registerCount++;
                    }
                    int remaindQuntity = roomQTY-registerCount;
                    lblRoomReceivedCount.setText(String.valueOf(registerCount));
                    lblAvailableCount.setText(String.valueOf(remaindQuntity));

                } catch (SQLException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }



    private void setRoomIDs() throws SQLException, IOException, ClassNotFoundException {
        cmbRoomIds.getItems().addAll(reservationBO.setRoomIDs());

    }

    private void loadAllDashBoardLabels() throws SQLException, IOException, ClassNotFoundException {
        int studentCount = 0;
        for (StudentDTO loadAllStudent : studentBO.loadAllStudents()) {
            studentCount++;

        }
        lblStudentCount.setText(String.valueOf(studentCount));
    }

    private void loadStudentCount() throws SQLException, IOException, ClassNotFoundException {
        int studentCount = 0;
        for (StudentDTO loadAllStudent : studentBO.loadAllStudents()) {
            studentCount++;

        }
        lblStudentCount.setText(String.valueOf(studentCount));
    }

}
