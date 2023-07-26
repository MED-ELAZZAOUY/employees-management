package ma.fsa.employeesmanagement.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ma.fsa.employeesmanagement.dao.EmployeeDao;
import ma.fsa.employeesmanagement.dao.JobDao;
import ma.fsa.employeesmanagement.models.Employee;
import ma.fsa.employeesmanagement.models.Job;

import java.util.Optional;

public class ActionTableCell<S> extends TableCell<S, Void> {
    private final ImageView deleteIcon;
    private final ImageView confirmIcon;

    public ActionTableCell() {
        deleteIcon = new ImageView(new Image("ma/fsa/employeesmanagement/presentation/images/icons8-delete-trash-28.png"));
        confirmIcon = new ImageView(new Image("ma/fsa/employeesmanagement/presentation/images/icons8-available-updates-27.png"));

        setGraphic(null);
        setAlignment(Pos.CENTER);

        // Add click listeners for the icons
        deleteIcon.setOnMouseClicked(event -> {
            S rowData = getTableView().getItems().get(getIndex());
            handleDeleteAction(rowData);
        });

        confirmIcon.setOnMouseClicked(event -> {
            S rowData = getTableView().getItems().get(getIndex());
            handleConfirmAction(rowData);
        });
    }

    private void handleDeleteAction(S rowData) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Job");
        alert.setContentText("Are you sure you want to delete this job?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Delete the job from the database
            JobDao jobDao = new JobDao();
            jobDao.delete((Job) rowData);

            // Remove the selected row from the TableView
            getTableView().getItems().remove(rowData);
        }
    }

    private void handleConfirmAction(S rowData) {
        if (rowData instanceof Job) {
            Job job = (Job) rowData;
            String employeeId = job.getIdEmployee();

            // Fetch the employee from the database based on the employeeId
            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = employeeDao.getById(employeeId);

            // Add the salary of the job to the employee's solde
            double newSolde = employee.getSolde() + job.getSalary();
            employee.setSolde(newSolde);

            // Update the employee's solde in the database
            employeeDao.update(employee);

            // Remove the selected job from the TableView
            getTableView().getItems().remove(job);

            // Delete the job from the database
            JobDao jobDao = new JobDao();
            jobDao.delete(job);
        }
    }


    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(new HBox(10, deleteIcon, confirmIcon));
        }
    }
}

