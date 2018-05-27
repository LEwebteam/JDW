package com.mmall.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller

public class LoginController {
    @RequestMapping("/login.do")
    public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "/html/login";
    }
}
