package controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import model.Student;
import org.apache.log4j.Logger;
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
import service.StudentService;
import util.Level;
import util.LogThis;

@Controller
public class StudentController 
{   
    final static Logger LOGGER = Logger.getLogger(StudentController.class);
       
    @Autowired
    private StudentService studentService;

    @ModelAttribute("command")
    public Student createStudentModel() {
        return new Student();
    }
    
    @GetMapping(value = {"/students", "/student_list"})
    public ModelAndView showListStudents() {
        return new ModelAndView("student/student_list", "studentList", studentService.listStudents());
    }
 
    @GetMapping(value = "/student_add")
    public ModelAndView getAddStudent(Model model) {
        return new ModelAndView("student/student_add", "command", new Student());
    }
    
    @PostMapping(value = "/student_add")
    public ModelAndView postAddStudent(@ModelAttribute("command") @Valid Student s, BindingResult br, RedirectAttributes redir, HttpServletRequest req) {
        if(br.hasErrors())
            return new ModelAndView("student/student_add");
        studentService.addStudent(s);
        redir.addFlashAttribute("statusMsg", "Student ID " + s.getStudentID() + " was added successfully.");
        LogThis.log(LOGGER, Level.INFO, "Student ID " + s.getStudentID() + " was added by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/student_list");
    }

    @GetMapping(value = "/student_details")
    public ModelAndView showStudentDetails() {
        return new ModelAndView("student/student_details");
    }
    
    @GetMapping(value = "/student_details/{id}")
    public ModelAndView getStudentDetails(@PathVariable("id") String id) {
        return studentService.lookupStudent(new ModelAndView("student/student_details"), id, true);
    }
    
    @PostMapping(value = "/student_details/email/{id}")
    public ModelAndView emailStudentResults(@PathVariable("id") Integer id, RedirectAttributes redir) {
        if(studentService.sendResultsEmail(id))
            redir.addFlashAttribute("statusMsg", "The results email sent successfully.");
        else
            redir.addFlashAttribute("errorMsg", "There was a problem with sending the results email. Please check the server logs for error information.");
        return new ModelAndView("redirect:/student_details/" + id);
    }
    
    @GetMapping(value = "/student_edit")
    public ModelAndView showStudentEdit() {
        return new ModelAndView("student/student_edit");
    }
    
    @GetMapping(value = "/student_edit/{id}")
    public ModelAndView getStudentEdit(@PathVariable("id") String id) {
        return studentService.lookupStudent(new ModelAndView("student/student_edit"), id, false);
    }
    
    @PostMapping(value = "/student_edit/{id}")
    public ModelAndView postStudentEdit(@PathVariable("id") Integer id, @ModelAttribute("command") @Valid Student s, BindingResult br, RedirectAttributes redir,  HttpServletRequest req) {
        if(br.hasErrors())
            return studentService.lookupStudent(new ModelAndView("student/student_edit"), id.toString(), false);
        studentService.updateStudent(s, id);
        redir.addFlashAttribute("statusMsg", "Student ID " + s.getStudentID() + " was edited successfully.");
        LogThis.log(LOGGER, Level.INFO, "Student ID " + id + " was edited by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/student_details/" + id);
    }
    
    @PostMapping(value = "/student_delete/{id}")
    public ModelAndView postDeleteStudent(@PathVariable("id") Integer id, RedirectAttributes redir, HttpServletRequest req) {
        studentService.removeStudent(id);
        redir.addFlashAttribute("statusMsg", "Student ID " + id + " was deleted successfully.");
        LogThis.log(LOGGER, Level.INFO, "Student ID " + id + " was deleted by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/student_list");
    }
}
