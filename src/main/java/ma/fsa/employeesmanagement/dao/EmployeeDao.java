package ma.fsa.employeesmanagement.dao;

import ma.fsa.employeesmanagement.models.Employee;
import ma.fsa.employeesmanagement.models.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements Dao<Employee>{
    private Connection connection;

    public EmployeeDao() {
        connection = SingletonConnexionDB.getConnection();
    }
    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Employee");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("CIN"),
                        rs.getDouble("solde")

                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }

    @Override
    public Employee getById(int id) {
        return null;
    }

    @Override
    public void save(Employee o) {

    }

    @Override
    public boolean delete(Employee o) {
        return false;
    }

    @Override
    public Employee update(Employee o) {
        return null;
    }
}
