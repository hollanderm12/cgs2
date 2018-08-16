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
import org.springframework.ui.Model;
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
    public ModelAndView lookupCourse(ModelAndView model, String id) {
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
        else
            model.addObject("detailsFound", c);
        return model;
    }
    
    @Override
    @Transactional
    public Model populateDropdowns(Model model, boolean studentToCourse) {
        List<Course> courseList = this.listCourses();
        List listToUse = studentToCourse ? studentService.listStudents() : teacherService.listTeachers();
        if(courseList.isEmpty() || listToUse.isEmpty()) {
            model.addAttribute("lookupError", true);
            if(courseList.isEmpty())
                model.addAttribute("noCourses", true);
            if(listToUse.isEmpty())
                if(studentToCourse)
                    model.addAttribute("noStudents", true);
                else
                    model.addAttribute("noTeachers", true);
        }
        else {
            model.addAttribute("courseList", courseList);
            if(studentToCourse)
                model.addAttribute("studentList", listToUse);
            else
                model.addAttribute("teacherList", listToUse);
            model.addAttribute("register", new Registration());
        }
        return model;
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