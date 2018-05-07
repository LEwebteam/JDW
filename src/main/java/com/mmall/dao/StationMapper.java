package com.mmall.dao;

import com.mmall.pojo.Station;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Station record);

    int insertSelective(Station record);

    Station selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Station record);

    int updateByPrimaryKey(Station record);

    List<Station> selectByOfficeId(Integer officeId);

    List<Station> select(@Param("stationName") String stationName, @Param("stationId") Integer stationId, @Param("stationType") String stationType, @Param("stationLevel") String stationLevel, @Param("office") Integer office, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Station> selectAll();
}