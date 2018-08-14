package service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.StudentDAO;
import model.Course;
import model.Result;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
 
@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ResultService resultService;

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
     
    @Override
    @Transactional
    public void addStudent(Student s) {
        studentDAO.addStudent(s);
    }

    @Override
    @Transactional
    public void updateStudent(Student s) {
        studentDAO.updateStudent(s);
    }

    @Override
    @Transactional
    public List<Student> listStudents() {
        return studentDAO.listStudents();
    }

    @Override
    @Transactional
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    @Transactional
    public void removeStudent(int id) {
        Student s = this.getStudentById(id);
        List<Course> courseList = courseService.listCourses();
        for(Course c : courseList)
            if(c.getStudentsRegistered().remove(courseService.unregisterStudent(id, c.getStudentsRegistered())))
                courseService.updateCourse(c);
        List<Result> resultList = resultService.listResults();
        for(Result r : resultList)
            if(r.getStudentResult().equals(s))
                resultService.removeResult(r.getResultID(), r);                  
        studentDAO.removeStudent(id);
    }
    
    @Override
    @Transactional
    public ModelAndView lookupStudent(ModelAndView model, String id) {
        Student s;
        try {
            s = this.getStudentById(Integer.parseInt(id));
        }
        catch(NumberFormatException ex) {
            model.addObject("lookupError", true);
            return model;
        }
        if(s == null)
            model.addObject("lookupError", true);
        else
            model.addObject("detailsFound", s);
        return model;
    }
}