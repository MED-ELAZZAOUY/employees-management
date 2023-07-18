package ma.fsa.employeesmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import ma.fsa.employeesmanagement.utils.Option;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;


public class AddJobController implements Initializable {
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

    private Option selectedOption;

    public AddJobController() {
        employeeDao = new EmployeeDao();
        //  (we can also inject it using a dependency injection framework)
        jobDao = new JobDao();
    }
    @FXML
    private void handleAddJob(MouseEvent event) throws IOException {
        if (event.getSource() != addJobButton)
            return;

        String name = nameField.getText();
        String salaryText = salaryField.getText();
        String description = descriptionField.getText();
        String idEmployee = null;

        double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            return;
        }

        if (selectedOption != null) {
            idEmployee = selectedOption.getId();
        } else {
            showWarningAlert("Please select Employee!");
            return;
        }

        System.out.println("idEmployee : "+ idEmployee);
        Job job = new Job(UUID.randomUUID().toString(), name, description, salary, idEmployee);

        System.out.println(job);
        jobDao.save(job);

        // Clear the input fields after adding the job
        nameField.clear();
        salaryField.clear();
        descriptionField.clear();
        //idEmployeeComboBox.clear();

        // openMainMenu("../presentation/views/addJobView.fxml");
    }

    private void showWarningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
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
            idEmployeeComboBox.getItems().add(new Option(aClass.getIdEmployee(), aClass.getLastName()+", "+aClass.getCIN()));
        });
        idEmployeeComboBox.setOnAction(e ->{
             selectedOption = (Option) idEmployeeComboBox.getValue();

            if (selectedOption != null) {
                System.out.println("Selected Employee Id : " + selectedOption.getId());
            }
        });

    }

}

