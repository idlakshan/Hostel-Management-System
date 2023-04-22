package lk.ijse.D24.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.D24.bo.custom.RoomBO;
import lk.ijse.D24.bo.custom.impl.RoomBOImpl;
import lk.ijse.D24.dto.RoomDTO;
import lk.ijse.D24.view.tm.RoomTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class RoomsFormController {
    public TextField txtId;
    public TextField txtKeyMoney;
    public TextField txtQty;
    public ComboBox cmbRoomType;
    public TextField txtSearch;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public TableView<RoomDTO> tblRoom;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQTY;

    RoomBO roomBO=new RoomBOImpl();


    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));


        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData(newValue);
            }
        });

        cmbLoad();
        loadAllRoom();
        generateRoomId();
    }

    private void generateRoomId() {
        try{
            txtId.setText(roomBO.generateNewRegisterId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setData(RoomDTO newValue) {
        txtId.setText(newValue.getRoomId());
        cmbRoomType.setValue(newValue.getType());
        txtKeyMoney.setText(String.valueOf(newValue.getKeyMoney()));
        txtQty.setText(String.valueOf(newValue.getQty()));
    }

    private void cmbLoad() {
        cmbRoomType.getItems().add("AC");
        cmbRoomType.getItems().add("NON-AC");
        cmbRoomType.getItems().add("AC/Food");
        cmbRoomType.getItems().add("NON-AC/Food");
    }

    public void saveOnAction(ActionEvent event){
        try {
            if( roomBO.add(new RoomDTO(
                    txtId.getText(), cmbRoomType.getValue().toString(), Double.parseDouble(txtKeyMoney.getText()),
                    Integer.parseInt(txtQty.getText())))){

                new Alert(Alert.AlertType.CONFIRMATION,"Room Added Successfully...!!!").showAndWait();

            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the Room!!!" + e.getMessage()).showAndWait();
        }
        loadAllRoom();
        clearText();
        generateRoomId();


    }

    private void clearText() {
        cmbRoomType.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtQty.clear();
        tblRoom.refresh();
    }

    private void loadAllRoom() {
        try{
            List<RoomDTO> allRooms = roomBO.loadAllRooms();
            ObservableList<RoomDTO> obList = FXCollections.observableArrayList();
            for (RoomDTO room : allRooms) {
                obList.add(new RoomTM(
                        room.getRoomId(),
                        room.getType(),
                        room.getKeyMoney(),
                        room.getQty()
                ));
            }

            tblRoom.setItems(obList);

            FilteredList<RoomDTO> filterData = new FilteredList(obList, b -> true);

            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(RoomDTO -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (RoomDTO.getRoomId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else
                        return false;
                });
            });

            SortedList<RoomDTO> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(tblRoom.comparatorProperty());
            tblRoom.setItems(sortedData);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void updateOnAction(ActionEvent event) {
        try{
            if (roomBO.update(new RoomDTO(
                    txtId.getText(), cmbRoomType.getValue().toString(), Double.parseDouble(txtKeyMoney.getText()), Integer.parseInt(txtQty.getText())))) {

                new Alert(Alert.AlertType.CONFIRMATION,"Data Updated Successfully...!!!").showAndWait();

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        loadAllRoom();
        clearText();
        generateRoomId();

    }

    public void deleteOnAction(ActionEvent event) {
        String id = tblRoom.getSelectionModel().getSelectedItem().getRoomId();

        try {
            roomBO.delete(id);
            tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
            tblRoom.getSelectionModel().clearSelection();

            new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure...?").showAndWait();

        }catch (Exception e) {
            e.printStackTrace();
        }
        loadAllRoom();
        generateRoomId();
        clearText();
    }

    public void clearOnAction(ActionEvent event) {
      clearText();
    }
}
