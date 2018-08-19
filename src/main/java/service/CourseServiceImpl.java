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
import org.springframework.web.servlet.ModelAndView;
import util.Registration;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO;
    @Autowired
    private StudentService studentService;
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
    @Transactional
    public ModelAndView lookupCourse(ModelAndView model, String id, boolean showRegistrations) {
        Course c;
        try {
            c = this.getCourseById(Integer.parseInt(id));
        }
        catch(NumberFormatException ex) {
            model.addObject("lookupError", true);
            return model;
        }
        if(c == null)
            model.addObject("lookupError", true);
        else {
            model.addObject("detailsFound", c);
            if(showRegistrations) {
                model.addObject("studentsRegistered", c.getStudentsRegistered());
                model.addObject("teachersRegistered", c.getTeachersRegistered());
            }
        }
        return model;
    }
    
    @Override
    @Transactional
    public ModelAndView populateDropdowns(ModelAndView model, boolean studentToCourse) {
        List<Course> courseList = this.listCourses();
        List listToUse = studentToCourse ? studentService.listStudents() : teacherService.listTeachers();
        if(courseList.isEmpty() || listToUse.isEmpty()) {
            model.addObject("lookupError", true);
            if(courseList.isEmpty())
                model.addObject("noCourses", true);
            if(listToUse.isEmpty())
                if(studentToCourse)
                    model.addObject("noStudents", true);
                else
                    model.addObject("noTeachers", true);
        }
        else {
            model.addObject("courseList", courseList);
            if(studentToCourse)
                model.addObject("studentList", listToUse);
            else
                model.addObject("teacherList", listToUse);
            model.addObject("register", new Registration());
        }
        return model;
    }
    
    @Override
    @Transactional
    public boolean registerStudent(int courseID, int studentID) {
        Course c = this.getCourseById(courseID);
        Set<Student> students = c.getStudentsRegistered();
        for(Student s : students) 
            if(s.getStudentID() == studentID)
                return false;
        c.getStudentsRegistered().add(studentService.getStudentById(studentID));       
        this.updateCourse(c);
        return true;
    }

    @Override
    @Transactional
    public boolean registerTeacher(int courseID, int teacherID) {
        Course c = this.getCourseById(courseID);
        Set<Teacher> teachers = c.getTeachersRegistered();
        for(Teacher t : teachers) 
            if(t.getTeacherID() == teacherID)
                return false;
        c.getTeachersRegistered().add(teacherService.getTeacherById(teacherID));
        this.updateCourse(c);
        return true;
    }
        
    @Override
    @Transactional
    public boolean unregisterStudent(int courseID, int studentID) {
        Course c = this.getCourseById(courseID);
        if(c.getStudentsRegistered().remove(studentService.getStudentById(studentID))) {
            this.updateCourse(c);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional
    public boolean unregisterTeacher(int courseID, int teacherID) {
        Course c = this.getCourseById(courseID);
        if(c.getTeachersRegistered().remove(teacherService.getTeacherById(teacherID))) {
            this.updateCourse(c);
            return true;
        }
        return false;
    }
}