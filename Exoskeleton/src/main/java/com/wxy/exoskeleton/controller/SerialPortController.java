package com.wxy.exoskeleton.controller;

import com.wxy.exoskeleton.controller.utils.MyLister;
import com.wxy.exoskeleton.controller.utils.SerialPortUtil;
import com.wxy.exoskeleton.model.Message;
import com.wxy.exoskeleton.model.Ports;
import com.wxy.exoskeleton.model.RespBean;
import gnu.io.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/com")
public class SerialPortController {

    private static final Logger logger = LoggerFactory.getLogger(SerialPortController.class);

    @Autowired
    SerialPortUtil serialPortUtil;

    @Autowired
    MyLister myLister;

    public static SerialPort serialPort = null;

    private String portname;

    /**
     * 获得串口列表
     * @return
     */
    @GetMapping("/getPorts")
    public Ports getAllPorts() {
        //查看所有串口
        SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();
        ArrayList<String> port = serialPortUtil.findPort();
        Ports ports = new Ports();
        ports.setPorts(port);
        return ports;
    }

    /**
     * 连接指定串口
     * @param portName
     * @param baudrate
     * @return
     */
    @GetMapping("/openPort")
    public RespBean openPort(@RequestParam String portName, @RequestParam Integer baudrate) {
        portname = portName;
        logger.info("正在打开串口" + portname + "...");
        serialPort = serialPortUtil.openPort(portname, baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

        if(serialPort != null && serialPortUtil.addListener(serialPort, myLister)) {
            return RespBean.ok("串口打开了！");
        } else {
            return RespBean.error("串口打开失败！");
        }
    }

    /**
     * 关闭当前连接的串口
     * @return
     */
    @GetMapping("/closePort")
    public RespBean closePort() {
        if(serialPortUtil.closePort(serialPort)) {
            return RespBean.ok("串口已关闭！");
        }
        return RespBean.error("关闭串口失败！");
    }

    /**
     * 向目标端口发送信息
     * @param message
     * @return
     */
    @PostMapping
    public RespBean sendMessage(@RequestBody Message message) {

        String msg = message.getMessage();

        byte[] data = msg.getBytes();

        if(serialPortUtil.sendToPort(serialPort, data)) {
            return RespBean.ok("发送成功！");
        }
        return RespBean.error("发送失败！");

    }

    /**
     * 站立
     * @return
     * @throws IOException
     */
    @GetMapping("/stand")
    public RespBean Stand() throws IOException {
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x01, 0x05 };
        if(serialPortUtil.sendToPort(serialPort, msg)) {
            return RespBean.ok("发送成功！");
        }
        return RespBean.error("发送失败！");

    }


    /**
     * 行走/
     * @return
     * @throws IOException
     */
    @GetMapping("/walk")
    public RespBean Walk() throws IOException {
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x02, 0x06 };
        if(serialPortUtil.sendToPort(serialPort, msg)) {
            return RespBean.ok("发送成功！");
        }
        return RespBean.error("发送失败！");

    }

    /**
     * 蹲起
     * @return
     * @throws IOException
     */
    @GetMapping("/upDown")
    public RespBean upDown() throws IOException {
        //String msg = " 0xAA, 0x55, 0x05, 0x03, 0x07 ";
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x03, 0x07 };
        if(serialPortUtil.sendToPort(serialPort, msg)) {
            return RespBean.ok("发送成功！");
        }
        return RespBean.error("发送失败！");

    }

    /**
     * 上楼梯
     * @return
     * @throws IOException
     */
    @GetMapping("/upstairs")
    public RespBean Upstairs() throws IOException {
        //String msg = " 0xAA, 0x55, 0x05, 0x04, 0x08 ";
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x04, 0x08 };
        if(serialPortUtil.sendToPort(serialPort, msg)) {
            return RespBean.ok("发送成功！");
        }
        return RespBean.error("发送失败！");

    }

    /**
     * 下楼梯
     * @return
     * @throws IOException
     */
    @GetMapping("/downstairs")
    public RespBean Downstairs() throws IOException {
        //String msg = " 0xAA, 0x55, 0x05, 0x05, 0x09 ";
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x05, 0x09 };
        if(serialPortUtil.sendToPort(serialPort, msg)) {
            return RespBean.ok("发送成功！");
        }
        return RespBean.error("发送失败！");

    }

    /**
     * 停止
     * @return
     * @throws IOException
     */
    @GetMapping("/stop")
    public RespBean Stop() throws IOException {
        //String msg = " 0xAA, 0x55, 0x05, 0x00, 0x04 ";
        byte[] msg = new byte[]{(byte) 0xAA, 0x55, 0x05, 0x00, 0x04 };
        if(serialPortUtil.sendToPort(serialPort, msg)) {
            return RespBean.ok("发送成功！");
        }
        return RespBean.error("发送失败！");

    }


}
