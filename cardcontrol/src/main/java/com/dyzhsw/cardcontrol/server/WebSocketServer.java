package com.dyzhsw.cardcontrol.server;

import java.io.IOException;
import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.dyzhsw.cardcontrol.message.ServerChannelHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.dyzhsw.cardcontrol.util.TailLogUtil;


@ServerEndpoint("/websocket/{id}")
@Component
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private Process process;

    private InputStream inputStream;

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        String path = "";
        try {
            path = getPathById(id);
            logger.info(path);
            // 执行tail -f命令
            process = Runtime.getRuntime().exec("tail -f " + path);
            inputStream = process.getInputStream();
            // 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
            TailLogUtil thread = new TailLogUtil(inputStream, session);
            thread.start();
        } catch (IOException e) {
            logger.info(String.format("read file [%s] error.%s", path, e));
        }
    }

    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose() {
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.info("close websocket error.:" + e);
            }
        }

        if(process != null){
            process.destroy();
        }
    }

    @OnError
    public void onError(Throwable thr) {
        System.out.println("websocket error." + thr);
    }

    private String getPathById(String id) {
        logger.info(id);
        return "/usr/logs/cardcontrol.log";
    }

}
