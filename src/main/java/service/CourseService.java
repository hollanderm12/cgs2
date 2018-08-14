package service;

import java.util.List;
import java.util.Set;
import model.Course;
import model.Student;
import model.Teacher;

public interface CourseService {
    public void addCourse(Course s);
    public void updateCourse(Course s);
    public List<Course> listCourses();
    public Course getCourseById(int id);
    public void removeCourse(int id, Course c);
    public boolean checkDuplicateStudent(int studentID, Set<Student> students);
    public boolean checkDuplicateTeacher(int teacherID, Set<Teacher> teachers);
    public Teacher unregisterTeacher(int teacherID, Set<Teacher> teachers);
    public Student unregisterStudent(int studentID, Set<Student> students);
}