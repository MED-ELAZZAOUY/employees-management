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
import ma.fsa.employeesmanagement.javaCard.EmployeeTask;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmployeeLoginController implements Initializable {
    @FXML
    private TextField codePINField;
    @FXML
    private JFXButton loginButton;
    private final byte[] entryPin = new byte[4];
    private int indexByte = 0;

    public void handleLogin(MouseEvent event) throws IOException {
        if (event.getSource() != loginButton)
            return;

        String codePIN = codePINField.getText();

        if (codePIN.length() == 4) {
            for (int i = 0; i < 4; i++) {
                try {
                    entryPin[i] = Byte.parseByte(codePIN.substring(i, i + 1));
                } catch (NumberFormatException ignored) {
                }
            }
        } else {
            if (codePIN.isEmpty()) {
                showWarningAlert("please enter your PIN code!");
                return;
            }else {
                showWarningAlert("Invalid PIN code length: " + codePIN.length());
                return;
            }
        }

        EmployeeTask validatePINTask = new EmployeeTask();
        ExecutorService executorService = Executors.newCachedThreadPool();//  handle multiple tasks concurrently, and threads will be created or reused as needed based on the task workload.
        System.out.println(Arrays.toString(entryPin));
        validatePINTask.setEntryPin(entryPin);
        System.out.println(Arrays.toString(entryPin));

        executorService.submit(validatePINTask); ///
        StringBuilder maskedPinBuilder = new StringBuilder();
        for (int i = 0; i < indexByte; i++) {
            maskedPinBuilder.append(entryPin[i]); // create a masked version of the entered PIN
        }

        System.out.println("begin task in class");
        System.out.println("pin = " + maskedPinBuilder);

        validatePINTask.setOnSucceeded((event1) -> { //  methode call
            System.out.println("begin task iscucces");
            if (validatePINTask.getValue()) {
                try {
                    openMainMenu();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                showErrorAlert("PIN code is wrong!");
                clearInputFields();
            }
        });

        /*
        if (authenticateUser(codePIN)) {
            openMainMenu();
        } else {
            showErrorAlert("PIN code is wrong!");
            clearInputFields();
        }*/
    }

    /*
    private boolean authenticateUser(String pin) {
        return pin.equals("admin");
    }

     */

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
