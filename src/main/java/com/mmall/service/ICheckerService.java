package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Check;
import com.mmall.pojo.Checker;
import com.mmall.pojo.Station;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface ICheckerService {

    ServerResponse<Checker> selectByPrimaryKey(Integer checkerId);

    ServerResponse saveOrUpdateChecker(Checker checker);

    ServerResponse<PageInfo> getCheckertList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchChecker(String checkerName, Integer office, int pageNum, int pageSize);

    ServerResponse deleteChecker(Integer checkerId);

    ServerResponse<PageInfo> getCheckertList(Integer officeId, int pageNum, int pageSize);

}
