package controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import model.Teacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.TeacherService;
import util.Level;
import util.LogThis;

@Controller
public class TeacherController {
    
    final static Logger LOGGER = Logger.getLogger(TeacherController.class);
    
    @Autowired
    private TeacherService teacherService;
    
    @ModelAttribute("command")
    public Teacher createTeacherModel() {
        return new Teacher();
    }
    
    @GetMapping(value = {"/programs", "/teacher_list"})
    public ModelAndView showListTeachers() {
        return new ModelAndView("program/teacher_list", "teacherList", teacherService.listTeachers());
    }
    
    @GetMapping(value = "/teacher_add")
    public ModelAndView getAddTeacher() {
        return new ModelAndView("program/teacher_add", "command", new Teacher());
    }
    
    @PostMapping(value = "/teacher_add")
    public ModelAndView postAddTeacher(@ModelAttribute("command") @Valid Teacher t, BindingResult br, RedirectAttributes redir, HttpServletRequest req) {
        if(br.hasErrors())
            return new ModelAndView("program/teacher_add");
        teacherService.addTeacher(t);
        redir.addFlashAttribute("statusMsg", "Teacher ID " + t.getTeacherID() + " was added successfully.");
        LogThis.log(LOGGER, Level.INFO, "Teacher ID " + t.getTeacherID() + " was added by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/teacher_list");
    }
    
    @GetMapping(value = "/teacher_details")
    public ModelAndView showTeacherDetails() {
        return new ModelAndView("program/teacher_details");
    }
    
    @GetMapping(value = "/teacher_details/{id}")
    public ModelAndView getTeacherDetails(@PathVariable("id") String id) {
        return teacherService.lookupTeacher(new ModelAndView("program/teacher_details"), id, true);
    }
    
    @GetMapping(value = "/teacher_edit")
    public ModelAndView showTeacherEdit() {
        return new ModelAndView("program/teacher_edit");
    }
    
    @GetMapping(value = "/teacher_edit/{id}")
    public ModelAndView getTeacherEdit(@PathVariable("id") String id) {
        return teacherService.lookupTeacher(new ModelAndView("program/teacher_edit"), id, false);
    }
    
    @PostMapping(value = "/teacher_edit/{id}")
    public ModelAndView postTeacherEdit(@PathVariable("id") Integer id, @ModelAttribute("command") @Valid Teacher t, BindingResult br, RedirectAttributes redir, HttpServletRequest req) {
        if(br.hasErrors())
            return teacherService.lookupTeacher(new ModelAndView("program/teacher_edit"), id.toString(), false); 
        teacherService.updateTeacher(t, id);
        redir.addFlashAttribute("statusMsg", "Student ID " + t.getTeacherID() + " was edited successfully.");
        LogThis.log(LOGGER, Level.INFO, "Teacher ID " + t.getTeacherID() + " was edited by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/teacher_details/" + id);
    }
    
    @PostMapping(value = "/teacher_delete/{id}")
    public ModelAndView postDeleteTeacher(@PathVariable("id") Integer id, RedirectAttributes redir, HttpServletRequest req) {
        teacherService.removeTeacher(id);
        redir.addFlashAttribute("statusMsg", "Teacher ID " + id + " was deleted successfully.");
        LogThis.log(LOGGER, Level.INFO, "Teacher ID " + id + " was deleted by user " + req.getRemoteUser());
        return new ModelAndView("redirect:/teacher_list");
    }
}
