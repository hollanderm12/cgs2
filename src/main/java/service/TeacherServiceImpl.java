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
    public void addTeacher(Teacher t) {
        teacherDAO.addTeacher(t);
    }

    @Override
    @Transactional
    public void updateTeacher(Teacher t, int id) {
        t.setTeacherID(id);
        teacherDAO.updateTeacher(t);
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
        Teacher t = this.getTeacherById(id);
        List<Course> courseList = courseService.listCourses();
        for(Course c : courseList)
            if(c.getTeachersRegistered().contains(t))
                courseService.unregisterTeacher(c.getCourseID(), id);
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
            model.addObject("errorMsg", "The teacher ID specified was not found. Please verify the teacher ID and try again.");
            return model;
        }
        if(t == null)
            model.addObject("errorMsg", "The teacher ID specified was not found. Please verify the teacher ID and try again.");
        else {
            model.addObject("detailsFound", t);
            if(listRegisteredCourses)
                model.addObject("coursesRegistered", t.getCoursesRegistered());
        }
        return model;
    }
}