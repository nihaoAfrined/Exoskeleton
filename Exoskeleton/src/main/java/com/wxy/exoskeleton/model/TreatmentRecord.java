package com.wxy.exoskeleton.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class TreatmentRecord {

    private Integer id;

    private Integer uid;

    private String treatmentType;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date startTime;

    private Double totalTime;

    private String effect;
}
