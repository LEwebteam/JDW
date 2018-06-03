package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Deleteinfo;
import com.mmall.pojo.Model;
import com.mmall.pojo.User;
import com.mmall.service.IDeleteInfoService;
import com.mmall.service.IFileService;
import com.mmall.service.IModelService;
import com.mmall.service.IUserService;
import com.mmall.service.impl.ModelServiceImpl;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by geely
 */

@Controller
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IFileService iFileService;
    @Autowired
    private IModelService iModelService;

    @Autowired
    private IDeleteInfoService deleteInfoService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Model model, String deletiInfo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (model.getRowFixedDistance() == null || model.getRowFixedDistance().equals("0")) {
            ArrayList<Integer> arraylist = new ArrayList<>();
            if (model.getRowMyDistance() != null && model.getRowMyDistance().length() != 0) {
                String[] ss = model.getRowMyDistance().split(",");
                for (String sTmp : ss) {
                    arraylist.add(Integer.parseInt(sTmp));
                }
                Integer sum = 0;
                for (Integer tmpSs : arraylist) {
                    sum += tmpSs;
                }
                model.setRowFixedDistance(String.valueOf(sum / arraylist.size()));
            } else {
                return ServerResponse.createByErrorMessage("请输入间距");
            }
        }
        if (model.getCloumnFixedDistance() == null || model.getCloumnFixedDistance().equals("0")) {
            ArrayList<Integer> arraylist = new ArrayList<>();
            if (model.getCloumnMyDistance() != null && model.getCloumnMyDistance().length() != 0) {
                String[] ss = model.getCloumnMyDistance().split(",");
                for (String sTmp : ss) {
                    arraylist.add(Integer.parseInt(sTmp));
                }
                Integer sum = 0;
                for (Integer tmpSs : arraylist) {
                    sum += tmpSs;
                }
                model.setCloumnFixedDistance(String.valueOf(sum / arraylist.size()));
            } else {
                return ServerResponse.createByErrorMessage("");
            }
        }

        List<String> modelNameList = iModelService.getmmodellName();

        for (String modelName : modelNameList) {
            if (modelName.equals(model.getModelName())) {
                return ServerResponse.createByErrorMessage("模型名称已存在");
            }
        }

        ServerResponse serverResponse = iModelService.saveOrUpdateModel(model, deletiInfo);

        if (iUserService.checkAdminRole(user).isSuccess()) {
            //管理员直接添加---office是否固定选项？

            return serverResponse;
        } else {
            //不是管理员则设置officeid
            return serverResponse;
        }
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "stationId", required = false) Integer stationId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            if (stationId == null)
                return iModelService.getModelList(pageNum, pageSize);
            else {
                return iModelService.getModelListBystation(stationId, pageNum, pageSize);
            }
        }
        if (stationId != null)
            return iModelService.getModelListBystation(stationId, pageNum, pageSize);
        else return ServerResponse.createByErrorMessage("请输入stationid");
    }

    @RequestMapping("get.do")
    @ResponseBody
    public ServerResponse getByid(HttpSession session, @RequestParam(required = true) Integer modelId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        return iModelService.selectByPrimaryKey(modelId);

    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> productSearch(HttpSession session, String modelName, Integer modelId, String modelType, String modelLevel, Integer office, Date startTime, Date endTime, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return iModelService.searchModel(modelName, modelId, modelType, modelLevel, office, startTime, endTime, pageNum, pageSize);
        } else {
            office = user.getOfficeId();
            return iModelService.searchModel(modelName, modelId, modelType, modelLevel, office, startTime, endTime, pageNum, pageSize);
        }
    }

    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
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
    public ServerResponse<String> getDrawing(HttpSession session, Integer modelId, HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return iModelService.getDrawing(modelId, request);
    }


}
