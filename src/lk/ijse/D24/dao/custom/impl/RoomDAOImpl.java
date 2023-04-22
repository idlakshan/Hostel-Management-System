package lk.ijse.D24.dao.custom.impl;

import lk.ijse.D24.dao.custom.RoomDAO;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean add(Room room) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room room) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.load(Room.class, s);
        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room find(String s) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, s);

        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public List<Room> loadAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Room> list = null;
        Query from_room = session.createQuery("FROM Room");
        list = from_room.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String generateNewId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT roomId FROM room ORDER BY roomId DESC LIMIT 1");

        String id = (String)(sqlQuery.uniqueResult());
        int newRegisterId = Integer.parseInt(id.replace("R-", "")) + 1;
        return String.format("R-%03d", newRegisterId);
    }

    @Override
    public List setRoomIDs() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<String> rId = new ArrayList<>();
        Query select_roomId_from_room = session.createQuery("SELECT roomId FROM Room");
        rId = select_roomId_from_room.list();
        transaction.commit();
        session.close();
        return rId;
    }
}
