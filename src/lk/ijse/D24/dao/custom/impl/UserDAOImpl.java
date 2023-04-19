package lk.ijse.D24.dao.custom.impl;

import lk.ijse.D24.dao.custom.UserDAO;
import lk.ijse.D24.entity.User;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public User find(String s) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public List<User> loadAll() throws SQLException, ClassNotFoundException, IOException {

        return null;
    }
}
