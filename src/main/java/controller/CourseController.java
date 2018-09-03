package controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import model.Course;
import org.apache.log4j.Logger;
import util.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CourseService;
import util.Level;
import util.LogThis;

@Controller
public class CourseController {
    
    final static Logger LOGGER = Logger.getLogger(CourseController.class);
    
    @Autowired
    private CourseService courseService;
    
    @ModelAttribute("command")
    public Course createCourseModel() {
        return new Course();
    }
    
    @GetMapping(value = "/course_list")
    public ModelAndView showListCourses() {
        return new ModelAndView("program/course_list", "courseList", courseService.listCourses());
    }
    
    @GetMapping(value = "/course_add")
    public ModelAndView getAddCourse() {
        return new ModelAndView("program/course_add", "command", new Course());
    }
    
    @PostMapping(value = "/course_add")
    public ModelAndView postAddCourse(@ModelAttribute("command") @Valid Course c, BindingResult br, RedirectAttributes redir, HttpServletRequest req) {
        if(br.hasErrors())
            return new ModelAndView("program/course_add");
        courseService.addCourse(c);
        redir.addFlashAttribute("statusMsg", "Course ID " + c.getCourseID() + " was added successfully.");
        LogThis.log(LOGGER, Level.INFO, "Course ID " + c.getCourseID() + " was added by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/course_list");
    }
    
    @GetMapping(value = "/course_details")
    public ModelAndView showCourseDetails() {
        return new ModelAndView("program/course_details");
    }
    
    @GetMapping(value = "/course_details/{id}")
    public ModelAndView getCourseDetails(@PathVariable("id") String id) {
       return courseService.lookupCourse(new ModelAndView("program/course_details"), id, true);         
    }
    
    @GetMapping(value = "/course_edit")
    public ModelAndView showCourseEdit() {
        return new ModelAndView("program/course_edit");
    }
    
    @GetMapping(value = "/course_edit/{id}")
    public ModelAndView getCourseEdit(@PathVariable("id") String id) {
        return courseService.lookupCourse(new ModelAndView("program/course_edit"), id, false);
    }
    
    @PostMapping(value = "/course_edit/{id}")
    public ModelAndView postCourseEdit(@PathVariable("id") Integer id, @ModelAttribute("command") @Valid Course c, BindingResult br, RedirectAttributes redir, HttpServletRequest req) {
        if(br.hasErrors())
            return courseService.lookupCourse(new ModelAndView("program/course_edit"), id.toString(), false);
        c.setCourseID(id);
        courseService.updateCourse(c);
        redir.addFlashAttribute("statusMsg", "Course ID " + c.getCourseID() + " was edited successfully.");
        LogThis.log(LOGGER, Level.INFO, "Course ID " + c.getCourseID() + " was edited by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/course_details/" + id);
    }
    
    @PostMapping(value = "/course_delete/{id}")
    public ModelAndView postDeleteCourse(@PathVariable("id") Integer id, RedirectAttributes redir, HttpServletRequest req) {
        courseService.removeCourse(id, courseService.getCourseById(id));
        redir.addFlashAttribute("statusMsg", "Course ID " + id + " was deleted successfully.");
        LogThis.log(LOGGER, Level.INFO, "Teacher ID " + id + " was deleted by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/course_list");
    }
    
    @GetMapping(value = "/course_add_student")
    public ModelAndView getStudentRegistration() {
        return courseService.populateDropdowns(new ModelAndView("program/course_add_student"), true);          
    }
    
    @GetMapping(value = "/course_add_student/{id}")
    public ModelAndView getStudentRegistrationSpecificCourse(@PathVariable("id") String courseId) {    
        return courseService.populateDropdowns(new ModelAndView("program/course_add_student", "courseIdChosen", courseId), true);
    }
    
    @PostMapping(value = "/course_add_student")
    public ModelAndView postStudentRegistration(@ModelAttribute("register") Registration r, RedirectAttributes redir) {
        if(courseService.registerStudent(r.getId1(), r.getId2()))
            redir.addFlashAttribute("statusMsg", "Student ID " + r.getId2() + " was successfully registered to this course.");
        else
            redir.addFlashAttribute("warningMsg", "Student ID " + r.getId2() + " is already registered to this course."); 
        return new ModelAndView("redirect:/course_details/" + r.getId1());
    }
    
    @PostMapping(value = "/course_remove_student/{courseId}/{studentId}")
    public ModelAndView postUnregisterStudent(@PathVariable("courseId") Integer courseId, @PathVariable("studentId") Integer studentId, RedirectAttributes redir) {
        if(courseService.unregisterStudent(courseId, studentId))
            redir.addFlashAttribute("statusMsg", "Student ID " + studentId + " was successfully unregistered from course ID " + courseId + ".");
        else
            redir.addFlashAttribute("errorMsg", "Could not unregister student ID " + studentId + " from course ID " + courseId + ". Please verify if the student exists in the system.");
        return new ModelAndView("redirect:/course_details/" + courseId);
    }
    
    @GetMapping(value = "/course_add_teacher")
    public ModelAndView getTeacherRegistration(Model model) {
        return courseService.populateDropdowns(new ModelAndView("program/course_add_teacher"), false);
    }
    
    @GetMapping(value = "/course_add_teacher/{id}")
    public ModelAndView getTeacherRegistrationSpecificCourse(@PathVariable("id") String courseId) {
        return courseService.populateDropdowns(new ModelAndView("program/course_add_teacher", "courseIdChosen", courseId), false);
    }
    
    @PostMapping(value = "/course_add_teacher")
    public ModelAndView postTeacherRegistration(@ModelAttribute("register") Registration r, RedirectAttributes redir) {
        if(courseService.registerTeacher(r.getId1(), r.getId2()))
            redir.addFlashAttribute("statusMsg", "Teacher ID " + r.getId2() + " was successfully registered to this course.");
        else
            redir.addFlashAttribute("warningMsg", "Teacher ID " + r.getId2() + " is already registered to this course."); 
        return new ModelAndView("redirect:/course_details/" + r.getId1());
    }
    
    @PostMapping(value = "/course_remove_teacher/{courseId}/{teacherId}")
    public ModelAndView postUnregisterTeacher(@PathVariable("courseId") Integer courseId, @PathVariable("teacherId") Integer teacherId, RedirectAttributes redir, HttpServletRequest req) {
        if(courseService.unregisterTeacher(courseId, teacherId))
            redir.addFlashAttribute("statusMsg", "Teacher ID " + teacherId + " was successfully unregistered from course ID " + courseId + ".");
        else
            redir.addFlashAttribute("errorMsg", "Could not unregister teacher ID " + teacherId + " from course ID " + courseId + ". Please verify if the teacher exists in the system.");
        return new ModelAndView("redirect:/course_details/" + courseId);
    }
}
