package com.mmall.pojo;

public class CheckerOV extends Checker {
    public String officename;

    public CheckerOV(Checker checkerT) {
        this.setId(checkerT.getId());
        this.setOffice(checkerT.getOffice());
        this.setLevel(checkerT.getLevel());
        this.setName(checkerT.getName());
        this.setPhonenum(checkerT.getPhonenum());
        this.setSex(checkerT.getSex());
    }
}
