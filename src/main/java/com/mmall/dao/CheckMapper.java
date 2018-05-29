package com.mmall.dao;

import com.mmall.pojo.Check;
import com.mmall.pojo.CheckWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckWithBLOBs record);

    int insertSelective(CheckWithBLOBs record);

    CheckWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CheckWithBLOBs record);

    int updateByPrimaryKey(Check record);

    List<CheckWithBLOBs> selectAll();

    List<CheckWithBLOBs> select(@Param("officeId") Integer officeId);
}