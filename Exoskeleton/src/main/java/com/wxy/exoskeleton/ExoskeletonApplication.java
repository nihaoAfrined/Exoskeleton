package com.wxy.exoskeleton;

import com.wxy.exoskeleton.controller.utils.socket.SocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.wxy.exoskeleton.mapper")
public class ExoskeletonApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ExoskeletonApplication.class, args);
        applicationContext.getBean(SocketServer.class).start();//在spring容器启动后，取到已经初始化的SocketServer，启动Socket服务
    }

}
