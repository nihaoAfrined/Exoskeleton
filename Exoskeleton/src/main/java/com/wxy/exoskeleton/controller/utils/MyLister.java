package com.wxy.exoskeleton.controller.utils;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.wxy.exoskeleton.controller.SerialPortController.serialPort;

@Component
public class MyLister implements SerialPortEventListener {

    @SneakyThrows
    @Override
    public void serialEvent(SerialPortEvent arg0) {
        if (arg0.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();
            byte[] bytes = serialPortUtil.readFromPort(serialPort);
            String byteStr = new String(bytes, 0, bytes.length).trim();
//            System.out.println(new Date() + "【读到的字节数组】：-----" + byteStr);
//            ISO8583 aiISO8583DTO = (ISO8583) TransISO8583MessageUtil.unpackISO8583(ISO8583.class, byteStr);
//            System.out.println("UNPACK ISO8583: " + JSON.toJSONString(aiISO8583DTO));
            System.out.println("===========start===========");
            System.out.println(new Date() + "【读到的字节数组】：-----" + byteStr);
            String needData = printHexString(bytes);
            System.out.println(new Date() + "【字节数组转字符串】：-----" + needData);
            System.out.println(new Date() + "【16进制字符串转字符串】：" + hexStringToString(needData));
            System.out.println("===========end===========");

//            ISO8583 aiiso8583DTO = new ISO8583();
//
//            aiiso8583DTO.setCardNo02(String.valueOf(System.currentTimeMillis()));
//            aiiso8583DTO.setTransProcCode03("123456");
//            aiiso8583DTO.setTransAmt04("000010000000");
//            aiiso8583DTO.setSysTrackNo11("888888");
//            aiiso8583DTO.setServiceInputModeCode22("100");
//            aiiso8583DTO.setServiceConditionCode25("66");
//            aiiso8583DTO.setCardAcceptorTerminalID41("08022206");
//            aiiso8583DTO.setCardAcceptorID42("000100000000005");
//            aiiso8583DTO.setAdditionalDataPrivate48("0000");
//            aiiso8583DTO.setCurrencyCode49("168");
//            aiiso8583DTO.setEWalletTransInfo58("53560118FFFFFFFFFFFF03104890100000006059FFFFFFFF0101000200000064020000080000032017122310225672FB47880000012B01");
//            aiiso8583DTO.setFld60Domain60("41000006666");
//            aiiso8583DTO.setOriginalInfoDomain61("000666000181");
//            aiiso8583DTO.setFld63Domain63("0000000000000000");

//            String maxBitmap = "7020048000C1805B";
//            final String TPDU = "6000000000";
//            final String HEAD = "612200000000";
//            String sendMsg = TransISO8583MessageUtil.packISO8583(aiiso8583DTO, maxBitmap, TPDU, HEAD, "0300");
            //接收到消息后 回复一个当前时间
            serialPortUtil.sendToPort(serialPort, new Date().toString().getBytes());
        }
    }


    // 字节数组转字符串
    private String printHexString(byte[] b) {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sbf.append(hex.toUpperCase() + " ");
        }
        return sbf.toString().trim();
    }

    /**
     * 16进制转换成为string类型字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
}
