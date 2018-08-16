package controller;

import java.util.Map;
import javax.validation.Valid;
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
import service.TeacherService;

@Controller
public class TeacherController 
{
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    
    @ModelAttribute("command")
    public Teacher createTeacherModel() {
        return new Teacher();
    }
    
    @RequestMapping(value = {"/programs", "/teacher_list"}, method = RequestMethod.GET)
    public ModelAndView showListTeachers() {
        ModelAndView model = new ModelAndView("program/teacher_list");
        model.addObject("teacherList", teacherService.listTeachers());
        return model;
    }
    
    @RequestMapping(value = "/teacher_add", method = RequestMethod.GET)
    public String getAddTeacher(Model model) {
        model.addAttribute("command", new Teacher());
        return "program/teacher_add";
    }
    
    @RequestMapping(value = "/teacher_add", method = RequestMethod.POST)
    public String postAddTeacher(@ModelAttribute("command") @Valid Teacher t, BindingResult br, Map model) {
        if(br.hasErrors())
            return "program/teacher_add";
        teacherService.addTeacher(t);
        return "redirect:/teacher_list";
    }
    
    @RequestMapping(value = "/teacher_details", method = RequestMethod.GET)
    public ModelAndView showTeacherDetails() {
        ModelAndView model = new ModelAndView("program/teacher_details");
        return model;
    }
    
    @RequestMapping(value = "/teacher_details/{id}", method = RequestMethod.GET)
    public ModelAndView getTeacherDetails(@PathVariable("id") String id) {
        ModelAndView model = new ModelAndView("program/teacher_details");
        return teacherService.lookupTeacher(model, id, true);
    }
    
    @RequestMapping(value = "/teacher_edit", method = RequestMethod.GET)
    public ModelAndView showTeacherEdit() {
        ModelAndView model = new ModelAndView("program/teacher_edit");
        return model;
    }
    
    @RequestMapping(value = "/teacher_edit/{id}", method = RequestMethod.GET)
    public ModelAndView getTeacherEdit(@PathVariable("id") String id) {
        ModelAndView model = new ModelAndView("program/teacher_edit");
        return teacherService.lookupTeacher(model, id, false);
    }
    
    @RequestMapping(value = "/teacher_edit/{id}", method = RequestMethod.POST)
    public String postTeacherEdit(@PathVariable("id") String id, @ModelAttribute("command") @Valid Teacher t, BindingResult br, Map model) {
        if(br.hasErrors())
            return "/program/teacher_edit/" + id;
        t.setTeacherID(Integer.parseInt(id));
        teacherService.updateTeacher(t);
        return "redirect:/teacher_list";
    }
    
    @RequestMapping(value = "/teacher_delete/{id}", method = RequestMethod.POST)
    public String postDeleteTeacher(@PathVariable("id") Integer id) {
        teacherService.removeTeacher(id);
        return "redirect:/teacher_list";
    }
}
