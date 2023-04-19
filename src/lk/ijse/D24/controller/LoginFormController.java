package lk.ijse.D24.controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LoginFormController {
    public TextField txtUserName;
    public PasswordField txtHidePassword;
    public TextField txtShowPassword;
    public ImageView lblCloseEye;
    public ImageView lblOpenEye;

    String password;


    public void initialize(){
        txtShowPassword.setVisible(false);
        lblOpenEye.setVisible(false);


    }

    public void hidePasswordOnAction(KeyEvent keyEvent) {
        password=txtHidePassword.getText();
        txtShowPassword.setText(password);
    }

    public void closeEyeOnClickAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(true);
        lblOpenEye.setVisible(true);
        txtHidePassword.setVisible(false);
        lblCloseEye.setVisible(false);
    }

    public void showPasswordOnAction(KeyEvent keyEvent) {
        password = txtShowPassword.getText();
        txtHidePassword.setText(password);
    }

    public void openEyeOnClickAction(MouseEvent mouseEvent) {
        txtHidePassword.setVisible(true);
        lblCloseEye.setVisible(true);
        txtShowPassword.setVisible(false);
        lblOpenEye.setVisible(false);
    }
}
