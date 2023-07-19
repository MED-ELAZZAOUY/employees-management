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
                        rs.getString("idEmployee"),
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
    public Employee getById(String idEmployee) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Employee WHERE idEmployee = ?");
            ps.setString(1, idEmployee);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getString("idEmployee"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("CIN"),
                        rs.getDouble("solde")
                );
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

        return null;
    }

    @Override
    public Employee update(Employee employee) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Employee SET firstName = ?, lastName = ?, CIN = ?, solde = ? WHERE idEmployee = ?");
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getCIN());
            ps.setDouble(4, employee.getSolde());
            ps.setString(5, employee.getIdEmployee());

            ps.executeUpdate();

            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void save(Employee o) {

    }

    @Override
    public boolean delete(Employee o) {
        return false;
    }


}
