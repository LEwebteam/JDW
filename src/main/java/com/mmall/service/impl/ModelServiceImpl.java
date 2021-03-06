package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CompanyMapper;
import com.mmall.dao.ModelMapper;
import com.mmall.dao.StationMapper;
import com.mmall.pojo.*;
import com.mmall.service.IModelService;
import com.mmall.util.MyUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ModelServiceImpl implements IModelService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StationMapper stationMapper;
    @Autowired
    DeleteInfoServiceImpl deleteInfoService;

    @Override
    public ServerResponse
    selectByPrimaryKey(Integer modelId) {
        Model model = modelMapper.selectByPrimaryKey(modelId);
        if (model != null) {
            List<Station> stationList = stationMapper.selectAll();
            ModelOV modelOV = new ModelOV(model);
            for (Station station : stationList) {
                if (station.getId() == model.getStationId()) {
                    modelOV.stationName = station.getName();
                    break;
                }
            }
            return ServerResponse.createBySuccess(modelOV);
        }
        return ServerResponse.createByErrorMessage("无信息");
    }

    @Override
    public ServerResponse saveOrUpdateModel(Model model, String deletiInfo) {
        if (model != null) {
            if (model.getId() != null) {
                int rowCount = modelMapper.updateByPrimaryKeySelective(model);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("模型更新成功", model.getId());
                }
                return ServerResponse.createByErrorMessage("保存失败");
            } else {
//                Integer modelId= (int)(MyUUIDUtil.getUUIDLong());
                Integer modelId = Math.abs(UUID.randomUUID().hashCode());
                model.setId(modelId);
                int rowCount = modelMapper.insertSelective(model);
                for (String s : deletiInfo.split(";")) {
                    for (int i = 0; i < s.split(":").length; i++) {
                        Deleteinfo deleteinfo = new Deleteinfo();
                        deleteinfo.setModelId(modelId);
                        deleteinfo.setType(s.split(":")[0]);
                        deleteinfo.setNode(s.split(":")[1]);
                        deleteinfo.setLine(s.split(":")[2]);
                        deleteinfo.setId(UUID.randomUUID().hashCode());
                        deleteInfoService.saveOrUpdateDeleteInfo(deleteinfo);
                    }
                }

                if (rowCount > 0)
                    return ServerResponse.createBySuccess("新增成功", model.getId());
                return ServerResponse.createByErrorMessage("保存失败");
            }
        }

        return ServerResponse.createByErrorMessage("保存失败");
    }

    @Override
    public ServerResponse<PageInfo> getModelList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Model> modelList = modelMapper.selectAll();
        List<Station> stationList = stationMapper.selectAll();
        List<ModelOV> modelOVS = new ArrayList<>();
        for (Model model : modelList) {
            ModelOV modelOV = new ModelOV(model);
            for (Station station : stationList) {
                if (station.getId() == model.getStationId()) {
                    modelOV.stationName = station.getName();
                    break;
                }
            }
            modelOVS.add(modelOV);
        }
        PageInfo pageInfo = new PageInfo((modelOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> searchModel(String modelName, Integer modelId, String modelType, String modelLevel, Integer office, Date startTime, Date endTime, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //  List<Model> modelList = modelMapper.select(modelName, modelId, modelType, modelLevel, office, startTime, endTime);
        //   PageInfo pageInfo = new PageInfo(modelList);
        //  return ServerResponse.createBySuccess(pageInfo);
        return null;
    }

    @Override
    public ServerResponse deleteModel(Integer modelId) {
        Integer rowCount = modelMapper.deleteByPrimaryKey(modelId);
        if (rowCount > 0) {

            return ServerResponse.createBySuccess("删除成功");
        } else {
            return ServerResponse.createByErrorMessage("删除失败");
        }


    }

    @Override
    public ServerResponse<PageInfo> getModelListBystation(Integer stationId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Model> modelList = modelMapper.selectByStationId(stationId);
        List<Station> stationList = stationMapper.selectAll();
        List<ModelOV> modelOVS = new ArrayList<>();
        for (Model model : modelList) {
            ModelOV modelOV = new ModelOV(model);
            for (Station station : stationList) {
                if (station.getId() == model.getStationId()) {
                    modelOV.stationName = station.getName();
                    break;
                }
            }
            modelOVS.add(modelOV);
        }
        PageInfo pageInfo = new PageInfo((modelOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> getDrawing(Integer modelId, HttpServletRequest request) {
        return null;
    }

    @Override
    public List<String> getmmodellName() {
        return modelMapper.getModelName();
    }
}

