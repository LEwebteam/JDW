package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.CheckWithBLOBs;

import java.util.Date;

/**
 * Created by geely
 */
public interface ICheckService {


    ServerResponse selectByPrimaryKey(Integer checkId);

    ServerResponse
    selectByPrimaryKeyWithBolgs(Integer checkId);

    ServerResponse saveOrUpdateCheck(CheckWithBLOBs check);

    ServerResponse<PageInfo> getCheckList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchCheck(Integer officeId, String  stationname, String checkername,Date startTime,Date endTime, int pageNum, int pageSize);

    ServerResponse deleteCheck(Integer checkId);

    ServerResponse<PageInfo> getCheckList(Integer officeId, int pageNum, int pageSize);

    ServerResponse<PageInfo> searchCheckAdmin(String stationname, String checkername, Date startTime, Date endTime, int pageNum, int pageSize);
}
