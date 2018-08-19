package service;

import java.util.List;
import model.Teacher;
import org.springframework.web.servlet.ModelAndView;

public interface TeacherService {
    public void addTeacher(Teacher s);
    public void updateTeacher(Teacher s, int id);
    public List<Teacher> listTeachers();
    public Teacher getTeacherById(int id);
    public void removeTeacher(int id);
    public ModelAndView lookupTeacher(ModelAndView model, String id, boolean listRegisteredCourses);
}