package lk.ijse.D24.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.D24.bo.custom.RegisterDetailsBO;
import lk.ijse.D24.bo.custom.impl.RegisterDetailsBOImpl;
import lk.ijse.D24.entity.Custom;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.view.tm.RegisterDetailsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DetailsReportFormController {
    public TableView tblRegisterDetails;
    public TableColumn colRegId;
    public TableColumn colRegDate;
    public TableColumn colSId;
    public TableColumn colSName;
    public TableColumn colContact;
    public TextField txtRoomType;
    public TextField txtKeyMoney;
    public TextField txtQty;
    public ComboBox<String> cmbRoomId;

    RegisterDetailsBO registerDetailsBO=new RegisterDetailsBOImpl();

    public void initialize(){
        colRegId.setCellValueFactory(new PropertyValueFactory<>("registerID"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        try{
            loadAllRoomIDs();
        }catch(Exception e){
            e.printStackTrace();
        }

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {

                    setRoomData(newValue);
                    loadStudentData(newValue);
                    tblRegisterDetails.refresh();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadStudentData(String id) throws SQLException, IOException, ClassNotFoundException {
        ObservableList<Custom> list = FXCollections.observableArrayList();
        List<Custom> customs = registerDetailsBO.loadAllStudentData(id);
        for (Custom custom : customs) {
            RegisterDetailsTM registerDetailsTM = new RegisterDetailsTM(
                    custom.getRegisterID(),
                    custom.getDate(),
                    custom.getStudentId(),
                    custom.getName(),
                    custom.getContactNo()
            );
            list.add(custom);
            tblRegisterDetails.setItems(list);
            tblRegisterDetails.refresh();
        }

    }

    private void setRoomData(String id) throws SQLException, IOException, ClassNotFoundException {
        Room room = registerDetailsBO.setRoomData(id);
        txtRoomType.setText(room.getType());
        txtKeyMoney.setText(String.valueOf(room.getKeyMoney()));
        txtQty.setText(String.valueOf(room.getQty()));
    }

    private void loadAllRoomIDs() throws SQLException, IOException, ClassNotFoundException {
        cmbRoomId.getItems().addAll(registerDetailsBO.loadRoomIDs());
    }




}
