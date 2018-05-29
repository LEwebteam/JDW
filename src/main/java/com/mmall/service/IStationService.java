package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Station;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by geely
 */
public interface IStationService {


    ServerResponse selectByPrimaryKey(Integer stationId);

    ServerResponse saveOrUpdateStation(Station station);


    ServerResponse<PageInfo> getStationtList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchStation(String stationName, Integer stationId, String stationType, String stationLevel, Integer office, Date startTime, Date endTime, int pageNum, int pageSize);

    ServerResponse deleteStation(Integer stationId);


    ServerResponse<PageInfo> getStationtList(Integer officeId, int pageNum, int pageSize);

    ServerResponse<String> getDrawing(Integer stationId, HttpServletRequest request);

    ServerResponse getAll();

    ServerResponse getAllByOffice(Integer officeId);
}
