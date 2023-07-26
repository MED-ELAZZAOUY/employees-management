package ma.fsa.employeesmanagement.dao;

import ma.fsa.employeesmanagement.models.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDao implements Dao<Admin> {
    private Connection connection;

    public AdminDao() {
        connection = SingletonConnexionDB.getConnection();
    }

    public boolean getByUserName(String userName, String passWord){
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Admin WHERE userName = ? and passWord = ? ");
            ps.setString(1, userName);
            ps.setString(2, passWord);

            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
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
    public List<Admin> getAll() {
        return null;
    }

    @Override
    public Admin getById(String id) {
        return null;
    }

    @Override
    public void save(Admin o) {

    }

    @Override
    public boolean delete(Admin o) {
        return false;
    }

    @Override
    public Admin update(Admin o) {
        return null;
    }
}
