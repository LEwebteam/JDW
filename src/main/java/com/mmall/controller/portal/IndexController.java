package com.mmall.controller.portal;

import com.mmall.pojo.Check;
import com.mmall.service.ICheckerService;
import com.mmall.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
//@RequestMapping("/html")
public class IndexController {


    @RequestMapping("/jdw.do")
    public ModelAndView welcome(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/html/index");
        return modelAndView;
    }

    //1.选择检测员
    @RequestMapping("/selectperson.do")
    public String selectPerson() {
        return "/html/selectperson";
    }

    @RequestMapping("paramset.do")
    public String paramset() {
        return "/html/paramset";
    }

    @RequestMapping("/selectstation.do")
    public String selectStation() {
        return "/html/selectstation";
    }

    @RequestMapping("selectjiediwang.do")
    public String selectJiediwang() {
        return "/html/selectjiediwang";
    }

    @RequestMapping("stationmanage.do")
    public String stationmanage() {
        return "/html/stationmanage";
    }

    @RequestMapping("addstation.do")
    public String addstation() {
        return "/html/addstation";
    }

    @RequestMapping("editstation.do")
    public String editstation() {
        return "/html/editstation";
    }

    @RequestMapping("jiediwangmanage.do")
    public String jiediwangManage() {
        return "/html/jiediwangmanage";
    }

    @RequestMapping("peoplemanage.do")
    public String peoplemanage() {
        return "/html/peoplemanage";
    }

    @RequestMapping("addperson.do")
    public String addperson() {
        return "/html/addperson";
    }

    @RequestMapping("addjiediwang.do")
    public String addjiediwang() {
        return "/html/addjiediwang";
    }

    @RequestMapping("editperson.do")
    public String editperson() {
        return "/html/editperson";
    }

    @RequestMapping("historyinfor.do")
    public String historyinfor() {
        return "/html/historyinfor";
    }

    @RequestMapping("managersetting.do")
    public String managersetting() {
        return "/html/managersetting";
    }

    @RequestMapping("revisepwd.do")
    public String revisepwd() {
        return "/html/revisepwd";
    }

    @RequestMapping("editjiediwang.do")
    public String editjiediwang() {
        return "/html/editjiediwang";
    }


}
