package service;

import java.util.List;
import model.Student;
import org.springframework.web.servlet.ModelAndView;

public interface StudentService {
    public void addStudent(Student s);
    public void updateStudent(Student s);
    public List<Student> listStudents();
    public Student getStudentById(int id);
    public void removeStudent(int id);    
    public ModelAndView lookupStudent(ModelAndView model, String id);
}