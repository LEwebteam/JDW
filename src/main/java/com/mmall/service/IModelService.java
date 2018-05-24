package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by geely
 */
public interface IModelService {


    ServerResponse selectByPrimaryKey(Integer modelId);

    ServerResponse saveOrUpdateModel(Model model, String deletiInfo);


    ServerResponse<PageInfo> getModelList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchModel(String stationName, Integer stationId, String stationType, String stationLevel, Integer office, Date startTime, Date endTime, int pageNum, int pageSize);

    ServerResponse deleteModel(Integer modelId);



    ServerResponse<PageInfo> getModelListBystation(Integer stationId, int pageNum, int pageSize);

    ServerResponse<String> getDrawing(Integer stationId, HttpServletRequest request);
}
