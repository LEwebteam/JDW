package com.mmall.pojo;

import java.util.Date;

public class Station {
    private Integer id;

    private Integer code;

    private String name;

    private Date buildTime;

    private String location;

    private String level;

    private Float area;

    private String type;

    private String darwing;

    private String drawingFileName;

    private Integer office;

    private String createBy;

    private Date createDatetime;

    private String updateBy;

    private Date updateDatetime;

    private Float deep;

    public Station(Integer id, Integer code, String name, Date buildTime, String location, String level, Float area, String type, String darwing, String drawingFileName, Integer office, String createBy, Date createDatetime, String updateBy, Date updateDatetime, Float deep) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.buildTime = buildTime;
        this.location = location;
        this.level = level;
        this.area = area;
        this.type = type;
        this.darwing = darwing;
        this.drawingFileName = drawingFileName;
        this.office = office;
        this.createBy = createBy;
        this.createDatetime = createDatetime;
        this.updateBy = updateBy;
        this.updateDatetime = updateDatetime;
        this.deep = deep;
    }

    public Station() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDarwing() {
        return darwing;
    }

    public void setDarwing(String darwing) {
        this.darwing = darwing == null ? null : darwing.trim();
    }

    public String getDrawingFileName() {
        return drawingFileName;
    }

    public void setDrawingFileName(String drawingFileName) {
        this.drawingFileName = drawingFileName == null ? null : drawingFileName.trim();
    }

    public Integer getOffice() {
        return office;
    }

    public void setOffice(Integer office) {
        this.office = office;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Float getDeep() {
        return deep;
    }

    public void setDeep(Float deep) {
        this.deep = deep;
    }
}