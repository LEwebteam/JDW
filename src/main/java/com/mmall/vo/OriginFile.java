package com.mmall.vo;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class OriginFile {
    public Integer id;
    public Integer stationId;

    public Integer modelId;

    public Integer checkerId;

    public Date checkTime;

    public String lineMethod;

    public String lineDetail;

    public Integer referenceNode;

    public Integer currentValue;

    public String weather;

    public String note;

    public MultipartFile file;
}
