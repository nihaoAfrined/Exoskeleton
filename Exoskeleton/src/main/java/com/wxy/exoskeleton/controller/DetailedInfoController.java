package com.wxy.exoskeleton.controller;


import com.wxy.exoskeleton.model.*;
import com.wxy.exoskeleton.service.DetailedInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 详细信息控制页
 */
@RestController
@RequestMapping("/basic/detail")
public class DetailedInfoController {

    @Autowired
    DetailedInfoService detailedInfoService;

    /**
     * 获得一页的治疗记录
     * @param page
     * @param size
     * @param tRecord
     * @param beginDateScope
     * @return
     */
    @GetMapping("/")
    public RespPageBean getTRecordsByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, TreatmentRecord tRecord, Date[] beginDateScope) {
        return detailedInfoService.getTRecordsByPage(page, size, tRecord, beginDateScope);
    }

    @GetMapping("/{uid}")
    public DetailedInfo getDInfoByUid(@PathVariable Integer uid) {
        return detailedInfoService.getDInfoByUid(uid);
    }

    /**
     * 添加详细信息
     * @param allInfo
     * @return
     */
    @PostMapping("/")
    public RespBean addDInfo(@RequestBody AllInfo allInfo) {
        if (detailedInfoService.addDInfo(allInfo) == 1) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 删除一条详细信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deleteDInfo(@PathVariable Integer id) {
        if (detailedInfoService.deleteDInfo(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    /**
     * 修改一条详细信息
     * @param allInfo
     * @return
     */
    @PutMapping("/")
    public RespBean updateDInfo(@RequestBody AllInfo allInfo) {
        if (detailedInfoService.updateDInfo(allInfo) == 1) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
    
    /**
     * 添加治疗信息
     * @param tRecord
     * @return
     */
    @PostMapping("/addTRecord")
    public RespBean addTreatmentRecord(@RequestBody TreatmentRecord tRecord) {
        if (detailedInfoService.addTRecord(tRecord) == 1) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 根据id删除一条治疗信息
     * @param id
     * @return
     */
    @DeleteMapping("/deleteTRecord/{id}")
    public RespBean deleteTreatmentRecord(@PathVariable Integer id) {
        if (detailedInfoService.deleteTRecord(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    /**
     * 更新（修改）一条治疗记录
     * @param tRecord
     * @return
     */
    @PutMapping("/updateTRecord")
    public RespBean updateTreatmentRecord(@RequestBody TreatmentRecord tRecord) {
        if (detailedInfoService.updateTRecord(tRecord) == 1) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
