package controller;

import java.util.Map;
import javax.validation.Valid;
import model.Student;
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
import service.StudentService;

@Controller
public class StudentController 
{   
    @Autowired
    private StudentService studentService;

    @ModelAttribute("command")
    public Student createStudentModel() {
        return new Student();
    }
           
    @RequestMapping(value = {"/students", "/student_list"}, method = RequestMethod.GET)
    public ModelAndView showListStudents() {
        ModelAndView model = new ModelAndView("student/student_list");
        model.addObject("studentList", studentService.listStudents());
        return model;
    }
 
    @RequestMapping(value = "/student_add", method = RequestMethod.GET)
    public String getAddStudent(Model model) {
        model.addAttribute("command", new Student());
        return "student/student_add";
    }
    
    @RequestMapping(value = "/student_add", method = RequestMethod.POST)
    public String postAddStudent(@ModelAttribute("command") @Valid Student s, BindingResult br, Map model, RedirectAttributes redir) {
        if(br.hasErrors())
            return "student/student_add";
        studentService.addStudent(s);
        redir.addFlashAttribute("statusMsg", "Student ID " + s.getStudentID() + " was added successfully.");
        return "redirect:/student_list";
    }

    @RequestMapping(value = "/student_details", method = RequestMethod.GET)
    public ModelAndView showStudentDetails() {
        ModelAndView model = new ModelAndView("student/student_details");
        return model;
    }
    
    @RequestMapping(value = "/student_details/{id}", method = RequestMethod.GET)
    public ModelAndView getStudentDetails(@PathVariable("id") String id) {
        ModelAndView model = new ModelAndView("student/student_details");
        return studentService.lookupStudent(model, id);
    }
    
    @RequestMapping(value = "/student_edit", method = RequestMethod.GET)
    public ModelAndView showStudentEdit() {
        ModelAndView model = new ModelAndView("student/student_edit");
        return model;
    }
    
    @RequestMapping(value = "/student_edit/{id}", method = RequestMethod.GET)
    public ModelAndView getStudentEdit(@PathVariable("id") String id) {
        ModelAndView model = new ModelAndView("student/student_edit");
        return studentService.lookupStudent(model, id);
    }
    
    @RequestMapping(value = "/student_edit/{id}", method = RequestMethod.POST)
    public String postStudentEdit(@PathVariable("id") String id, @ModelAttribute("command") @Valid Student s, BindingResult br, Map model, RedirectAttributes redir) {
        if(br.hasErrors())
            return "/student/student_edit/" + id;
        s.setStudentID(Integer.parseInt(id));
        studentService.updateStudent(s);
        redir.addFlashAttribute("statusMsg", "Student ID " + s.getStudentID() + " was edited successfully.");
        return "redirect:/student_list";
    }
    
    @RequestMapping(value = "/student_delete/{id}", method = RequestMethod.POST)
    public String postDeleteStudent(@PathVariable("id") Integer id, RedirectAttributes redir) {
        studentService.removeStudent(id);
        redir.addFlashAttribute("statusMsg", "Student ID " + id + " was deleted successfully.");
        return "redirect:/student_list";
    }
}
