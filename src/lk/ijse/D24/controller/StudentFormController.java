package lk.ijse.D24.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.D24.bo.custom.StudentBO;
import lk.ijse.D24.bo.custom.impl.StudentBOImpl;
import lk.ijse.D24.dto.StudentDTO;
import lk.ijse.D24.view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public DatePicker txtDob;
    public ComboBox cmbGender;
    public TextField txtSearch;
    public TableView<StudentDTO> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableColumn colGender;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    private final StudentBO studentBO=new StudentBOImpl();


    public void initialize(){


        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadAllStudents();
        setCmbData();
        generateStudentId();


        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                setData(newValue);
            }
        });
    }

    private void generateStudentId() {
        try{
            txtId.setText(studentBO.generateNewRegisterId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setCmbData() {
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");


    }


    public void saveOnAction(ActionEvent event)  {
        try {
            studentBO.add(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(),
                    txtContact.getText(), LocalDate.parse(txtDob.getValue().toString()), cmbGender.getValue().toString()));

            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved!!!").showAndWait();
            loadAllStudents();
            clearText();
            generateStudentId();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateOnAction(ActionEvent event) {
        try{
            if (studentBO.update(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(),
                    txtContact.getText(), LocalDate.parse(txtDob.getValue().toString()), cmbGender.getValue().toString()))) {

                new Alert(Alert.AlertType.CONFIRMATION,"Data Updated Successfully...!!!").showAndWait();

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        loadAllStudents();
        clearText();
        generateStudentId();
    }

    public void deleteOnAction(ActionEvent event) {

        String id=tblStudent.getSelectionModel().getSelectedItem().getStudentId();


        try {
            studentBO.delete(id);
            new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure to Delete...?").showAndWait();
            tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
            tblStudent.getSelectionModel().clearSelection();



        }catch (Exception e) {
            e.printStackTrace();
        }
        loadAllStudents();
        generateStudentId();
        clearText();

    }

    public void clearOnAction(ActionEvent event) {
        clearText();
    }


    private void setData(StudentDTO s) {
        txtId.setText(s.getStudentId());
        txtName.setText(s.getName());
        txtAddress.setText(s.getAddress());
        txtContact.setText(s.getContactNo());
        txtDob.setValue(s.getDob());
        cmbGender.setValue(s.getGender());

    }
    private void clearText() {
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
        tblStudent.refresh();
    }


    private void loadAllStudents() {
        try{
            List<StudentDTO> allStudents = studentBO.loadAllStudents();
            ObservableList<StudentDTO> obList = FXCollections.observableArrayList();
            for (StudentDTO student : allStudents) {
                obList.add(new StudentTM(
                        student.getStudentId(),
                        student.getName(),
                        student.getAddress(),
                        student.getContactNo(),
                        student.getDob(),
                        student.getGender()
                ));
            }

            tblStudent.setItems(obList);

            FilteredList<StudentDTO> filterData = new FilteredList(obList, b -> true);

            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(StudentDTO -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (StudentDTO.getStudentId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else
                        return false;
                });
            });

            SortedList<StudentDTO> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(tblStudent.comparatorProperty());
            tblStudent.setItems(sortedData);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
