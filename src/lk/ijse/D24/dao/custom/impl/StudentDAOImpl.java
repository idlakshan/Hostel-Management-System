package lk.ijse.D24.dao.custom.impl;

import lk.ijse.D24.dao.custom.StudentDAO;
import lk.ijse.D24.entity.Student;
import lk.ijse.D24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws IOException  {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, s);
        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student find(String id) throws  IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);

        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public List<Student> loadAll() throws  IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> list = null;
        Query from_student = session.createQuery("FROM Student");
        list = from_student.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List setRoomIDs() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<String> sId = new ArrayList<>();
        Query select_studentId_from_student = session.createQuery("SELECT studentId FROM Student");
        sId = select_studentId_from_student.list();
        transaction.commit();
        session.close();
        return sId;
    }

    @Override
    public String lastStudentID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT studentId FROM Student ORDER BY studentId DESC LIMIT 1");
        String id = (String)(sqlQuery.uniqueResult());

        return id;
    }

    @Override
    public String generateNewId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT studentId FROM student ORDER BY studentId DESC LIMIT 1");

        String id = (String)(sqlQuery.uniqueResult());
        int newRegisterId = Integer.parseInt(id.replace("S-", "")) + 1;
        return String.format("S-%03d", newRegisterId);
    }
}
