package service;

import dao.ResultDAO;
import java.util.List;
import model.Course;
import model.Result;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class ResultServiceImpl implements ResultService {
    private ResultDAO resultDAO;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    public void setResultDAO(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }
     
    @Override
    @Transactional
    public int addResult(Result r) {
        Student s = studentService.getStudentById(Integer.parseInt(r.getStudentID()));
        if(!s.getCoursesRegistered().contains(courseService.getCourseById(Integer.parseInt(r.getCourseID()))))
            return 0;
        else {
            r.setStudentResult(studentService.getStudentById(Integer.parseInt(r.getStudentID())));
            r.setCourseResult(courseService.getCourseById(Integer.parseInt(r.getCourseID())));
            resultDAO.addResult(r);
            return 1;
        }
    }

    @Override
    @Transactional
    public int updateResult(Result r) {
        Student s = studentService.getStudentById(Integer.parseInt(r.getStudentID()));
        if(!s.getCoursesRegistered().contains(courseService.getCourseById(Integer.parseInt(r.getCourseID()))))
            return 0;
        else {
            r.setStudentResult(studentService.getStudentById(Integer.parseInt(r.getStudentID())));
            r.setCourseResult(courseService.getCourseById(Integer.parseInt(r.getCourseID())));
            resultDAO.updateResult(r);
            return 1;
        }
    }

    @Override
    @Transactional
    public List<Result> listResults() {
        return resultDAO.listResults();
    }

    @Override
    @Transactional
    public Result getResultById(int id) {
        return resultDAO.getResultById(id);
    }

    @Override
    @Transactional
    public void removeResult(int id, Result r) {
        resultDAO.removeResult(id);
    }
    
    @Override
    public Model populateDropdowns(Model model) {
        List<Student> studentList = studentService.listStudents();
        List<Course> courseList = courseService.listCourses();       
        if(studentList.isEmpty() || courseList.isEmpty()) {
            model.addAttribute("lookupError", true);
            if(studentList.isEmpty())
                model.addAttribute("noStudents", true);
            if(courseList.isEmpty())
                model.addAttribute("noCourses", true);         
        }
        else {
            model.addAttribute("studentList", studentList);
            model.addAttribute("courseList", courseList);
            model.addAttribute("command", new Result());
        }
        return model;
    }

    @Override
    @Transactional
    public Model lookupResult(Model model, String id) {
        Result r;
        try {
            r = this.getResultById(Integer.parseInt(id));
        }
        catch(NumberFormatException ex) {
            model.addAttribute("lookupError", true);
            return model;
        }
        if(r == null)
            model.addAttribute("lookupError", true);
        else {
            model = this.populateDropdowns(model);
            model.addAttribute("detailsFound", r);
        }
        return model;
    }
}
