package com.mmall.service.impl;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CorpMapper;
import com.mmall.pojo.Company;
import com.mmall.pojo.Corp;
import com.mmall.service.ICompanyService;
import com.mmall.service.ICorpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geely
 */

@Slf4j
@Service
public class CorpServiceImpl implements ICorpService {

    @Autowired
    private CorpMapper corpMapper;


    @Override
    public ServerResponse<PageInfo> getCorpList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse getAll() {
        List<Corp> list = corpMapper.selectAll();

        return ServerResponse.createBySuccess("成功",list);
    }

//    @Override
//    public Company selectByPrimaryKey(Integer officeid) {
//        Company company = corpMapper.selectByPrimaryKey(officeid);
//        return company;
//    }
}
