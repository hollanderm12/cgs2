package controller;

import javax.validation.Valid;
import model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping(value = { "/result_list", "/teachers" })
    public ModelAndView showListResults() {
        return new ModelAndView("result/result_list", "resultList", resultService.listResults());
    }
       
    @GetMapping(value = "/result_add")
    public ModelAndView getAddResult() {
        return resultService.populateDropdowns(new ModelAndView("result/result_add", "command", new Result()));
    }
        
    @PostMapping(value = "/result_add")
    public ModelAndView postAddResult(@ModelAttribute("command") @Valid Result r, BindingResult br, RedirectAttributes redir) {
        if(br.hasErrors())
            return resultService.populateDropdowns(new ModelAndView("result/result_add"));
        if(!resultService.addResult(r)) {
            return resultService.populateDropdowns(new ModelAndView("result/result_add", "warningMsg", 
                    "Student ID " + r.getStudentID() + " is not registered in course ID " + r.getCourseID() + 
                    ". Please verify the student's course registrations and try again."));
        }
        redir.addFlashAttribute("statusMsg", "Result ID " + r.getResultID() + " was added successfully.");
        return new ModelAndView("redirect:/result_list");
    }
    
    @GetMapping(value = "/result_edit")
    public ModelAndView showResultEdit() {
        return new ModelAndView("result/result_edit");
    }
    
    @GetMapping(value = "/result_edit/{id}")
    public ModelAndView getResultEdit(@PathVariable("id") String id) {
        return resultService.lookupResult(new ModelAndView("result/result_edit"), id);
    }
    
    @PostMapping(value = "/result_edit/{id}")
    public ModelAndView postResultEdit(@PathVariable("id") Integer id, @ModelAttribute("command") @Valid Result r, BindingResult br, RedirectAttributes redir) {
        if(br.hasErrors())
            return resultService.lookupResult(new ModelAndView("result/result_edit"), id.toString());
        r.setResultID(id);
        if(!resultService.updateResult(r)) {
            redir.addFlashAttribute("warningMsg", 
                    "Student ID " + r.getStudentID() + " is not registered in course ID " + r.getCourseID() + 
                    ". Please verify the student's course registrations and try again.");
            return new ModelAndView("redirect:/result_edit/" + id);
        }
        redir.addFlashAttribute("statusMsg", "Result ID " + r.getResultID() + " was edited successfully.");
        return new ModelAndView("redirect:/result_edit/" + id);       
    }

    @PostMapping(value = "/result_delete/{id}")
    public ModelAndView postDeleteResult(@PathVariable("id") Integer id, RedirectAttributes redir) {
        Result r = resultService.getResultById(id);
        resultService.removeResult(id, r);
        redir.addFlashAttribute("statusMsg", "Result ID " + r.getResultID() + " was deleted successfully.");
        return new ModelAndView("redirect:/result_list");
    }
}
