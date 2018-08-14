package dao;

import java.util.List;
import model.Course;
 
public interface CourseDAO {
    public void addCourse(Course s);
    public void updateCourse(Course s);
    public List<Course> listCourses();
    public Course getCourseById(int id);
   public void removeCourse(int id);
}