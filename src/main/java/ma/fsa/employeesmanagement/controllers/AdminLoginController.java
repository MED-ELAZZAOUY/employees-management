package ma.fsa.employeesmanagement.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ma.fsa.employeesmanagement.dao.AdminDao;
import ma.fsa.employeesmanagement.dao.Dao;
import ma.fsa.employeesmanagement.models.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminLoginController implements Initializable {
    @FXML
    private JFXButton loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private AdminDao adminDao;

    public AdminLoginController(){
        adminDao = new AdminDao();
    }

    @FXML
    private void handleLogin(MouseEvent event) throws IOException {
        if (event.getSource() != loginButton) {
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showWarningAlert("Please fill in all the fields!");
            return;
        }

        if (authenticateUser(username, password)) {
            openMainMenu();
        } else {
            showErrorAlert("Username or password is wrong!");
            clearInputFields();
        }
    }

    private boolean authenticateUser(String username, String password) {
        return adminDao.getByUserName(username, password);
    }

    private void openMainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../presentation/views/MenuView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
        stage.show();
        // Close the login window
        usernameField.getScene().getWindow().hide();
    }

    private void showWarningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    private void clearInputFields() {
        usernameField.clear();
        passwordField.clear();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) { }
}
