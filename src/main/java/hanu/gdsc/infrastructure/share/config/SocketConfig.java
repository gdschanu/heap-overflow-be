package hanu.gdsc.infrastructure.share.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SocketConfig {
    public int PORT = 5000;

    public String IP = "localhost";

    public SocketConfig(Environment environment) {
        if (environment.getProperty("server.ip") != null) {
            IP = environment.getProperty("server.ip");
        }
        if (environment.getProperty("socket.port") != null) {
            PORT = Integer.parseInt(environment.getProperty("socket.port"));
        }
    }
}
