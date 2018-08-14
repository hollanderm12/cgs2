package dao;

import java.util.List;
import model.Teacher;
 
public interface TeacherDAO {
    public void addTeacher(Teacher s);
    public void updateTeacher(Teacher s);
    public List<Teacher> listTeachers();
    public Teacher getTeacherById(int id);
   public void removeTeacher(int id);
}