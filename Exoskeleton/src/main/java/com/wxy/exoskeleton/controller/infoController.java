package com.wxy.exoskeleton.controller;

import com.wxy.exoskeleton.controller.utils.POIUtils;
import com.wxy.exoskeleton.model.AllInfo;
import com.wxy.exoskeleton.model.Info;
import com.wxy.exoskeleton.model.RespBean;
import com.wxy.exoskeleton.model.RespPageBean;
import com.wxy.exoskeleton.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/basic")
public class infoController {

    @Autowired
    InfoService infoService;

    /**
     * 获取一页的数据信息
     */
    @GetMapping("/")
    public RespPageBean getInfoByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Info info) {
        return infoService.getInfoByPage(page, size, info);
    }

    @PostMapping("/search")
    public RespPageBean searchInfoByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestBody Info info) {
        return infoService.getInfoByPage(page, size, info);
    }



    /**
     * 添加一条新数据
     * @param allInfo
     * @return
     */
    @PostMapping("/")
    public RespBean addInfo(@RequestBody AllInfo allInfo) {
        if(infoService.addInfo(allInfo) == 1) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 依据id删除数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RespBean deleteInfoById(@PathVariable Integer id) {
        if(infoService.deleteInfoByPrimaryKey(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    /**
     * 更新信息
     * @param allInfo
     * @return
     */
    @PutMapping("/")
    public RespBean updateInfo(@RequestBody AllInfo allInfo) {
        if(infoService.updateInfo(allInfo) == 1) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }


    @PostMapping("/import")
    public RespBean importDate(MultipartFile file) {
        List<AllInfo> list = POIUtils.excel2AllInfo(file);

        if(infoService.addInfos(list) == (list.size() * 2)) {
            return RespBean.ok("上传成功！");
        }
        return RespBean.error("上传失败！");
    }

    /**
     * 下载数据，使用ResponseEntity,可以向前端返回一段二进制数据
     * @return
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        List<AllInfo> list = (List<AllInfo>)infoService.getInfoByPage(null, null, new Info()).getData();
        return POIUtils.AllInfo2Excel(list);
    }


}
