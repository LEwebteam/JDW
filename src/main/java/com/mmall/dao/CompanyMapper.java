package com.mmall.dao;

import com.mmall.pojo.Company;
import com.mmall.pojo.Station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    List<Company> selectAll();

}