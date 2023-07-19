package ma.fsa.employeesmanagement.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ma.fsa.employeesmanagement.dao.EmployeeDao;
import ma.fsa.employeesmanagement.dao.JobDao;
import ma.fsa.employeesmanagement.models.Employee;
import ma.fsa.employeesmanagement.models.Job;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class JobsEmployeeController implements Initializable {
    //-- employee
    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> idEmployeeTabColumn;

    @FXML
    private TableColumn<Employee, String> firstNameTabColumn;

    @FXML
    private TableColumn<Employee, String> lastNameTabColumn;

    @FXML
    private TableColumn<Employee, String> CINTabColumn;

    @FXML
    private TableColumn<Employee, Double> soldeTabColumn;

    // ----- jobs
    @FXML
    private TableView<Job> jobsTableView;

    @FXML
    private TableColumn<Job, String> idJobTabColumn;

    @FXML
    private TableColumn<Job, String> jobNameTabColumn;

    @FXML
    private TableColumn<Job, Double> salaryTabColumn;

    @FXML
    private TableColumn<Job, Void> actionTabColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up table columns for Employee TableView
        idEmployeeTabColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdEmployee()));
        firstNameTabColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        lastNameTabColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        CINTabColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCIN()));
        soldeTabColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getSolde()).asObject());

        // Set up table columns for Job TableView
        idJobTabColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdJob()));
        jobNameTabColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getJobName()));
        salaryTabColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getSalary()).asObject());

        // Fetch data from the database and populate the TableView
        populateEmployeeTableView();
        populateJobsTableView();
    }

    private void populateEmployeeTableView() {
        EmployeeDao employeeDao = new EmployeeDao();
        //List<Employee> employee = employeeDao.getAll();
        Employee employee = employeeDao.getById("abcd");
        employeeTableView.getItems().add(employee);
    }

    private void populateJobsTableView() {
        JobDao jobDao = new JobDao();
        List<Job> jobs = jobDao.getJobs("abcd");
        jobsTableView.getItems().addAll(jobs);
    }
}
