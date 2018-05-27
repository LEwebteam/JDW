package com.mmall.controller.portal;

import com.mmall.pojo.Check;
import com.mmall.service.ICheckerService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/html")
public class IndexController {

    @Autowired
    private ICheckerService iCheckerService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping("/welcome.do")
    @ResponseBody
    public ModelAndView welcome(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/loginwelcome.do")
    @ResponseBody
    public ModelAndView login(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping("/test.do")
    @ResponseBody
    public void login(HttpSession session, HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        response.sendRedirect("/user/list.do");
    }

    @RequestMapping("/testcheck.do")
    @ResponseBody
    public ModelAndView check(HttpSession session, Check check) {

        System.out.println(check.getStationId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
