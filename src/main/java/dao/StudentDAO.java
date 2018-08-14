package dao;

import java.util.List;
import model.Student;

public interface StudentDAO {
    public void addStudent(Student s);
    public void updateStudent(Student s);
    public List<Student> listStudents();
    public Student getStudentById(int id);
    public void removeStudent(int id);
}