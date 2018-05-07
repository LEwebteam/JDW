package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Checker;
import com.mmall.pojo.User;
import com.mmall.service.ICheckerService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checker")
public class CheckerController {

    @Autowired
    private ICheckerService iCheckerService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse checkerSave(HttpSession session, Checker checker) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //管理员直接添加---office是否固定选项？
            return iCheckerService.saveOrUpdateChecker(checker);
        } else {
            //不是管理员则设置officeid
            checker.setOffice(user.getOfficeId());
            return iCheckerService.saveOrUpdateChecker(checker);
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
            return iCheckerService.getCheckertList(pageNum, pageSize);
        }

        return iCheckerService.getCheckertList(user.getOfficeId(), pageNum, pageSize);
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> checkerSearch(HttpSession session, String checkerName, Integer office, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");

        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return iCheckerService.searchChecker(checkerName, office, pageNum, pageSize);
        } else {
            office = user.getOfficeId();
            return iCheckerService.searchChecker(checkerName, office, pageNum, pageSize);
        }
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse<PageInfo> checkerDelete(HttpSession session, Integer checkerId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return iCheckerService.deleteChecker(checkerId);
        } else {
            int office = user.getOfficeId();
            Checker checker = iCheckerService.selectByPrimaryKey(checkerId).getData();
            if (checker != null) {
                if (checker.getOffice() == office) {
                    return iCheckerService.deleteChecker(checkerId);
                }else {
                    return ServerResponse.createByErrorMessage("非本单位检测员不能删除");

                }
            }
        }
        return ServerResponse.createByErrorMessage("删除失败");

    }


}
