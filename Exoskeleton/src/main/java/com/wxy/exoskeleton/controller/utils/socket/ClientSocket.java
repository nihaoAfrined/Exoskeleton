package com.wxy.exoskeleton.controller.utils.socket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static com.wxy.exoskeleton.controller.utils.socket.SocketServer.*;
import static com.wxy.exoskeleton.controller.utils.socket.SocketHandler.*;

@Slf4j
@Data
public class ClientSocket implements Runnable{

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String key;
    private String message;

    @Override
    public void run() {
        //每5秒进行一次客户端连接，判断是否需要释放资源
        while (true){
            try {
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isSocketClosed(this)){
                log.info("客户端已关闭,其Key值为：{}", this.getKey());
//                //关闭对应的服务端资源
//                close(this);
                // 重置客户端
                InitRegister();
                break;
            }
        }
    }

}
