package com.wxy.exoskeleton.controller;

import com.wxy.exoskeleton.controller.utils.socket.ClientSocket;
import com.wxy.exoskeleton.controller.utils.socket.SocketHandler;
import com.wxy.exoskeleton.controller.utils.socket.SocketServer;
import com.wxy.exoskeleton.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/wireless")
public class WirelessController {


    private boolean flag = false;

    @Autowired
    SocketServer socketServer;

    @Autowired
    SocketHandler socketHandler;

//    @GetMapping("/open")
//    public boolean openSocket() throws IOException {
//        socketServer.start();
//        return true;
//    }

    /**
     * 向客户端发送信息
     * @param message
     * @return
     * @throws IOException
     */
//    @PostMapping("/sendMessage")
//    public boolean SendMessage(@RequestBody Message message) throws IOException {
//        if (message != null) {
//            String mes = message.getMessage();
//            ClientSocket register = socketServer.getRegister();
//            socketHandler.sendMessage(register, mes);
//            return true;
//        }
//        return false;
//    }

    /**
     * 初始化客户端
     * @return
     */
    @GetMapping("/init")
    public boolean InitClient() {
        socketServer.InitRegister();
        return true;
    }


    /**
     * 站立
     * @return
     * @throws IOException
     */
    @GetMapping("/stand")
    public boolean Stand() throws IOException {
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x01, 0x05 };
        ClientSocket register = socketServer.getRegister();
        socketHandler.sendMessage(register, msg);
        return true;

    }


    /**
     * 行走/
     * @return
     * @throws IOException
     */
    @GetMapping("/walk")
    public boolean Walk() throws IOException {
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x02, 0x06 };
        ClientSocket register = socketServer.getRegister();
        socketHandler.sendMessage(register, msg);
        return true;

    }

    /**
     * 蹲起
     * @return
     * @throws IOException
     */
    @GetMapping("/upDown")
    public boolean upDown() throws IOException {
        //String msg = " 0xAA, 0x55, 0x05, 0x03, 0x07 ";
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x03, 0x07 };
        ClientSocket register = socketServer.getRegister();
        socketHandler.sendMessage(register, msg);
        return true;

    }

    /**
     * 上楼梯
     * @return
     * @throws IOException
     */
    @GetMapping("/upstairs")
    public boolean Upstairs() throws IOException {
        //String msg = " 0xAA, 0x55, 0x05, 0x04, 0x08 ";
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x04, 0x08 };
        ClientSocket register = socketServer.getRegister();
        socketHandler.sendMessage(register, msg);
        return true;

    }

    /**
     * 下楼梯
     * @return
     * @throws IOException
     */
    @GetMapping("/downstairs")
    public boolean Downstairs() throws IOException {
        //String msg = " 0xAA, 0x55, 0x05, 0x05, 0x09 ";
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x05, 0x09 };
        ClientSocket register = socketServer.getRegister();
        socketHandler.sendMessage(register, msg);
        return true;

    }

    /**
     * 停止
     * @return
     * @throws IOException
     */
    @GetMapping("/stop")
    public boolean Stop() throws IOException {
        //String msg = " 0xAA, 0x55, 0x05, 0x00, 0x04 ";
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x00, 0x04 };
        ClientSocket register = socketServer.getRegister();
        socketHandler.sendMessage(register, msg);
        return true;

    }



    @PostMapping("/test")
    public void Test(@RequestBody Message message) {
        String cli = message.getClientName();
        String mes = message.getMessage();
        System.out.println(cli);
        System.out.println(mes);
    }
}
