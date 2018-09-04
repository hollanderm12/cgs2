package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController 
{
    @GetMapping(value = {"/index.html", "index", "home"} )
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }
    
    @GetMapping(value = {"log_in", "login" } )
    public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) String error) {
        if(error != null)
            return new ModelAndView("login/login", "error", "Invalid username or password. Please try again.");
        return new ModelAndView("login/login");
    }
}