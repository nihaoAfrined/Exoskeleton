package com.wxy.exoskeleton;

import com.wxy.exoskeleton.model.Info;
import com.wxy.exoskeleton.model.RespPageBean;
import com.wxy.exoskeleton.service.InfoService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan(basePackages = "com.wxy.exoskeleton.mapper")
class ExoskeletonApplicationTests {

    @Autowired
    InfoService infoService;

//    @Test
//    void contextLoads() {
//
//        Info info = new Info();
//
//        RespPageBean infoByPage = infoService.getInfoByPage(1, 10, info);
//
//        List<Info> data = infoByPage.getData();
//
//        System.out.println(data);
//    }



//    @Test
//    void testFindInfoByPage() {
//
//        List<Info> lists = infoService.findInfoByPage(1,2);
//
//        for( Info list : lists ) {
//            System.out.println(list);
//        }
//    }

    @Test
    void test1() {
        List<Info> lists = infoService.findInfoByLength(176.0);

        for( Info list : lists ) {
            System.out.println(list);
        }
    }


}
