package dao;

import java.util.List;
import model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CourseDAOImpl implements CourseDAO {
    @Autowired
    private SessionFactory sessionFactory;
    	
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    @Transactional
    public void addCourse(Course s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(s);
    }

    @Override
    public void updateCourse(Course s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(s);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> listCourses() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Course> coursesList = session.createQuery("from Course order by courseID").list();
        return coursesList;
    }

    @Override
    public Course getCourseById(int id) {
        Session session = this.sessionFactory.getCurrentSession();		
        Course s = (Course) session.get(Course.class, id);
        return s;
    }

    @Override
    public void removeCourse(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Course s = (Course) session.load(Course.class, id);
        if(null != s){
            session.delete(s);
        }
    }
}
