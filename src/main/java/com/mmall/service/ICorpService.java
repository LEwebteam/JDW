package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Company;

/**
 * Created by geely
 */
public interface ICorpService {

    ServerResponse<PageInfo> getCorpList(int pageNum, int pageSize);
    ServerResponse getAll();


//    Company selectByPrimaryKey(Integer id);
}
