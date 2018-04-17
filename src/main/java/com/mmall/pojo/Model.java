package com.mmall.pojo;

public class Model {
    private Integer id;

    private String code;

    private String modelName;

    private String rowNodeNum;

    private String cloumnNodeNum;

    private String rowFixedDistance;

    private String cloumnFixedDistance;

    private String rowMyDistance;

    private String cloumnMyDistance;

    private Integer nodeNum;

    private Integer lineNum;

    private Integer stationId;

    private Float deep;

    private Integer bodyNum;

    private String drawing;

    private String drawingFileName;

    public Model(Integer id, String code, String modelName, String rowNodeNum, String cloumnNodeNum, String rowFixedDistance, String cloumnFixedDistance, String rowMyDistance, String cloumnMyDistance, Integer nodeNum, Integer lineNum, Integer stationId, Float deep, Integer bodyNum, String drawing, String drawingFileName) {
        this.id = id;
        this.code = code;
        this.modelName = modelName;
        this.rowNodeNum = rowNodeNum;
        this.cloumnNodeNum = cloumnNodeNum;
        this.rowFixedDistance = rowFixedDistance;
        this.cloumnFixedDistance = cloumnFixedDistance;
        this.rowMyDistance = rowMyDistance;
        this.cloumnMyDistance = cloumnMyDistance;
        this.nodeNum = nodeNum;
        this.lineNum = lineNum;
        this.stationId = stationId;
        this.deep = deep;
        this.bodyNum = bodyNum;
        this.drawing = drawing;
        this.drawingFileName = drawingFileName;
    }

    public Model() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getRowNodeNum() {
        return rowNodeNum;
    }

    public void setRowNodeNum(String rowNodeNum) {
        this.rowNodeNum = rowNodeNum == null ? null : rowNodeNum.trim();
    }

    public String getCloumnNodeNum() {
        return cloumnNodeNum;
    }

    public void setCloumnNodeNum(String cloumnNodeNum) {
        this.cloumnNodeNum = cloumnNodeNum == null ? null : cloumnNodeNum.trim();
    }

    public String getRowFixedDistance() {
        return rowFixedDistance;
    }

    public void setRowFixedDistance(String rowFixedDistance) {
        this.rowFixedDistance = rowFixedDistance == null ? null : rowFixedDistance.trim();
    }

    public String getCloumnFixedDistance() {
        return cloumnFixedDistance;
    }

    public void setCloumnFixedDistance(String cloumnFixedDistance) {
        this.cloumnFixedDistance = cloumnFixedDistance == null ? null : cloumnFixedDistance.trim();
    }

    public String getRowMyDistance() {
        return rowMyDistance;
    }

    public void setRowMyDistance(String rowMyDistance) {
        this.rowMyDistance = rowMyDistance == null ? null : rowMyDistance.trim();
    }

    public String getCloumnMyDistance() {
        return cloumnMyDistance;
    }

    public void setCloumnMyDistance(String cloumnMyDistance) {
        this.cloumnMyDistance = cloumnMyDistance == null ? null : cloumnMyDistance.trim();
    }

    public Integer getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(Integer nodeNum) {
        this.nodeNum = nodeNum;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Float getDeep() {
        return deep;
    }

    public void setDeep(Float deep) {
        this.deep = deep;
    }

    public Integer getBodyNum() {
        return bodyNum;
    }

    public void setBodyNum(Integer bodyNum) {
        this.bodyNum = bodyNum;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing == null ? null : drawing.trim();
    }

    public String getDrawingFileName() {
        return drawingFileName;
    }

    public void setDrawingFileName(String drawingFileName) {
        this.drawingFileName = drawingFileName == null ? null : drawingFileName.trim();
    }
}