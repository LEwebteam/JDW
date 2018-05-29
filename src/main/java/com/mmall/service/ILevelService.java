package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Company;
import com.mmall.pojo.Level;

/**
 * Created by geely
 */
public interface ILevelService {

    ServerResponse getAll();
    Level selectByPrimaryKey(Integer id);



}
