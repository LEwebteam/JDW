package com.mmall.service.impl;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CompanyMapper;
import com.mmall.dao.LevelMapper;
import com.mmall.pojo.Company;
import com.mmall.pojo.Level;
import com.mmall.service.ICompanyService;
import com.mmall.service.ILevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geely
 */

@Slf4j
@Service
public class LevelServiceImpl implements ILevelService {

    @Autowired
    private LevelMapper levelMapper;



    @Override
    public ServerResponse getAll() {
        List<Level> list = levelMapper.selectAll();

        return ServerResponse.createBySuccess("成功",list);
    }

    @Override
    public Level selectByPrimaryKey(Integer id) {
        Level level = levelMapper.selectByPrimaryKey(id);
        return level;
    }
}
