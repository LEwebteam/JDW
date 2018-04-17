package com.mmall.pojo;

public class Deleteinfo {
    private Integer id;

    private Integer modelId;

    private String type;

    private String node;

    private String line;

    public Deleteinfo(Integer id, Integer modelId, String type, String node, String line) {
        this.id = id;
        this.modelId = modelId;
        this.type = type;
        this.node = node;
        this.line = line;
    }

    public Deleteinfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node == null ? null : node.trim();
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line == null ? null : line.trim();
    }
}