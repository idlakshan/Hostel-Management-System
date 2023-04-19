package lk.ijse.D24.dao.custom.impl;

import lk.ijse.D24.dao.custom.UserDAO;
import lk.ijse.D24.entity.User;
import lk.ijse.D24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> list = null;
        Query from_user = session.createQuery("FROM User");
        list = from_user.list();

        transaction.commit();
        session.close();

        return list;
    }
}
