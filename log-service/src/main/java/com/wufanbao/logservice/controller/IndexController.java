package com.wufanbao.logservice.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.event.LoggingEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

@RestController
public class IndexController {

    private Log suppleLog = LogFactory.getLog(this.getClass());

    @GetMapping("/")
    public String Test() {
        suppleLog.info("Test");
        return "Test";
    }

    /**
     * 写日志
     */
    @PostMapping("write")
    public void logWrite(String info) {
        suppleLog.debug(info);
    }

    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(4560);
        while (true) {
            Socket client = socket.accept();
            Thread t = new Thread(new LogRunner(client));
            t.start();
        }
    }

    static class LogRunner implements Runnable {
        private ObjectInputStream ois;

        public LogRunner(Socket client) {
            try {
                this.ois = new ObjectInputStream(client.getInputStream());
            } catch (Exception e) {
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    LoggingEvent event = (LoggingEvent) ois.readObject();
                    System.out.println(event.getLoggerName() + ": " + event.getMessage());
                }
            } catch (Exception e) {
            } finally {
            }
        }
    }
}
