package com.mmall.service.impl;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.DeleteinfoMapper;
import com.mmall.dao.ModelMapper;
import com.mmall.pojo.Deleteinfo;
import com.mmall.pojo.Model;
import com.mmall.service.IDeleteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeleteInfoServiceImpl implements IDeleteInfoService {

@Autowired
DeleteinfoMapper deleteinfoMapper;
    @Override
    public ServerResponse selectByPrimaryKey(Integer modelId) {
        return null;
    }

    @Override
    public ServerResponse saveOrUpdateDeleteInfo(Deleteinfo deleteinfo) {
       int i= deleteinfoMapper.insert(deleteinfo);
        return i>0?ServerResponse.createBySuccess():ServerResponse.createByErrorMessage("成功");
    }

    @Override
    public List<Deleteinfo> getDelteInfoList(int modelId) {

        return deleteinfoMapper.selectByModelId(modelId);
    }

    @Override
    public ServerResponse<PageInfo> searchModel(String stationName, Integer stationId, String stationType, String stationLevel, Integer office, Date startTime, Date endTime, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse deleteModel(Integer modelId) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getModelListBystation(Integer stationId, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse<String> getDrawing(Integer stationId, HttpServletRequest request) {
        return null;
    }
}

