package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Station;
import com.mmall.pojo.User;
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
@RequestMapping("/station")
public class StationController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IStationService iStationService;
    @Autowired
    private IFileService iFileService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Station station) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //管理员直接添加---office是否固定选项？
            return iStationService.saveOrUpdateStation(station);
        } else {
            //不是管理员则设置officeid
            station.setOffice(user.getOfficeId());
            return iStationService.saveOrUpdateStation(station);
        }
    }
    @RequestMapping("get.do")
    @ResponseBody
    public ServerResponse getByid(HttpSession session, @RequestParam(required = true) Integer stationId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return iStationService.selectByPrimaryKey(stationId);
    }

    @RequestMapping("getAll.do")
    @ResponseBody
    public ServerResponse getall(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iStationService.getAll();
        }
        else{
            return iStationService.getAllByOffice(user.getOfficeId());
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return iStationService.getStationtList(pageNum, pageSize);
        }

        return iStationService.getStationtList(user.getOfficeId(), pageNum, pageSize);
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> productSearch(HttpSession session, @RequestParam(required = false) String stationName,
                                                  @RequestParam(required = false)Integer stationId,
                                                  @RequestParam(required = false)String stationType,
                                                  @RequestParam(required = false)String stationLevel,
                                                  @RequestParam(required = false)Integer office,
                                                  @RequestParam(required = false)Date startTime,
                                                  @RequestParam(required = false)Date endTime, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return iStationService.searchStation(stationName, stationId, stationType, stationLevel, office, startTime, endTime, pageNum, pageSize);
        } else {
            office = user.getOfficeId();
            return iStationService.searchStation(stationName, stationId, stationType, stationLevel, office, startTime, endTime, pageNum, pageSize);
        }
    }

    @RequestMapping(value = "upload.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        String path = request.getSession().getServletContext().getRealPath("/upload");
        String targetFileName = iFileService.upload(file, path);
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/JDW/upload/" + targetFileName;
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);//存返回的文件名
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }

    @RequestMapping("getdrawing.do")
    @ResponseBody
    public ServerResponse<String> getDrawing(HttpSession session, Integer stationId,HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return iStationService.getDrawing(stationId,request);
    }





}
