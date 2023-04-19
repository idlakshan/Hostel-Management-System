package lk.ijse.D24.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DashboardFormController {
    public AnchorPane dashboardContext;
    public Button btnStudent;
    public Button btnRoom;
    public Button btnBooking;
    public Button btnReport;
    public AnchorPane loadFormContext;
    public Button btnDashboard;
    public Button btnLogout;

    public void initialize() throws IOException {
        setUi("HomePageForm");
    }


    public void studentOnAction(ActionEvent event) throws IOException {
        setUi("StudentForm");

    }

    public void roomOnAction(ActionEvent event) {
    }

    public void bookingOnAction(ActionEvent event) {
    }

    public void reportOnAction(ActionEvent event) {
    }

    public void dashboardOnAction(ActionEvent event) throws IOException {
           setUi("HomePageForm");
    }


    public void logoutOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure to logout...?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            Stage stage = (Stage) dashboardContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
            stage.centerOnScreen();
        }

    }

    public void setUi(String path) throws IOException {
        loadFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+path+".fxml"));
        loadFormContext.getChildren().add(parent);
    }


}
