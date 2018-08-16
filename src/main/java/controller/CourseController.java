package controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import model.Course;
import util.Registration;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        return courseService.lookupCourse(model, id);
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
        model = courseService.populateDropdowns(model, true);          
        return "program/course_add_student";
    }
    
    @RequestMapping(value = "/course_add_student", method = RequestMethod.POST)
    public ModelAndView postStudentRegistration(@ModelAttribute("register") Registration r, RedirectAttributes redir) {
        Course c = courseService.getCourseById(r.getId1());
        if(!courseService.checkDuplicateStudent(r.getId2(), c.getStudentsRegistered())) {
            c.getStudentsRegistered().add(studentService.getStudentById(r.getId2()));       
            courseService.updateCourse(c);
            redir.addFlashAttribute("statusMsg", "Student ID " + r.getId2() + " was successfully registered to this course.");
        }
        else
           redir.addFlashAttribute("statusMsg", "Student ID " + r.getId2() + " is already registered to this course."); 
        return new ModelAndView("redirect:/course_details/" + r.getId1());
    }
    
    @RequestMapping(value = "/course_remove_student/{courseId}/{studentId}", method = RequestMethod.POST)
    public String postUnregisterStudent(@PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId, RedirectAttributes redir) {
        Course c = courseService.getCourseById(Integer.parseInt(courseId));
        int actuallyUnregistered = c.getTeachersRegistered().size();
        c.getStudentsRegistered().remove(courseService.unregisterStudent(Integer.parseInt(studentId), c.getStudentsRegistered()));
        if(actuallyUnregistered != c.getStudentsRegistered().size())
            redir.addFlashAttribute("statusMsg", "Student ID " + studentId + " was successfully unregistered from course ID " + courseId + ".");
        else
            redir.addFlashAttribute("statusMsg", "Could not unregister student ID " + studentId + " from course ID " + courseId + ". Please verify if the student exists in the system.");
        courseService.updateCourse(c);    
        return "redirect:../../course_details/" + courseId;
    }
    
    @RequestMapping(value = "/course_add_teacher", method = RequestMethod.GET)
    public String getTeacherRegistration(Model model) {
        model = courseService.populateDropdowns(model, false);    
        return "program/course_add_teacher";
    }
    
    @RequestMapping(value = "/course_add_teacher", method = RequestMethod.POST)
    public ModelAndView postTeacherRegistration(@ModelAttribute("register") Registration r, RedirectAttributes redir) {
        Course c = courseService.getCourseById(r.getId1());
        if(!courseService.checkDuplicateTeacher(r.getId2(), c.getTeachersRegistered())) {
            c.getTeachersRegistered().add(teacherService.getTeacherById(r.getId2()));       
            courseService.updateCourse(c);
            redir.addFlashAttribute("statusMsg", "Teacher ID " + r.getId2() + " was successfully registered to this course.");
        }
        else
           redir.addFlashAttribute("statusMsg", "Teacher ID " + r.getId2() + " is already registered to this course."); 
        return new ModelAndView("redirect:/course_details/" + r.getId1());
    }
    
    @RequestMapping(value = "/course_remove_teacher/{courseId}/{teacherId}", method = RequestMethod.POST)
    public String postUnregisterTeacher(@PathVariable("courseId") String courseId, @PathVariable("teacherId") String teacherId, RedirectAttributes redir) {
        Course c = courseService.getCourseById(Integer.parseInt(courseId));
        int actuallyUnregistered = c.getTeachersRegistered().size();
        c.getTeachersRegistered().remove(courseService.unregisterTeacher(Integer.parseInt(teacherId), c.getTeachersRegistered()));
        if(actuallyUnregistered != c.getTeachersRegistered().size())
            redir.addFlashAttribute("statusMsg", "Teacher ID " + teacherId + " was successfully unregistered from course ID " + courseId + ".");
        else
            redir.addFlashAttribute("statusMsg", "Could not unregister teacher ID " + teacherId + " from course ID " + courseId + ". Please verify if the teacher exists in the system.");
        courseService.updateCourse(c);    
        return "redirect:../../course_details/" + courseId;
    }
}
