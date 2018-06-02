package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CheckMapper;
import com.mmall.dao.CheckerMapper;
import com.mmall.dao.ModelMapper;
import com.mmall.dao.StationMapper;
import com.mmall.pojo.*;
import com.mmall.service.ICheckService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.MyUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CheckServiceImpl implements ICheckService {

    @Autowired
    CheckMapper checkMapper;
    @Autowired
    CheckerMapper checkerMapper;
    @Autowired
    StationMapper stationMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ServerResponse
    selectByPrimaryKey(Integer checkId) {
        CheckWithBLOBs check = checkMapper.selectByPrimaryKey(checkId);
        check.setTransformData(null);
        check.setOriginalData(null);
        if (check != null) {
            return ServerResponse.createBySuccess(check);
        }
        return ServerResponse.createByErrorMessage("无信息");
    }

    @Override
    public ServerResponse selectByPrimaryKeyWithBolgs(Integer checkId) {
        CheckWithBLOBs check = checkMapper.selectByPrimaryKey(checkId);
        check.setOriginalData(null);
        check.setTransformData(null);

        if (check != null) {
            List<Checker> checkerList = checkerMapper.selectAll();
            List<Model> modelList = modelMapper.selectAll();
            List<Station> stationList = stationMapper.selectAll();

            CheckOV checkOV = new CheckOV(check);
            for (Station station : stationList) {
                if (station.getId() == check.getStationId()) {
                    checkOV.stationname = station.getName();
                    break;
                }
            }
            for (Model model : modelList) {
                if (model.getId() == check.getModelId()) {
                    checkOV.modelname = model.getModelName();
                    break;
                }
            }
            for (Checker checker : checkerList) {
                if (checker.getId() == check.getCheckerId()) {
                    checkOV.checkername = checker.getName();
                    break;
                }
            }

            checkOV.checkTimeString= DateTimeUtil.dateToStr(checkOV.getCheckTime());
            return ServerResponse.createBySuccess(checkOV);


        }
        return ServerResponse.createByErrorMessage("无信息");
    }

    @Override
    public ServerResponse saveOrUpdateCheck(CheckWithBLOBs check) {
        if (check != null) {
            if (check.getId() != null) {
                int rowCount = checkMapper.updateByPrimaryKeySelective(check);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("检测更新成功", check.getId());
                }
                return ServerResponse.createByErrorMessage("保存失败");
            } else {
//                check.setId((int) MyUUIDUtil.getUUIDLong());
                check.setId((int) (Math.abs(UUID.randomUUID().hashCode())));
                int rowCount = checkMapper.insertSelective(check);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("检测新增成功", check.getId());
                }
                return ServerResponse.createByErrorMessage("保存失败");
            }
        }

        return ServerResponse.createByErrorMessage("保存失败");
    }

    @Override
    public ServerResponse<PageInfo> getCheckList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CheckWithBLOBs> checkList = checkMapper.selectAll();
        if (checkList.size() == 0) {
            return ServerResponse.createBySuccess("无数据", null);
        }
        for (CheckWithBLOBs check : checkList) {
            check.setOriginalData(null);
            check.setTransformData(null);
        }
        List<Checker> checkerList = checkerMapper.selectAll();
        List<Model> modelList = modelMapper.selectAll();
        List<Station> stationList = stationMapper.selectAll();

        List<CheckOV> checkOVS = new ArrayList<>();
        for (CheckWithBLOBs check : checkList) {
            CheckOV checkOV = new CheckOV(check);
            for (Station station : stationList) {
                if (station.getId() == check.getStationId()) {
                    checkOV.stationname = station.getName();
                    break;
                }
            }
            for (Model model : modelList) {
                if (model.getId() == check.getModelId()) {
                    checkOV.modelname = model.getModelName();
                    break;
                }
            }
            for (Checker checker : checkerList) {
                if (checker.getId() == check.getCheckerId()) {
                    checkOV.checkername = checker.getName();
                    break;
                }
            }
            checkOV.checkTimeString=DateTimeUtil.dateToStr(checkOV.getCheckTime());
            checkOVS.add(checkOV);
        }
        PageInfo pageInfo = new PageInfo((checkOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }


    @Override
    public ServerResponse deleteCheck(Integer checkId) {
        Integer rowCount = checkMapper.deleteByPrimaryKey(checkId);
        if (rowCount > 0) {

            return ServerResponse.createBySuccess("删除成功");
        } else {
            return ServerResponse.createByErrorMessage("删除失败");
        }


    }

    @Override
    public ServerResponse<PageInfo> getCheckList(Integer officeId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CheckWithBLOBs> checkList = checkMapper.select(officeId);
        if (checkList.size() == 0) {
            return ServerResponse.createBySuccess("无数据", null);
        }
        for (CheckWithBLOBs check : checkList) {
            check.setOriginalData(null);
            check.setTransformData(null);
        }
        List<Checker> checkerList = checkerMapper.selectAll();
        List<Model> modelList = modelMapper.selectAll();
        List<Station> stationList = stationMapper.selectAll();

        List<CheckOV> checkOVS = new ArrayList<>();
        for (CheckWithBLOBs check : checkList) {
            CheckOV checkOV = new CheckOV(check);
            for (Station station : stationList) {
                if (station.getId() == check.getStationId()) {
                    checkOV.stationname = station.getName();
                    break;
                }
            }
            for (Model model : modelList) {
                if (model.getId() == check.getModelId()) {
                    checkOV.modelname = model.getModelName();
                    break;
                }
            }
            for (Checker checker : checkerList) {
                if (checker.getId() == check.getCheckerId()) {
                    checkOV.checkername = checker.getName();
                    break;
                }
            }
            checkOV.checkTimeString=DateTimeUtil.dateToStr(checkOV.getCheckTime());
            checkOVS.add(checkOV);
        }
        PageInfo pageInfo = new PageInfo((checkOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> searchCheck(Integer officeId, String stationname, String checkername, Date startTime, Date endTime, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CheckWithBLOBs> checkList = checkMapper.selectBySCO(officeId, stationname, checkername, startTime, endTime);
        if (checkList.size() == 0) {
            return ServerResponse.createBySuccess("无数据", null);
        }
        for (CheckWithBLOBs check : checkList) {
            check.setOriginalData(null);
            check.setTransformData(null);
        }
        List<Checker> checkerList = checkerMapper.selectAll();
        List<Model> modelList = modelMapper.selectAll();
        List<Station> stationList = stationMapper.selectAll();

        List<CheckOV> checkOVS = new ArrayList<>();
        for (CheckWithBLOBs check : checkList) {
            CheckOV checkOV = new CheckOV(check);
            for (Station station : stationList) {
                if (station.getId() == check.getStationId()) {
                    checkOV.stationname = station.getName();
                    break;
                }
            }
            for (Model model : modelList) {
                if (model.getId() == check.getModelId()) {
                    checkOV.modelname = model.getModelName();
                    break;
                }
            }
            for (Checker checker : checkerList) {
                if (checker.getId() == check.getCheckerId()) {
                    checkOV.checkername = checker.getName();
                    break;
                }
            }
            checkOV.checkTimeString=DateTimeUtil.dateToStr(checkOV.getCheckTime());
            checkOVS.add(checkOV);
        }
        PageInfo pageInfo = new PageInfo((checkOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> searchCheckAdmin(String stationname, String checkername, Date startTime, Date endTime, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CheckWithBLOBs> checkList = checkMapper.selectBySC(stationname, checkername, startTime, endTime);
        if (checkList.size() == 0) {
            return ServerResponse.createBySuccess("无数据", null);
        }
        for (CheckWithBLOBs check : checkList) {
            check.setOriginalData(null);
            check.setTransformData(null);
        }
        List<Checker> checkerList = checkerMapper.selectAll();
        List<Model> modelList = modelMapper.selectAll();
        List<Station> stationList = stationMapper.selectAll();

        List<CheckOV> checkOVS = new ArrayList<>();
        for (CheckWithBLOBs check : checkList) {
            CheckOV checkOV = new CheckOV(check);
            for (Station station : stationList) {
                if (station.getId() == check.getStationId()) {
                    checkOV.stationname = station.getName();
                    break;
                }
            }
            for (Model model : modelList) {
                if (model.getId() == check.getModelId()) {
                    checkOV.modelname = model.getModelName();
                    break;
                }
            }
            for (Checker checker : checkerList) {
                if (checker.getId() == check.getCheckerId()) {
                    checkOV.checkername = checker.getName();
                    break;
                }
            }
            checkOV.checkTimeString=DateTimeUtil.dateToStr(checkOV.getCheckTime());
            checkOVS.add(checkOV);
        }
        PageInfo pageInfo = new PageInfo((checkOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }

}

