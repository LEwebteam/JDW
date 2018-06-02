package com.mmall.pojo;

import java.sql.Statement;

public class StationOV extends Station {
    public String officename;
    public String bulidTimeString;

    public StationOV(Station station) {
        this.setOffice(station.getOffice());
        this.setArea(station.getArea());
        this.setBuildTime(station.getBuildTime());
        this.setCode(station.getCode());
        this.setCreateBy(station.getCreateBy());
        this.setCreateDatetime(station.getCreateDatetime());
        this.setDarwing(station.getDarwing());
        this.setDeep(station.getDeep());
        this.setDrawingFileName(station.getDrawingFileName());
        this.setId(station.getId());
        this.setLevel(station.getLevel());
        this.setLocation(station.getLocation());
        this.setType(station.getType());
        this.setName(station.getName());
        this.setUpdateBy(station.getUpdateBy());
        this.setUpdateDatetime(station.getUpdateDatetime());
        this.setBuildTime(station.getBuildTime());
    }
}
