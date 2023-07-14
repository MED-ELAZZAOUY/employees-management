package ma.fsa.employeesmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddJobController implements Initializable {
    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button addJobButton;

    @FXML
    void handleAddJob(MouseEvent event) throws IOException {
        if (event.getSource() != addJobButton)
            return;

        openMainMenu("../presentation/views/addJobView.fxml");
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
    public void initialize(URL url, ResourceBundle rb) {
    }    

    
}
