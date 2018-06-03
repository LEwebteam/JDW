package com.mmall.dao;

import com.mmall.pojo.Corp;

import java.util.List;

public interface CorpMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Corp record);

    int insertSelective(Corp record);

    List<Corp>  selectAll();

    Corp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Corp record);

    int updateByPrimaryKey(Corp record);
}