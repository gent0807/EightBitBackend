package com.eightbit.biz.common.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j
public class CommonsExceptionAdvice {
        @ExceptionHandler(Exception.class)
        public String except(Exception ex, Model model) {
            log.error("Exception ........"+ex.getMessage());
            model.addAttribute("exception",ex);
            return "error_page";
        }





}
