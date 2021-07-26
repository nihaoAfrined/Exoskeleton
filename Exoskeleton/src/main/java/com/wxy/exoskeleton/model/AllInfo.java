package com.wxy.exoskeleton.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class AllInfo implements Serializable {

    private Integer id;

    private String name;

    private String gender;

    private Integer age;

    private Double weight;

    private Double height;

    private Double legLength;

    private Double crotchWidth;

    private Integer isPatient;

    private Integer type;

    private String number;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date date;

}
