package service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.TeacherDAO;
import model.Course;
import model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
 
@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherDAO teacherDAO;
    @Autowired
    private CourseService courseService;

    public void setTeacherDAO(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }
     
    @Override
    @Transactional
    public void addTeacher(Teacher s) {
        teacherDAO.addTeacher(s);
    }

    @Override
    @Transactional
    public void updateTeacher(Teacher s) {
        teacherDAO.updateTeacher(s);
    }

    @Override
    @Transactional
    public List<Teacher> listTeachers() {
        return teacherDAO.listTeachers();
    }

    @Override
    @Transactional
    public Teacher getTeacherById(int id) {
        return teacherDAO.getTeacherById(id);
    }

    @Override
    @Transactional
    public void removeTeacher(int id) {
        List<Course> courseList = courseService.listCourses();
        for(Course c : courseList)
            if(c.getTeachersRegistered().remove(courseService.unregisterTeacher(id, c.getTeachersRegistered())))
                courseService.updateCourse(c);
        teacherDAO.removeTeacher(id);
    }
    
    @Override
    @Transactional
    public ModelAndView lookupTeacher(ModelAndView model, String id, boolean listRegisteredCourses) {
        Teacher t;
        try {
            t = this.getTeacherById(Integer.parseInt(id));
        }
        catch(NumberFormatException ex) {
            model.addObject("lookupError", true);
            return model;
        }
        if(t == null)
            model.addObject("lookupError", true);
        else {
            model.addObject("detailsFound", t);
            if(listRegisteredCourses)
                model.addObject("coursesRegistered", t.getCoursesRegistered());
        }
        return model;
    }
}