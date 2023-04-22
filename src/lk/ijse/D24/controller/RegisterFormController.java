package lk.ijse.D24.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.D24.bo.custom.ReservationBO;
import lk.ijse.D24.bo.custom.RoomBO;
import lk.ijse.D24.bo.custom.impl.ReservationBOImpl;
import lk.ijse.D24.bo.custom.impl.RoomBOImpl;
import lk.ijse.D24.dto.ReservationDTO;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RegisterFormController {
    public TextField txtContactNo;
    public TextField txtName;
    public ComboBox<String> cmbStudentID;
    public TextField txtDOB;
    public TextField txtAddress;
    public TextField txtGender;
    public ComboBox<String> cmbRoomID;
    public TextField txtRoomType;
    public TextField txtKeyMoney;
    public TextField txtQuntity;
    public TextField txtRegisterNo;
    public TextField txtRegisterDate;
    public TextField lblLastStudentId;
    public JFXButton btnRegister;
    public Label txtAvilability;

    RoomBO roomBO=new RoomBOImpl();
    ReservationBO reservationBO=new ReservationBOImpl();

    public void initialize(){
        try {
            loadAllStudentIDs();
            loadAllRoomIDs();
            generateNewRegisterId();
            loadDate();
            lastStudentID();
        } catch (Exception e) {
           e.printStackTrace();
        }

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{

                setStudentData(newValue);

            }catch (Exception e){
                e.printStackTrace();
            }
        });


        cmbRoomID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){

                try{

                    setRoomData(newValue);

                }catch (Exception e){
                    e.printStackTrace();
                }

                Room room = null;

                try {

                    room = roomBO.getRoom(newValue);

                } catch (SQLException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                int roomQTY = room.getQty();

                try {

                    List<ReservationDTO> reservationDTOS = reservationBO.reservedRoomByTD(newValue);

                    int registerCount = 0;
                    for (ReservationDTO reservationDTO : reservationDTOS) {
                        registerCount++;
                    }
                    int remaindQuntity = roomQTY-registerCount;

                    if(remaindQuntity == 0){
                        txtAvilability.setText("UNAVAILABLE");
                    }else{
                        txtAvilability.setText("AVAILABLE");
                    }
                } catch (SQLException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void generateNewRegisterId() {
        try{
            txtRegisterNo.setText(reservationBO.generateNewRegisterId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void lastStudentID() throws SQLException, IOException, ClassNotFoundException {
        lblLastStudentId.setText(reservationBO.lastStudentID());
    }

    private void loadDate() {
        txtRegisterDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void loadAllRoomIDs() throws SQLException, IOException, ClassNotFoundException {
        cmbRoomID.getItems().addAll(reservationBO.setRoomIDs());
    }

    private void loadAllStudentIDs() throws SQLException, IOException, ClassNotFoundException {
        cmbStudentID.getItems().addAll(reservationBO.setStudentIDs());
    }


    public void registerOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if(reservationBO.registerStudent(new ReservationDTO(
                txtRegisterNo.getText(),
                LocalDate.parse(txtRegisterDate.getText()),
                cmbStudentID.getValue(),
                cmbRoomID.getValue(),
                txtAvilability.getText()
        ))){
            new Alert(Alert.AlertType.CONFIRMATION,"Data Added Successfully...!!!").showAndWait();

        }else{
            new Alert(Alert.AlertType.WARNING,"Data Not Added ").showAndWait();
        }
        generateNewRegisterId();
        clearText();

    }
    private void setRoomData(String id) throws SQLException, ClassNotFoundException, IOException {
        Room room = reservationBO.setRoomsData(id);
        txtRoomType.setText(room.getType());
        txtKeyMoney.setText(String.valueOf(room.getKeyMoney()));
        txtQuntity.setText(String.valueOf(room.getQty()));
    }
    private void setStudentData(String id) throws SQLException, ClassNotFoundException, IOException {
        Student student = reservationBO.setStudentsData(id);
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContactNo.setText(student.getContactNo());
        txtDOB.setText(String.valueOf(student.getDob()));
        txtGender.setText(student.getGender());

    }
    private void clearText() {
        cmbRoomID.setValue(null);
        cmbStudentID.setValue(null);
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtDOB.clear();
        txtGender.clear();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtQuntity.clear();
        txtRegisterNo.getText();
        txtAvilability.setText("");
    }

}
