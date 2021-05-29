package com.wxy.exoskeleton.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Info implements Serializable {

    private Integer id;

    private String name;

    private String gender;

    private Integer age;

    private Double weight;

    private Double height;

    private Double legLength;

    private Double crotchWidth;

    private Integer isPatient;

    private String type;

}
