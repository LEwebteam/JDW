package com.mmall.pojo;

public class ModelOV extends Model {
    public String stationName;

    public ModelOV(Model model) {
        this.setId(model.getId());
        this.setCode(model.getCode());
        this.setModelName(model.getModelName());
        this.setRowNodeNum(model.getRowNodeNum());
        this.setCloumnNodeNum(model.getCloumnNodeNum());
        this.setRowFixedDistance(model.getRowFixedDistance());
        this.setCloumnFixedDistance(model.getCloumnFixedDistance());
        this.setRowMyDistance(model.getRowMyDistance());
        this.setCloumnMyDistance(model.getCloumnMyDistance());
        this.setNodeNum(model.getNodeNum());
        this.setLineNum(model.getNodeNum());
        this.setStationId(model.getStationId());
        this.setDeep(model.getDeep());
        this.setBodyNum(model.getBodyNum());
        this.setDrawing(model.getDrawing());
        this.setDrawingFileName(model.getDrawingFileName());
    }
}
