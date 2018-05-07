package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.StationMapper;
import com.mmall.pojo.Checker;
import com.mmall.pojo.Station;
import com.mmall.service.IStationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class StationServiceImpl implements IStationService {

    @Autowired
    StationMapper stationMapper;


    @Override
    public ServerResponse
    selectByPrimaryKey(Integer stationId){
        Station station= stationMapper.selectByPrimaryKey(stationId);
        if(station!=null){
            return ServerResponse.createBySuccess(station);
        }
        return ServerResponse.createByErrorMessage("无信息");
    }


    @Override
    public ServerResponse saveOrUpdateStation(Station station) {
        if (station != null) {
            if (station.getId() != null) {
                int rowCount = stationMapper.updateByPrimaryKey(station);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("站点更新成功");
                }
                return ServerResponse.createBySuccess("站点更新失败");
            } else {
                int rowCount = stationMapper.insert(station);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("站点新增成功");
                }
                return ServerResponse.createBySuccess("站点新增失败");
            }
        }

        return ServerResponse.createByErrorMessage("保存失败");
    }

    @Override
    public ServerResponse<PageInfo> getStationtList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Station> stationList = stationMapper.selectAll();
        PageInfo pageInfo = new PageInfo((stationList));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> searchStation(String stationName, Integer stationId, String stationType, String stationLevel, Integer office, Date startTime, Date endTime, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Station> stationList = stationMapper.select(stationName, stationId, stationType, stationLevel, office, startTime, endTime);
        PageInfo pageInfo = new PageInfo(stationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteStation(Integer stationId) {
       Integer rowCount = stationMapper.deleteByPrimaryKey(stationId);
       if(rowCount>0){

           return ServerResponse.createBySuccess("删除成功");
       }else{
           return ServerResponse.createByErrorMessage("删除失败");
       }
    }

    @Override
    public ServerResponse<PageInfo> getStationtList(Integer officeId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Station> stationList = stationMapper.selectByOfficeId(officeId);
        PageInfo pageInfo = new PageInfo(stationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> getDrawing(Integer stationId, HttpServletRequest request) {
        if(stationId==null){
            return ServerResponse.createByErrorMessage("请输入变电站id");
        }

        String targetFileName = stationMapper.selectByPrimaryKey(stationId).getDrawingFileName();

        if(StringUtils.isEmpty(targetFileName)){
            return ServerResponse.createBySuccess("没有图纸");
        }
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/JDW/upload/" + targetFileName;

        return ServerResponse.createBySuccess(url);
    }
}
