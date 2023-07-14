package ma.fsa.employeesmanagement.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {
    @FXML
    private JFXButton checkEmpButton;

    @FXML
    private JFXButton addJobButton;



    @FXML
    void handleAddJob(MouseEvent event) throws IOException {
        if (event.getSource() != addJobButton)
            return;

        openMainMenu("../presentation/views/AddJobView.fxml");
    }

    @FXML
    private void hanleCheckEmployee(MouseEvent event) throws IOException {
        if (event.getSource() != checkEmpButton)
            return;

        openMainMenu("../presentation/views/EmployeeLoginView.fxml");
    }


    private void openMainMenu(String link) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(link));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
        stage.show();
        addJobButton.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) { }
}
