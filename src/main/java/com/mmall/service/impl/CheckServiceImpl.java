package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CheckMapper;
import com.mmall.pojo.Check;
import com.mmall.pojo.CheckWithBLOBs;
import com.mmall.service.ICheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CheckServiceImpl implements ICheckService {

    @Autowired
    CheckMapper checkMapper;


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
    public ServerResponse
    selectByPrimaryKeyWithBolgs(Integer checkId) {
        CheckWithBLOBs check = checkMapper.selectByPrimaryKey(checkId);

        if (check != null) {
            return ServerResponse.createBySuccess(check);
        }
        return ServerResponse.createByErrorMessage("无信息");
    }

    @Override
    public ServerResponse saveOrUpdateCheck(CheckWithBLOBs check) {
        if (check != null) {
            if (check.getId() != null) {
                int rowCount = checkMapper.updateByPrimaryKeySelective(check);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("检测更新成功");
                }
                return ServerResponse.createBySuccess("检测更新失败");
            } else {
                check.setId(UUID.randomUUID().hashCode());
                int rowCount = checkMapper.insertSelective(check);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("检测新增成功");
                }
                return ServerResponse.createBySuccess("检测新增失败");
            }
        }

        return ServerResponse.createByErrorMessage("保存失败");
    }

    @Override
    public ServerResponse<PageInfo> getCheckList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CheckWithBLOBs> checkList = checkMapper.selectAll();
        for (CheckWithBLOBs check : checkList) {
            check.setOriginalData(null);
            check.setTransformData(null);
        }
        PageInfo pageInfo = new PageInfo((checkList));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> searchCheck(String stationName, Integer stationId, String modelId, Integer checkerId, Date startTime, Date endTime, int pageNum, int pageSize) {
        return null;
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
    public ServerResponse<PageInfo> getCheckList(Integer stationId, Integer modelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CheckWithBLOBs> checkList = checkMapper.select(stationId, modelId);
        for (CheckWithBLOBs check : checkList) {
            check.setOriginalData(null);
            check.setTransformData(null);
        }
        PageInfo pageInfo = new PageInfo(checkList);
        return ServerResponse.createBySuccess(pageInfo);
    }

}

