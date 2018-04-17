package com.mmall.dao;

import com.mmall.pojo.Checker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Checker record);

    int insertSelective(Checker record);

    Checker selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Checker record);

    int updateByPrimaryKey(Checker record);

    List<Checker> selectAll();

    List<Checker> select(@Param("checkerName") String checkerName, @Param("office") Integer office);

    List<Checker> selectByOfficeId(@Param("officeId") Integer officeId);
}