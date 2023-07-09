package com.example.demo.WEB;


import com.example.demo.model.ObjectNotFoundException;
import com.example.demo.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjectNotFoundAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView onObjectNotFound(ObjectNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");

        modelAndView.addObject("objectType", e.getObjectType());

        modelAndView.addObject("objectId", e.getObjectId());
        return modelAndView;
    }


}
