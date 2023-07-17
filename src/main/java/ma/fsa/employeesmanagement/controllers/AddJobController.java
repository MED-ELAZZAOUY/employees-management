package ma.fsa.employeesmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ma.fsa.employeesmanagement.dao.Dao;
import ma.fsa.employeesmanagement.dao.EmployeeDao;
import ma.fsa.employeesmanagement.dao.JobDao;
import ma.fsa.employeesmanagement.models.Employee;
import ma.fsa.employeesmanagement.models.Job;

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
    private ComboBox idEmployeeComboBox;

    @FXML
    private Button addJobButton;

    private Dao<Job> jobDao;
    private Dao<Employee> employeeDao;

    public AddJobController() {
        //  (we can also inject it using a dependency injection framework)
        jobDao = new JobDao();
        employeeDao = new EmployeeDao();
    }
    @FXML
    void handleAddJob(MouseEvent event) throws IOException {
        if (event.getSource() != addJobButton)
            return;

        //String idJob = idField.getText();
        String name = nameField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        String description = descriptionField.getText();
        String idEmployee = (String) idEmployeeComboBox.getSelectionModel().getSelectedItem();
        System.out.println(idEmployee);

        Job job = new Job(name, description, salary, idEmployee);


        jobDao.save(job);

        // Clear the input fields after adding the job
        idField.clear();
        nameField.clear();
        salaryField.clear();
        descriptionField.clear();
        //idEmployeeComboBox.clear();

        // openMainMenu("../presentation/views/addJobView.fxml");
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
        employeeDao.getAll().forEach(aClass ->{
            idEmployeeComboBox.getItems().add(aClass.getLastName()+","+aClass.getCIN());
        });

    }

}

