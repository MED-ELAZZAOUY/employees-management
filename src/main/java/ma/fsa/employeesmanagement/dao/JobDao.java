package ma.fsa.employeesmanagement.dao;

import ma.fsa.employeesmanagement.models.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JobDao implements Dao<Job>{
    private Connection connection;

    public JobDao() {
        connection = SingletonConnexionDB.getConnection();
    }
    @Override
    public List<Job> getAll() {
        List<Job> jobs = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Job");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job(
                        //rs.getString("idJob"),
                        rs.getString("jobName"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getString("idEmployee")
                );
                jobs.add(job);
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

        return jobs;
    }

    @Override
    public Job getById(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Job WHERE idJob = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Job(
                        //rs.getString("idJob"),
                        rs.getString("jobName"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getString("idEmployee")
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
    public void save(Job job) {
        try {
            //System.out.println(" info Job : "+job.getIdJob()+" "+ job.getIdEmployee());
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Job (idJob, jobName, description, salary, idEmployee) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, job.getIdJob());
            ps.setString(2, job.getJobName());
            ps.setString(3, job.getDescription());
            ps.setDouble(4, job.getSalary());
            ps.setString(5, job.getIdEmployee());

            ps.executeUpdate();

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
    public boolean delete(Job job) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Job WHERE idJob = ?");
            ps.setString(1, job.getIdJob());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
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
    public Job update(Job job) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Job SET jobName = ?, description = ?, salary = ?, idEmployee = ? WHERE idJob = ?");
            ps.setString(1, job.getJobName());
            ps.setString(2, job.getDescription());
            ps.setDouble(3, job.getSalary());
            ps.setString(4, job.getIdEmployee());
            ps.setString(5, job.getIdJob());

            ps.executeUpdate();

            return job;
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

}
