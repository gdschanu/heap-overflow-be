package hanu.gdsc.infrastructure.share.controller;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import hanu.gdsc.infrastructure.share.config.SocketConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;

@org.springframework.context.annotation.Configuration
public class Socket {
    private SocketIOServer server = null;
    private int eventListenerCount = 0;
    private final int startServerWhenEventListenerCountReach = 2;

    public Socket(SocketConfig socketConfig) {
        Configuration config = new Configuration();
        config.setHostname(socketConfig.IP);
        config.setPort(socketConfig.PORT);
        config.setOrigin("*");
        server = new SocketIOServer(config);
    }

    public <T> void addEventListener(java.lang.String eventName, java.lang.Class<T> eventClass, com.corundumstudio.socketio.listener.DataListener<T> listener) {
        eventListenerCount++;
        server.addEventListener(eventName, eventClass, listener);
        if (eventListenerCount >= startServerWhenEventListenerCountReach)
            server.start();
    }

    @PreDestroy
    public void destroy() {
        if (server != null)
            server.stop();
    }
}
