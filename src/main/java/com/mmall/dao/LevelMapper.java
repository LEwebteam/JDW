package com.mmall.dao;

import com.mmall.pojo.Company;
import com.mmall.pojo.Level;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Level record);

    int insertSelective(Level record);

    Level selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);

    List<Level> selectAll();
}