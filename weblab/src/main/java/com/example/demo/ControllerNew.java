
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ControllerNew {

    @RequestMapping("main")
    public ModelAndView main() {
        System.out.println("main");
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("test");
        return new ModelAndView("main");
    }
}
