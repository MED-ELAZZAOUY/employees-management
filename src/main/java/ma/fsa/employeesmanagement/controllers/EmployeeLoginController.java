package ma.fsa.employeesmanagement.controllers;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeLoginController implements Initializable {
    @FXML
    private TextField codePINField;
    @FXML
    private JFXButton loginButton;

    public void handleLogin(MouseEvent event) throws IOException {
        if (event.getSource() != loginButton)
            return;

        String codePIN = codePINField.getText();

        if (codePIN.isEmpty()) {
            showWarningAlert("please enter your PIN code!");
            return;
        }

        if (authenticateUser(codePIN)) {
            openMainMenu();
        } else {
            showErrorAlert("PIN code is wrong!");
            clearInputFields();
        }
    }

    private boolean authenticateUser(String pin) {
        return pin.equals("admin");
    }

    private void openMainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../presentation/views/JobsEmployeeView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
        stage.show();
        // Close the login window
        codePINField.getScene().getWindow().hide();
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
        codePINField.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
