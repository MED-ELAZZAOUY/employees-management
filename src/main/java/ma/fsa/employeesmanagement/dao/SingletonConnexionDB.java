package ma.fsa.employeesmanagement.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ma.fsa.employeesmanagement.dao.entites.Job;

import java.sql.*;

public class SingletonConnexionDB{

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees_managment", "root", "");
            System.out.println("Connection Established");

            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public static ObservableList<Job>  getDataJobs(int employeeId) {
        Connection conn = ConnectDb();
        ObservableList<Job> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Job WHERE idEmployee = ?");
            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(
                        rs.getInt("idJob"),
                        rs.getString("jobName"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getInt("idEmployee")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public static boolean deleteAffectationByJobId(int empId, int jobId) {
        Connection conn = ConnectDb();

        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM affectation WHERE idemp = ? and idjob = ?");
            ps.setInt(1, empId);
            ps.setInt(2, jobId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the connection
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateJobColumn(int employeeId, String columnName, String newValue) {
        Connection conn = ConnectDb();

        try {
            PreparedStatement updateStatement = conn.prepareStatement("UPDATE job SET  = ? WHERE id_employee = ?");
            updateStatement.setString(1, newValue);
            updateStatement.setInt(2, employeeId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}