package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CheckerMapper;
import com.mmall.dao.CompanyMapper;
import com.mmall.pojo.*;
import com.mmall.service.ICheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CheckerServiceImpl implements ICheckerService {

    @Autowired
    CheckerMapper checkerMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public ServerResponse selectByPrimaryKey(Integer checkerId) {
        Checker checker = checkerMapper.selectByPrimaryKey(checkerId);
        if (checker != null) {
            return ServerResponse.createBySuccess(checker);
        }
        return ServerResponse.createByErrorMessage("无信息");
    }

    @Override
    public ServerResponse saveOrUpdateChecker(Checker checker) {
        if (checker != null) {
            if (checker.getId() != null) {
                int rowCount = checkerMapper.updateByPrimaryKey(checker);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccessMessage("检测员更新成功");
                }
                return ServerResponse.createByErrorMessage("保存失败");
            } else {
                int rowCount = checkerMapper.insert(checker);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccessMessage("检测员新增成功");
                }
                return ServerResponse.createByErrorMessage("保存失败");
            }
        }

        return ServerResponse.createByErrorMessage("保存失败");
    }

    @Override
    public ServerResponse<PageInfo> getCheckertList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Checker> checkerList = checkerMapper.selectAll();
        List<Company> companyList = companyMapper.selectAll();
        List<CheckerOV> checkOVS = new ArrayList<>();
        for (Checker checkerT : checkerList) {
            CheckerOV checkOV = new CheckerOV(checkerT);
            for (Company company : companyList) {
                if (company.getId() == checkerT.getOffice()) {
                    checkOV.officename = company.getCompanyName();
                    break;
                }
            }
            checkOVS.add(checkOV);
        }
        PageInfo pageInfo = new PageInfo((checkOVS));


        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> searchChecker(String checkerName, Integer office, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Checker> checkerList = checkerMapper.select(checkerName, office);
        PageInfo pageInfo = new PageInfo(checkerList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteChecker(Integer checkerId) {
        Integer rowCount = checkerMapper.deleteByPrimaryKey(checkerId);
        if (rowCount > 0) {

            return ServerResponse.createBySuccess("删除成功");
        } else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public ServerResponse<PageInfo> getCheckertList(Integer officeId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Checker> checkerList = checkerMapper.selectByOfficeId(officeId);
        PageInfo pageInfo = new PageInfo(checkerList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> getCheckertListBystationId(Integer stationId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Checker> checkerList = checkerMapper.selectByStationId(stationId);
        PageInfo pageInfo = new PageInfo(checkerList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> getCheckertListBymodelId(Integer modelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Checker> checkerList = checkerMapper.selectByModelId(modelId);
        PageInfo pageInfo = new PageInfo(checkerList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> getCheckertListByOffice(Integer officeId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Checker> checkerList = checkerMapper.selectByOfficeId(officeId);
        List<Company> companyList = companyMapper.selectAll();
        List<CheckerOV> checkOVS = new ArrayList<>();
        for (Checker checkerT : checkerList) {
            CheckerOV checkOV = new CheckerOV(checkerT);
            for (Company company : companyList) {
                if (company.getId() == checkerT.getOffice()) {
                    checkOV.officename = company.getCompanyName();
                    break;
                }
            }
            checkOVS.add(checkOV);
        }
        PageInfo pageInfo = new PageInfo((checkOVS));
        return ServerResponse.createBySuccess(pageInfo);
    }
}
