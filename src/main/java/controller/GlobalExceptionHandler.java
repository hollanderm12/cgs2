package controller;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import util.Level;
import util.LogThis;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    final static Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(HibernateException.class)
    public ModelAndView handleHibernateException(Exception ex) {
        LogThis.log(LOGGER, Level.ERROR, "Hibernate exception occurred. Reason: " + ex.getMessage());
        return new ModelAndView("errors/500_hibernate_exception");
    }
}