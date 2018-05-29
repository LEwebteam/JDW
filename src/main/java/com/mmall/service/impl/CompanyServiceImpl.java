package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CompanyMapper;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.Company;
import com.mmall.pojo.User;
import com.mmall.service.ICompanyService;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geely
 */

@Slf4j
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyMapper companyMapper;


    @Override
    public ServerResponse<PageInfo> getCompanyList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse getAll() {
        List<Company> list = companyMapper.selectAll();

        return ServerResponse.createBySuccess("成功",list);
    }

    @Override
    public Company selectByPrimaryKey(Integer officeid) {
        Company company =companyMapper.selectByPrimaryKey(officeid);
        return company;
    }
}
