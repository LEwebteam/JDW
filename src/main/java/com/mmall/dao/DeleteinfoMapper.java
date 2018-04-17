package com.mmall.dao;

import com.mmall.pojo.Deleteinfo;

public interface DeleteinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deleteinfo record);

    int insertSelective(Deleteinfo record);

    Deleteinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deleteinfo record);

    int updateByPrimaryKey(Deleteinfo record);
}