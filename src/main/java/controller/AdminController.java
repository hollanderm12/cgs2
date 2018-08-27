package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController 
{
    @GetMapping(value={"", "/index.html", "index"} )
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }
    
    @GetMapping(value = "log_in")
    public ModelAndView getLoginPage() {
        return new ModelAndView("redirect:index");
    }
}