package com.ex.FitApp.handling;

import com.ex.FitApp.handling.exceptions.PageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({PageNotFoundException.class})
    public ModelAndView catchNotFoundException(PageNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("errors/error-404");
        return modelAndView;
    }
}
