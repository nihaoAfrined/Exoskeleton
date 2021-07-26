package com.wxy.exoskeleton.controller.utils;

import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

@Component
public class SerialPortUtil {

    private static final Logger logger = LoggerFactory.getLogger(SerialPortUtil.class);

    private static SerialPortUtil serialPortUtil = null;

    static {
        //在该类被ClassLoader加载时就初始化一个SerialTool对象
        if (serialPortUtil == null) {
            serialPortUtil = new SerialPortUtil();
        }
    }

    //私有化SerialTool类的构造方法，不允许其他类生成SerialTool对象
    private SerialPortUtil() {
    }

    /**
     * 获取提供服务的SerialTool对象
     *
     * @return serialPortUtil
     */
    public static SerialPortUtil getSerialPortUtil() {
        if (serialPortUtil == null) {
            serialPortUtil = new SerialPortUtil();
        }
        return serialPortUtil;
    }

    /**
     * 查找所有可用端口
     *
     * @return 可用端口名称列表
     */
    public ArrayList<String> findPort() {
        //获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();

        ArrayList<String> portNameList = new ArrayList<>();

        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }

        return portNameList;
    }

    /**
     * 打开串口
     *
     * @param portName 端口名称
     * @param baudrate 波特率
     * @param databits 数据位
     * @param parity   校验位（奇偶位）
     * @param stopbits 停止位
     * @return 串口对象
     * //     * @throws SerialPortParameterFailure 设置串口参数失败
     * //     * @throws NotASerialPort             端口指向设备不是串口类型
     * //     * @throws NoSuchPort                 没有该端口对应的串口设备
     * //     * @throws PortInUse                  端口已被占用
     */
    public SerialPort openPort(String portName, int baudrate, int databits, int parity, int stopbits) {

        try {
            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);

            //判断是不是串口
            if (commPort instanceof SerialPort) {

                SerialPort serialPort = (SerialPort) commPort;
                try {
                    //设置一下串口的波特率等参数
                    serialPort.setSerialPortParams(baudrate, databits, stopbits, parity);
                } catch (UnsupportedCommOperationException e) {
                }
                System.out.println("Open " + portName + " sucessfully !");
                return serialPort;
            } else {
                logger.error("不是串口");
            }
        } catch (NoSuchPortException e1) {
            logger.error("没有找到端口");
            e1.printStackTrace();
        } catch (PortInUseException e2) {
            logger.error("端口被占用");
            e2.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭串口
     *
     * @param serialPort 待关闭的串口对象
     */
    public boolean closePort(SerialPort serialPort) {
        if (serialPort != null) {
            logger.info("串口正在关闭...");
            serialPort.close();
            logger.info("串口已经正确关闭。");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 往串口发送数据
     *
     * @param serialPort 串口对象
     * @param order      待发送数据
     *                   //     * @throws SendDataToSerialPortFailure        向串口发送数据失败
     *                   //     * @throws SerialPortOutputStreamCloseFailure 关闭串口对象的输出流出错
     */
    public boolean sendToPort(SerialPort serialPort, byte[] order) {
        OutputStream out = null;
        try {
            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从串口读取数据
     *
     * @param serialPort 当前已建立连接的SerialPort对象
     * @return 读取到的数据
     * //     * @throws ReadDataFromSerialPortFailure     从串口读取数据时出错
     * //     * @throws SerialPortInputStreamCloseFailure 关闭串口对象输入流出错
     */
    public byte[] readFromPort(SerialPort serialPort) {

        InputStream in = null;
        byte[] bytes = null;

        try {
            in = serialPort.getInputStream();
            int bufflenth = in.available();

            while (bufflenth != 0) {
                bytes = new byte[bufflenth];
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 添加监听器
     *
     * @param port     串口对象
     * @param listener 串口监听器
     *                 //     * @throws TooManyListeners 监听类对象过多
     */
    public boolean addListener(SerialPort port, SerialPortEventListener listener) {

        try {
            //给串口添加监听器
            port.addEventListener(listener);
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
            //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);

            return true;
        } catch (TooManyListenersException e) {
//            throw new TooManyListeners();
            logger.error("太多监听器");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除监听器
     *
     * @param port     串口对象
     * @param listener 串口监听器
     *                 //     * @throws TooManyListeners 监听类对象过多
     */
    public void removeListener(SerialPort port, SerialPortEventListener listener) {
        //删除串口监听器
        port.removeEventListener();
    }
}
