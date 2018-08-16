package service;

import java.util.List;
import java.util.Set;
import model.Course;
import model.Student;
import model.Teacher;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface CourseService {
    public void addCourse(Course s);
    public void updateCourse(Course s);
    public List<Course> listCourses();
    public Course getCourseById(int id);
    public void removeCourse(int id, Course c);
    public ModelAndView lookupCourse(ModelAndView model, String id);
    public Model populateDropdowns(Model model, boolean studentToCourse);
    public boolean checkDuplicateStudent(int studentID, Set<Student> students);
    public boolean checkDuplicateTeacher(int teacherID, Set<Teacher> teachers);
    public Teacher unregisterTeacher(int teacherID, Set<Teacher> teachers);
    public Student unregisterStudent(int studentID, Set<Student> students);
}