package com.mmall.pojo;

public class Checker {
    private Integer id;

    private String name;

    private String sex;

    private Integer office;

    private String level;

    private String phonenum;

    public Checker(Integer id, String name, String sex, Integer office, String level, String phonenum) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.office = office;
        this.level = level;
        this.phonenum = phonenum;
    }

    public Checker() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getOffice() {
        return office;
    }

    public void setOffice(Integer office) {
        this.office = office;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }
}