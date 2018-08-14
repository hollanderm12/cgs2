package controller;

import javax.validation.Valid;
import model.Result;
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
import service.ResultService;

@Controller
public class ResultController {
    @Autowired
    private ResultService resultService;
    
    @ModelAttribute("command")
    public Result createResultModel() {
        return new Result();
    }
    
    @RequestMapping(value = { "/result_list", "/teachers" }, method = RequestMethod.GET)
    public ModelAndView showListResults() {
        ModelAndView model = new ModelAndView("result/result_list");
        model.addObject("resultList", resultService.listResults());
        return model;
    }
       
    @RequestMapping(value = "/result_add", method = RequestMethod.GET)
    public String getAddResult(Model model) {
        model = resultService.populateDropdowns(model);
        return "result/result_add";
    }
        
    @RequestMapping(value = "/result_add", method = RequestMethod.POST)
    public String postAddResult(@ModelAttribute("command") @Valid Result r, BindingResult br, Model model, RedirectAttributes redir) {
        if(br.hasErrors())
            return "result/result_add";
        if(resultService.addResult(r) == 0) {
            model = resultService.populateDropdowns(model);
            model.addAttribute("studentNotRegistered", true);
            model.addAttribute("badStudentID", r.getStudentID());
            model.addAttribute("badCourseID", r.getCourseID());
            return "result/result_add";
        }
        redir.addFlashAttribute("statusMsg", "Result ID " + r.getResultID() + " was added successfully.");
        return "redirect:/result_list";
    }
    
    @RequestMapping(value = "/result_edit", method = RequestMethod.GET)
    public ModelAndView showResultEdit() {
        ModelAndView model = new ModelAndView("result/result_edit");
        return model;
    }
    
    @RequestMapping(value = "/result_edit/{id}", method = RequestMethod.GET)
    public String getResultEdit(@PathVariable("id") String id, Model model) {
        model = resultService.lookupResult(model, id);
        return "result/result_edit";
    }
    
    @RequestMapping(value = "/result_edit/{id}", method = RequestMethod.POST)
    public String postResultEdit(@PathVariable("id") Integer id, @ModelAttribute("command") @Valid Result r, BindingResult br, Model model, RedirectAttributes redir) {
        if(br.hasErrors())
            return "result/result_edit";
        r.setResultID(id);
        if(resultService.updateResult(r) == 0) {
            redir.addFlashAttribute("id", id);
            redir.addFlashAttribute("studentNotRegistered", true);
            redir.addFlashAttribute("badStudentID", r.getStudentID());
            redir.addFlashAttribute("badCourseID", r.getCourseID());
            return "redirect:/result_edit/{id}";
        }
        redir.addFlashAttribute("statusMsg", "Result ID " + r.getResultID() + " was edited successfully.");
        return "redirect:/result_list";       
    }

    @RequestMapping(value = "/result_delete/{id}", method = RequestMethod.POST)
    public String postDeleteResult(@PathVariable("id") Integer id, RedirectAttributes redir) {
        Result r = resultService.getResultById(id);
        resultService.removeResult(id, r);
        redir.addFlashAttribute("statusMsg", "Result ID " + r.getResultID() + " was deleted successfully.");
        return "redirect:/result_list";
    }
}
