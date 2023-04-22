package lk.ijse.D24.dao.custom.impl;

import lk.ijse.D24.dao.custom.RegisterDetailsDAO;
import lk.ijse.D24.entity.Custom;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterDetailsDAOImpl implements RegisterDetailsDAO {
    @Override
    public List<Custom> loadAllStudentData(String id) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT reg.registerID, reg.date, s.studentId, s.name, s.contactNo FROM Reservation reg INNER JOIN Student s ON reg.student=s.studentId WHERE reg.room=:Room");
        query.setParameter("Room",session.load(Room.class, id) );
        
        List<Object[]>list = query.list();

        List<Custom> allDetails = new ArrayList<>();
        for (Object[] objects : list) {
            allDetails.add(new Custom(
                    objects[0].toString(),
                    LocalDate.parse(objects[1].toString()),
                    objects[2].toString(),
                    objects[3].toString(),
                    objects[4].toString()
            ));
        }
        transaction.commit();
        return allDetails;
    }
}
