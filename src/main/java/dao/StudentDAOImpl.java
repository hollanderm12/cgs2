
package dao;

import java.util.List;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;
    	
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    @Transactional
    public void addStudent(Student s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(s);
    }

    @Override
    public void updateStudent(Student s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(s);
        System.out.println("Here!");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> listStudents() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Student> studentsList = session.createQuery("from Student order by studentID").list();
        return studentsList;
    }

    @Override
    public Student getStudentById(int id) {
        Session session = this.sessionFactory.getCurrentSession();		
        Student s = (Student) session.get(Student.class, id);
        return s;
    }

    @Override
    public void removeStudent(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Student s = (Student) session.load(Student.class, id);
        if(null != s){
            session.delete(s);
        }
    }
}
