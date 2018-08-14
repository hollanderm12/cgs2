package controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import model.Course;
import util.Registration;
import model.Student;
import model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CourseService;
import service.StudentService;
import service.TeacherService;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    
    @ModelAttribute("command")
    public Course createCourseModel() {
        return new Course();
    }
    
    @RequestMapping(value = "/course_list", method = RequestMethod.GET)
    public ModelAndView showListCourses() {
        ModelAndView model = new ModelAndView("program/course_list");
        model.addObject("courseList", courseService.listCourses());
        return model;
    }
    
    @RequestMapping(value = "/course_add", method = RequestMethod.GET)
    public String getAddCourse(Model model) {
        model.addAttribute("command", new Course());
        return "program/course_add";
    }
    
    @RequestMapping(value = "/course_add", method = RequestMethod.POST)
    public String postAddCourse(@ModelAttribute("command") @Valid Course c, BindingResult br, Map model) {
        if(br.hasErrors())
            return "program/course_add";
        courseService.addCourse(c);
        return "redirect:/course_list";
    }
    
    @RequestMapping(value = "/course_details", method = RequestMethod.GET)
    public ModelAndView showCourseDetails() {
        ModelAndView model = new ModelAndView("program/course_details");
        return model;
    }
    
    @RequestMapping(value = "/course_details/{id}", method = RequestMethod.GET)
    public ModelAndView getCourseDetails(@PathVariable("id") String id) {
        ModelAndView model = new ModelAndView("program/course_details");
        Course c = new Course();
        try {
            c = courseService.getCourseById(Integer.parseInt(id));
        }
        catch(NumberFormatException ex) {
            model.addObject("lookupError", true);
            return model;
        }
        if(c == null)
            model.addObject("lookupError", true);
        else {
            model.addObject("detailsFound", c);
            model.addObject("studentsRegistered", c.getStudentsRegistered());
            model.addObject("teachersRegistered", c.getTeachersRegistered());
        }
        return model;
    }
    
    @RequestMapping(value = "/course_edit", method = RequestMethod.GET)
    public ModelAndView showCourseEdit() {
        ModelAndView model = new ModelAndView("program/course_edit");
        return model;
    }
    
    @RequestMapping(value = "/course_edit/{id}", method = RequestMethod.GET)
    public ModelAndView getCourseEdit(@PathVariable("id") String id) {
        ModelAndView model = new ModelAndView("program/course_edit");
        Course t = new Course();
        try {
            t = courseService.getCourseById(Integer.parseInt(id));
        }
        catch(NumberFormatException ex) {
            model.addObject("lookupError", true);
            return model;
        }
        if(t == null)
            model.addObject("lookupError", true);
        else
            model.addObject("detailsFound", t);
        return model;
    }
    
    @RequestMapping(value = "/course_edit/{id}", method = RequestMethod.POST)
    public String postCourseEdit(@PathVariable("id") String id, @ModelAttribute("command") @Valid Course t, BindingResult br, Map model) {
        if(br.hasErrors())
            return "/program/course_edit/" + id;
        t.setCourseID(Integer.parseInt(id));
        courseService.updateCourse(t);
        return "redirect:/course_list";
    }
    
    @RequestMapping(value = "/course_delete/{id}", method = RequestMethod.POST)
    public String postDeleteCourse(@PathVariable("id") String id) {
        Course c = courseService.getCourseById(Integer.parseInt(id));
        courseService.removeCourse(Integer.parseInt(id), c);
        return "redirect:/course_list";
    }
    
    @RequestMapping(value = "/course_add_student", method = RequestMethod.GET)
    public String getStudentRegistration(Model model) {
        List<Course> courseList = courseService.listCourses();
        List<Student> studentList = studentService.listStudents();
        if(courseList.isEmpty() || studentList.isEmpty()) {
            model.addAttribute("lookupError", true);
            if(courseList.isEmpty())
                model.addAttribute("noCourses", true);
            if(studentList.isEmpty())
                model.addAttribute("noStudents", true);
        }
        else {
            model.addAttribute("courseList", courseList);
            model.addAttribute("studentList", studentList);
            model.addAttribute("register", new Registration());
        }          
        return "program/course_add_student";
    }
    
    @RequestMapping(value = "/course_add_student", method = RequestMethod.POST)
    public String postStudentRegistration(@ModelAttribute("register") Registration r) {
        Course c = courseService.getCourseById(r.getId1());
        if(!courseService.checkDuplicateStudent(r.getId2(), c.getStudentsRegistered())) {
            c.getStudentsRegistered().add(studentService.getStudentById(r.getId2()));       
            courseService.updateCourse(c);
        }
        return "redirect:/course_list";
    }
    
    @RequestMapping(value = "/course_remove_student/{courseId}/{studentId}", method = RequestMethod.POST)
    public String postUnregisterStudent(@PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId) {
        Course c = courseService.getCourseById(Integer.parseInt(courseId));
        try {
            c.getStudentsRegistered().remove(courseService.unregisterStudent(Integer.parseInt(studentId), c.getStudentsRegistered()));
            courseService.updateCourse(c);
        }
        catch(NullPointerException ex) {
            System.out.println("Tried to unregister a student that didn't exist.");
        }        
        return "redirect:../../course_details/" + courseId;
    }
    
    @RequestMapping(value = "/course_add_teacher", method = RequestMethod.GET)
    public String getTeacherRegistration(Model model) {
        List<Course> courseList = courseService.listCourses();
        List<Teacher> teacherList = teacherService.listTeachers();
        if(courseList.isEmpty() || teacherList.isEmpty()) {
            model.addAttribute("lookupError", true);
            if(courseList.isEmpty())
                model.addAttribute("noCourses", true);
            if(teacherList.isEmpty())
                model.addAttribute("noTeachers", true);
        }
        else {
            model.addAttribute("courseList", courseList);
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("register", new Registration());
        }          
        return "program/course_add_teacher";
    }
    
    @RequestMapping(value = "/course_add_teacher", method = RequestMethod.POST)
    public String postTeacherRegistration(@ModelAttribute("register") Registration r) {
        Course c = courseService.getCourseById(r.getId1());
        if(!courseService.checkDuplicateTeacher(r.getId2(), c.getTeachersRegistered())) {
            c.getTeachersRegistered().add(teacherService.getTeacherById(r.getId2()));       
            courseService.updateCourse(c);
        }
        return "redirect:/course_list";
    }
    
    @RequestMapping(value = "/course_remove_teacher/{courseId}/{teacherId}", method = RequestMethod.POST)
    public String postUnregisterTeacher(@PathVariable("courseId") String courseId, @PathVariable("teacherId") String teacherId) {
        Course c = courseService.getCourseById(Integer.parseInt(courseId));
        try {
            c.getTeachersRegistered().remove(courseService.unregisterTeacher(Integer.parseInt(teacherId), c.getTeachersRegistered()));
            courseService.updateCourse(c);
        }
        catch(NullPointerException ex) {
            System.out.println("Tried to unregister a teacher that didn't exist.");
        }        
        return "redirect:../../course_details/" + courseId;
    }
}
