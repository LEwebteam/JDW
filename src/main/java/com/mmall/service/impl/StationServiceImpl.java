package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CompanyMapper;
import com.mmall.dao.StationMapper;
import com.mmall.pojo.Checker;
import com.mmall.pojo.Company;
import com.mmall.pojo.Station;
import com.mmall.pojo.StationOV;
import com.mmall.service.IStationService;
import com.mmall.util.DateTimeUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class StationServiceImpl implements IStationService {

    @Autowired
    StationMapper stationMapper;
    @Autowired
    CompanyMapper companyMapper;


    @Override
    public ServerResponse
    selectByPrimaryKey(Integer stationId) {
        Station station = stationMapper.selectByPrimaryKey(stationId);
        if (station != null) {
            List<Company> companyList = companyMapper.selectAll();
            StationOV stationOV = new StationOV(station);
            for (Company company : companyList) {
                if (company.getId() == station.getOffice()) {
                    stationOV.officename = company.getCompanyName();
                    break;
                }
            }
            stationOV.bulidTimeString = DateTimeUtil.dateToStr(stationOV.getBuildTime());
            return ServerResponse.createBySuccess(stationOV);
        }
        return ServerResponse.createByErrorMessage("无信息");
    }


    @Override
    public ServerResponse saveOrUpdateStation(Station station) {
        if (station != null) {
            if (station.getId() != null) {
                int rowCount = 0;
                try {
                    rowCount = stationMapper.insert(station);
                }catch (Exception e){
                    return ServerResponse.createByErrorMessage("新增失败，所属局不存在");
                }
                if (rowCount > 0) {
                    return ServerResponse.createBySuccessMessage("站点更新成功");
                }
                return ServerResponse.createByErrorMessage("保存失败");
            } else {
                station.setId(Math.abs(UUID.randomUUID().hashCode()));
                int rowCount = 0;
                try {
                    rowCount = stationMapper.insert(station);
                }catch (Exception e){
                    return ServerResponse.createByErrorMessage("新增失败，所属局不存在");
                }
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("站点新增成功",station.getId());
                }
                return ServerResponse.createByErrorMessage("保存失败");
            }
        }

        return ServerResponse.createByErrorMessage("保存失败");
    }

    @Override
    public ServerResponse<PageInfo> getStationtList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Station> stationList = stationMapper.selectAll();
        List<Company> companyList = companyMapper.selectAll();
        List<StationOV> stationOVS = new ArrayList<>();
        for (Station stationT : stationList) {
            StationOV stationOV = new StationOV(stationT);
            for (Company company : companyList) {
                if (company.getId() == stationT.getOffice()) {
                    stationOV.officename = company.getCompanyName();
                    break;
                }
            }
            stationOV.bulidTimeString = DateTimeUtil.dateToStr(stationOV.getBuildTime());
            stationOVS.add(stationOV);
        }
        PageInfo pageInfo = new PageInfo((stationOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> searchStation(String stationName, Integer stationId, String stationType, String stationLevel, Integer office, Date startTime, Date endTime, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Station> stationList = stationMapper.select(stationName, stationId, stationType, stationLevel, office, startTime, endTime);
        List<Company> companyList = companyMapper.selectAll();
        List<StationOV> stationOVS = new ArrayList<>();
        for (Station stationT : stationList) {
            StationOV stationOV = new StationOV(stationT);
            for (Company company : companyList) {
                if (company.getId() == stationT.getOffice()) {
                    stationOV.officename = company.getCompanyName();
                    break;
                }
            }
            stationOV.bulidTimeString = DateTimeUtil.dateToStr(stationOV.getBuildTime());
            stationOVS.add(stationOV);
        }
        PageInfo pageInfo = new PageInfo((stationOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteStation(Integer stationId) {
        Integer rowCount = stationMapper.deleteByPrimaryKey(stationId);
        if (rowCount > 0) {

            return ServerResponse.createBySuccess("删除成功");
        } else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public ServerResponse<PageInfo> getStationtList(Integer officeId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Station> stationList = stationMapper.selectByOfficeId(officeId);
        List<Company> companyList = companyMapper.selectAll();
        List<StationOV> stationOVS = new ArrayList<>();
        for (Station stationT : stationList) {
            StationOV stationOV = new StationOV(stationT);
            for (Company company : companyList) {
                if (company.getId() == stationT.getOffice()) {
                    stationOV.officename = company.getCompanyName();
                    break;
                }
            }
            stationOV.bulidTimeString = DateTimeUtil.dateToStr(stationOV.getBuildTime());
            stationOVS.add(stationOV);
        }
        PageInfo pageInfo = new PageInfo((stationOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> getDrawing(Integer stationId, HttpServletRequest request) {
        if (stationId == null) {
            return ServerResponse.createByErrorMessage("请输入变电站id");
        }

        String targetFileName = stationMapper.selectByPrimaryKey(stationId).getDrawingFileName();

        if (StringUtils.isEmpty(targetFileName)) {
            return ServerResponse.createBySuccess("没有图纸");
        }
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/JDW/upload/" + targetFileName;

        return ServerResponse.createBySuccess(url);
    }

    @Override
    public ServerResponse getAll() {
        return ServerResponse.createBySuccess(stationMapper.selectAll());
    }

    @Override
    public ServerResponse getAllByOffice(Integer officeId) {
        return ServerResponse.createBySuccess(stationMapper.selectByOfficeId(officeId));
    }
}
