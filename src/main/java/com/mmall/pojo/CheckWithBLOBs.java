package com.mmall.pojo;

import java.util.Date;

public class CheckWithBLOBs extends Check {
    private String weather;

    private byte[] originalData;

    private byte[] transformData;

    private String checkResult;

    private String note;

    public CheckWithBLOBs(Integer id, Integer code, Integer stationId, Integer modelId, Integer checkerId, Date checkTime, String lineMethod, String lineDetail, Integer referenceNode, Integer currentValue, String weather, byte[] originalData, byte[] transformData, String checkResult, String note) {
        super(id, code, stationId, modelId, checkerId, checkTime, lineMethod, lineDetail, referenceNode, currentValue);
        this.weather = weather;
        this.originalData = originalData;
        this.transformData = transformData;
        this.checkResult = checkResult;
        this.note = note;
    }

    public CheckWithBLOBs() {
        super();
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather == null ? null : weather.trim();
    }

    public byte[] getOriginalData() {
        return originalData;
    }

    public void setOriginalData(byte[] originalData) {
        this.originalData = originalData;
    }

    public byte[] getTransformData() {
        return transformData;
    }

    public void setTransformData(byte[] transformData) {
        this.transformData = transformData;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}