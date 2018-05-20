package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Check;
import com.mmall.pojo.CheckWithBLOBs;
import com.mmall.pojo.Station;
import com.mmall.pojo.User;
import com.mmall.service.ICheckService;
import com.mmall.service.IFileService;
import com.mmall.service.IUserService;
import com.mmall.vo.OriginFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by geely
 */

@Controller
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICheckService iCheckService;
    @Autowired
    private IFileService iFileService;

    @RequestMapping("sss.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Check check, @RequestParam("filename") String filename) throws IOException {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        CheckWithBLOBs check1 = new CheckWithBLOBs();
        check1.setId(check.getId());
        check1.setStationId(check.getStationId());
        check1.setModelId(check.getModelId());
        check1.setCheckerId(check.getCheckerId());
        check1.setCheckTime(check.getCheckTime());
        check1.setLineMethod(check.getLineMethod());
        check1.setLineDetail(check.getLineDetail());

        check1.setNote(filename);
        return iCheckService.saveOrUpdateCheck(check1);
    }


    /*@RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Integer stationId, @RequestParam(value = "id", required = false) Integer id,
                                      Integer modelId, Integer checkerId, Date checkTime,
                                      String lineMethod, String lineDetail, @RequestParam("filename") String filename) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        CheckWithBLOBs check1 = new CheckWithBLOBs();
        check1.setId(id);
        check1.setStationId(stationId);
        check1.setModelId(modelId);
        check1.setCheckerId(checkerId);
        check1.setCheckTime(checkTime);
        check1.setLineMethod(lineMethod);
        check1.setLineDetail(lineDetail);
        check1.setNote(filename);
        return iCheckService.saveOrUpdateCheck(check1);

*/

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "stationId", required = false) Integer stationId, Integer modelId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return iCheckService.getCheckList(pageNum, pageSize);
        }

        return iCheckService.getCheckList(stationId, modelId, pageNum, pageSize);
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> productSearch(HttpSession session, String checkName, Integer checkId, String checkType, String checkLevel, Integer office, Date startTime, Date endTime, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return iCheckService.searchCheck(checkName, checkId, checkType, checkLevel, office, startTime, endTime, pageNum, pageSize);
        } else {
            office = user.getOfficeId();
            return iCheckService.searchCheck(checkName, checkId, checkType, checkLevel, office, startTime, endTime, pageNum, pageSize);
        }
    }

    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        String path = request.getSession().getServletContext().getRealPath("/upload");
        String targetFileName = iFileService.uploadtxt(file, path);
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);//存返回的文件名
        return ServerResponse.createBySuccess(fileMap);
    }

    /*@RequestMapping(value = "uploadData.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadOriginData(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request,) {
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
    }*/



  /*  @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = Maps.newHashMap();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            resultMap.put("success", false);
            resultMap.put("msg", "请登录管理员");
            return resultMap;
        }
        //富文本中对于返回值有自己的要求,我们使用是simditor所以按照simditor的要求进行返回
//        {
//            "success": true/false,
//                "msg": "error message", # optional
//            "file_path": "[real file path]"
//        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file, path);
            if (StringUtils.isBlank(targetFileName)) {
                resultMap.put("success", false);
                resultMap.put("msg", "上传失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
            resultMap.put("success", true);
            resultMap.put("msg", "上传成功");
            resultMap.put("file_path", url);
            response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
            return resultMap;
        } else {
            resultMap.put("success", false);
            resultMap.put("msg", "无权限操作");
            return resultMap;
        }
    }*/


}
