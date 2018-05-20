package com.mmall.controller.portal;

import com.mathworks.toolbox.javabuilder.MWException;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.CheckWithBLOBs;
import com.mmall.pojo.Model;
import com.mmall.service.ICheckService;
import com.mmall.service.IModelService;
import com.mmall.util.MatlabUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/matlab")
public class MatlabController {
    @Autowired
    private ICheckService checkService;
    @Autowired
    private IModelService modelService;

    @RequestMapping("/getResult.do")
    @ResponseBody
    public ServerResponse<String> getMat(Integer modelId, Integer checkId, HttpServletRequest request) throws MWException, IOException {

        Model model = (Model) modelService.selectByPrimaryKey(modelId).getData();
        CheckWithBLOBs check = (CheckWithBLOBs) checkService.selectByPrimaryKeyWithBolgs(checkId).getData();
        String s = MatlabUtil.process(check, model, request);
        check.setCheckResult(s);

        checkService.saveOrUpdateCheck(check);

        return ServerResponse.createBySuccess(s);
    }
}
