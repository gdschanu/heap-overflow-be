package hanu.gdsc.infrastructure.share.config;

import org.springframework.core.env.Environment;

public class SocketConfig {
    public static int PORT = 5000;

    public static String IP = "localhost";

    public SocketConfig(Environment environment) {
        if (environment.getProperty("server.ip") != null) {
            IP = environment.getProperty("server.ip");
        }
        if (environment.getProperty("socket.port") != null) {
            PORT = Integer.parseInt(environment.getProperty("socket.port"));
        }
    }
}
