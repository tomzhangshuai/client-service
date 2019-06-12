package com.wufanbao.logservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//参考文档：https://my.oschina.net/kkrgwbj/blog/734530
//https://blog.csdn.net/whj826069748/article/details/80937076
//https://my.oschina.net/kkrgwbj/blog/734530

@SpringBootApplication
public class LogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogServiceApplication.class, args);

        //起socket服务
        SocketServer server = new SocketServer();
        server.startSocketServer(4560);

    }
}
