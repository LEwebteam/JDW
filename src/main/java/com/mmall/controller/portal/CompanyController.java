package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Station;
import com.mmall.pojo.User;
import com.mmall.service.ICompanyService;
import com.mmall.service.IFileService;
import com.mmall.service.IStationService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created by geely
 */

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService iCompanyService;


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return iCompanyService.getAll();
    }
    @RequestMapping("get.do")
    @ResponseBody
    public ServerResponse getByid(HttpSession session, @RequestParam(required = true) Integer officeid){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        return ServerResponse.createBySuccess(iCompanyService.selectByPrimaryKey(officeid));
    }


}
