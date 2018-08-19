package service;

import java.util.List;
import model.Course;
import org.springframework.web.servlet.ModelAndView;

public interface CourseService {
    public void addCourse(Course s);
    public void updateCourse(Course s);
    public List<Course> listCourses();
    public Course getCourseById(int id);
    public void removeCourse(int id, Course c);
    public ModelAndView lookupCourse(ModelAndView model, String id, boolean showRegistrations);
    public ModelAndView populateDropdowns(ModelAndView model, boolean studentToCourse);
    public boolean registerStudent(int courseID, int studentID);
    public boolean registerTeacher(int courseID, int teacherID);
    public boolean unregisterStudent(int courseID, int studentID);
    public boolean unregisterTeacher(int courseID, int teacherID);  
}