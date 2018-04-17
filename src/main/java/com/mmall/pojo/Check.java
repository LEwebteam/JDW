package com.mmall.pojo;

import java.util.Date;

public class Check {
    private Integer id;

    private Integer code;

    private Integer stationId;

    private Integer modelId;

    private Integer checkerId;

    private Date checkTime;

    private String lineMethod;

    private String lineDetail;

    private Integer referenceNode;

    private Integer currentValue;

    public Check(Integer id, Integer code, Integer stationId, Integer modelId, Integer checkerId, Date checkTime, String lineMethod, String lineDetail, Integer referenceNode, Integer currentValue) {
        this.id = id;
        this.code = code;
        this.stationId = stationId;
        this.modelId = modelId;
        this.checkerId = checkerId;
        this.checkTime = checkTime;
        this.lineMethod = lineMethod;
        this.lineDetail = lineDetail;
        this.referenceNode = referenceNode;
        this.currentValue = currentValue;
    }

    public Check() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Integer checkerId) {
        this.checkerId = checkerId;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getLineMethod() {
        return lineMethod;
    }

    public void setLineMethod(String lineMethod) {
        this.lineMethod = lineMethod == null ? null : lineMethod.trim();
    }

    public String getLineDetail() {
        return lineDetail;
    }

    public void setLineDetail(String lineDetail) {
        this.lineDetail = lineDetail == null ? null : lineDetail.trim();
    }

    public Integer getReferenceNode() {
        return referenceNode;
    }

    public void setReferenceNode(Integer referenceNode) {
        this.referenceNode = referenceNode;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }
}