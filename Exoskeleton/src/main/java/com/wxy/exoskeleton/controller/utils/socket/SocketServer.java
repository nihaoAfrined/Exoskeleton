package com.wxy.exoskeleton.controller.utils.socket;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.wxy.exoskeleton.controller.utils.socket.SocketHandler.onMessage;
import static com.wxy.exoskeleton.controller.utils.socket.SocketHandler.register;


@Slf4j
@Component
@NoArgsConstructor
public class SocketServer {

    @Value("${wifiport}")
    private Integer port;
    private boolean started;
    private ServerSocket serverSocket;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private static ClientSocket register;

    public static void main(String[] args){
        new SocketServer().start(8068);
    }

    public void start(){
        start(null);
    }

    public ClientSocket getRegister() throws IOException {


        if(register == null) {
            Socket socket = serverSocket.accept();
            socket.setKeepAlive(true);
            register = register(socket);
        }

        return  register;
    }

    public static void InitRegister() {

        if(register != null) {
            register = null;
        }

    }


    public void start(Integer port){
        log.info("port: {}, {}", this.port, port);
        try {
            serverSocket =  new ServerSocket(port == null ? this.port : port);
            started = true;
            log.info("Socket服务已启动，占用端口： {}", serverSocket.getLocalPort());
        } catch (IOException e) {
            log.error("端口冲突,异常信息：{}", e);
            System.exit(0);
        }
        ClientSocket register = null;
        if(started) {
            try {
                register = getRegister();
                log.info("客户端已连接，其Key值为：{}", register.getKey());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        while (register != null) {
//            String msg = onMessage(register);
//            System.out.println(msg);
//        }

    }
}



