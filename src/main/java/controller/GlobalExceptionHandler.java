package controller;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HibernateException.class)
    public ModelAndView handleHibernateException(HttpServletRequest request, Exception ex) {
        System.out.println("Hibernate exception occured. Path: " + request.getRequestURL());
        return new ModelAndView("error");
    }
}
