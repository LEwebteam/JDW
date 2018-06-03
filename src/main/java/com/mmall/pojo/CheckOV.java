package com.mmall.pojo;

import javafx.scene.control.cell.CheckBoxListCell;

public class CheckOV extends CheckWithBLOBs {
    public String stationname;
    public String modelname;
    public String checkername;
    public String checkTimeString;

    public CheckOV(CheckWithBLOBs check) {
        this.setId(check.getId());
        this.setCode(check.getCode());
        this.setStationId(check.getStationId());
        this.setModelId(check.getModelId());
        this.setCheckerId(check.getCheckerId());
        this.setCheckTime(check.getCheckTime());
        this.setLineMethod(check.getLineMethod());
        this.setLineDetail(check.getLineDetail());
        this.setWeather(check.getWeather());
        this.setCheckResult(check.getCheckResult());
        this.setNote(check.getNote());

    }
}
