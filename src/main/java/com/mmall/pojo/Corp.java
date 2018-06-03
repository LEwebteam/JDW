package com.mmall.pojo;

public class Corp {
    private Long id;

    private String corpName;

    public Corp(Long id, String corpName) {
        this.id = id;
        this.corpName = corpName;
    }

    public Corp() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName == null ? null : corpName.trim();
    }
}