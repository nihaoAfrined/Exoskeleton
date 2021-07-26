package com.wxy.exoskeleton.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ImportData {

    private Long total;

    private List<AllInfo> lists;
}
