package lk.ijse.D24.dao.custom.impl;

import lk.ijse.D24.dao.custom.ReservationDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public boolean add(Reservation reservation) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(reservation);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation reservation) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public Reservation find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Reservation> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT registerID FROM Reservation ORDER BY registerID DESC LIMIT 1");

        String id = (String)(sqlQuery.uniqueResult());
        int newRegisterId = Integer.parseInt(id.replace("REG-", "")) + 1;
        return String.format("REG-%03d", newRegisterId);
    }

    public List<Reservation> searchReservedRoomById (String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reservation WHERE room = :roomId";
        Query query = session.createQuery(hql);

        Room room = new Room();
        room.setRoomId(id);

        query.setParameter("roomId", room);
        List<Reservation> r = query.list();

        transaction.commit();
        session.close();

        return r;
    }
}
