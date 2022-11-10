package hanu.gdsc.infrastructure.share.controller;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import hanu.gdsc.infrastructure.share.config.SocketConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class SocketIO {
    private SocketIOServer server = null;

    public SocketIO() {
        Configuration config = new Configuration();
        config.setHostname(SocketConfig.IP);
        config.setPort(SocketConfig.PORT);
        config.setOrigin("*");
        server = new SocketIOServer(config);
    }

    public <T> void addEventListener(java.lang.String eventName, java.lang.Class<T> eventClass, com.corundumstudio.socketio.listener.DataListener<T> listener) {
        server.stop();
        server.addEventListener(eventName, eventClass, listener);
        server.start();
    }

    @PreDestroy
    public void destroy() {
        if (server != null)
            server.stop();
    }
}
