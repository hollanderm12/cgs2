package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController 
{
    @RequestMapping(value={"", "/index.html", "index"}, method = RequestMethod.GET)
    public ModelAndView getIndex() 
    {
        ModelAndView model = new ModelAndView("index");
        return model;
    }
}

//Hello there!
