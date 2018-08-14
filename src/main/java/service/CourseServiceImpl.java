package service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.CourseDAO;
import java.util.Set;
import model.Course;
import model.Student;
import model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
 
@Service
public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO;
    @Autowired
    private TeacherService teacherService;

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }
     
    @Override
    @Transactional
    public void addCourse(Course s) {
        courseDAO.addCourse(s);
    }

    @Override
    @Transactional
    public void updateCourse(Course s) {
        courseDAO.updateCourse(s);
    }

    @Override
    @Transactional
    public List<Course> listCourses() {
        return courseDAO.listCourses();
    }

    @Override
    @Transactional
    public Course getCourseById(int id) {
        return courseDAO.getCourseById(id);
    }

    @Override
    @Transactional
    public void removeCourse(int id, Course c) {
        c.getStudentsRegistered().clear();
        c.getTeachersRegistered().clear();
        courseDAO.removeCourse(id);
    }
    
    @Override
    public boolean checkDuplicateStudent(int studentID, Set<Student> students) {
        for(Student s : students) 
            if(s.getStudentID() == studentID)
                return true;       
        return false;
    }

    @Override
    public boolean checkDuplicateTeacher(int teacherID, Set<Teacher> teachers) {
        for(Teacher t : teachers) 
            if(t.getTeacherID() == teacherID)
                return true;       
        return false;
    }
    
    @Override
    public Teacher unregisterTeacher(int teacherID, Set<Teacher> teachers) {
        for(Teacher t : teachers)
            if(t.getTeacherID() == teacherID)
                return t;
        return null;
    }
    
    @Override
    public Student unregisterStudent(int studentID, Set<Student> students) {
        for(Student s : students)
            if(s.getStudentID() == studentID)
                return s;
        return null;
    }
}