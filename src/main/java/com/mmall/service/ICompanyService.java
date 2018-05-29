package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Company;
import com.mmall.pojo.User;

/**
 * Created by geely
 */
public interface ICompanyService {

    ServerResponse<PageInfo> getCompanyList(int pageNum, int pageSize);
    ServerResponse getAll();


    Company selectByPrimaryKey(Integer id);
}
