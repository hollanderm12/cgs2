
package dao;

import java.util.List;
import model.Teacher;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TeacherDAOImpl implements TeacherDAO {
    @Autowired
    private SessionFactory sessionFactory;
    	
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    @Transactional
    public void addTeacher(Teacher s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(s);
    }

    @Override
    public void updateTeacher(Teacher s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(s);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Teacher> listTeachers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Teacher> teachersList = session.createQuery("from Teacher order by teacherID").list();
        return teachersList;
    }

    @Override
    public Teacher getTeacherById(int id) {
        Session session = this.sessionFactory.getCurrentSession();		
        Teacher s = (Teacher) session.get(Teacher.class, id);
        return s;
    }

    @Override
    public void removeTeacher(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Teacher s = (Teacher) session.load(Teacher.class, id);
        if(null != s){
            session.delete(s);
        }
    }
}
