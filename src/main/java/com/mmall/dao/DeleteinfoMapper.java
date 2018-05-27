package com.mmall.dao;

import com.mmall.pojo.Deleteinfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface DeleteinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deleteinfo record);

    List<Deleteinfo> selectByModelId(@Param("modelId")Integer modelId);


    int insertSelective(Deleteinfo record);

    Deleteinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deleteinfo record);

    int updateByPrimaryKey(Deleteinfo record);
}