package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Deleteinfo;
import com.mmall.pojo.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by geely
 */
public interface IDeleteInfoService {


    ServerResponse selectByPrimaryKey(Integer modelId);





    ServerResponse saveOrUpdateDeleteInfo(Deleteinfo deleteinfo);

    List<Deleteinfo> getDelteInfoList(int modelId);



    ServerResponse<PageInfo> searchModel(String stationName, Integer stationId, String stationType, String stationLevel, Integer office, Date startTime, Date endTime, int pageNum, int pageSize);

    ServerResponse deleteModel(Integer modelId);



    ServerResponse<PageInfo> getModelListBystation(Integer stationId, int pageNum, int pageSize);

    ServerResponse<String> getDrawing(Integer stationId, HttpServletRequest request);
}
