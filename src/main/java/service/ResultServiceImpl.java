package service;

import dao.ResultDAO;
import java.util.List;
import model.Course;
import model.Result;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

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
    public boolean addResult(Result r) {
        Student s = studentService.getStudentById(Integer.parseInt(r.getStudentID()));
        if(!s.getCoursesRegistered().contains(courseService.getCourseById(Integer.parseInt(r.getCourseID()))))
            return false;
        else {
            r.setStudentResult(studentService.getStudentById(Integer.parseInt(r.getStudentID())));
            r.setCourseResult(courseService.getCourseById(Integer.parseInt(r.getCourseID())));
            resultDAO.addResult(r);
            return true;
        }
    }

    @Override
    @Transactional
    public boolean updateResult(Result r) {
        Student s = studentService.getStudentById(Integer.parseInt(r.getStudentID()));
        if(!s.getCoursesRegistered().contains(courseService.getCourseById(Integer.parseInt(r.getCourseID()))))
            return false;
        r.setStudentResult(studentService.getStudentById(Integer.parseInt(r.getStudentID())));
        r.setCourseResult(courseService.getCourseById(Integer.parseInt(r.getCourseID())));
        resultDAO.updateResult(r);
        return true;
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
    public ModelAndView populateDropdowns(ModelAndView model) {
        List<Student> studentList = studentService.listStudents();
        List<Course> courseList = courseService.listCourses();
        if(studentList.isEmpty() || courseList.isEmpty()) {
            model.addObject("lookupError", true);
            if(studentList.isEmpty())
                model.addObject("noStudents", true);
            if(courseList.isEmpty())
                model.addObject("noCourses", true);         
        }
        else {
            model.addObject("studentList", studentList);
            model.addObject("courseList", courseList);
            model.addObject("command", new Result());
        }
        return model;
    }

    @Override
    @Transactional
    public ModelAndView lookupResult(ModelAndView model, String id) {
        Result r;
        try {
            r = this.getResultById(Integer.parseInt(id));
        }
        catch(NumberFormatException ex) {
            model.addObject("lookupError", true);
            return model;
        }
        if(r == null)
            model.addObject("lookupError", true);
        else {
            model = this.populateDropdowns(model);
            model.addObject("detailsFound", r);
        }
        return model;
    }
}
