package hanu.gdsc.coreProblem.services.testCasePing;

import hanu.gdsc.coreProblem.config.TestCasePingConfig;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocketThreads {
    private ServerSocket serverSocket;
    private static final List<SocketThread> socketThreads = new ArrayList<>();

    public SocketThreads() throws IOException {
        serverSocket = new ServerSocket(TestCasePingConfig.PORT);
        new Thread(() -> {
            while (true) {
                getNewSocketThread();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                removeClosedSockets();
            }
        }).start();
    }

    private void getNewSocketThread() {
        try {
            SocketThread socketThread = new SocketThread(serverSocket);
            synchronized (this) {
                socketThreads.add(socketThread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<SocketThread> get() {
        return socketThreads;
    }

    private void removeClosedSockets() {
        synchronized (this) {
            int i = 0;
            while (i < socketThreads.size()) {
                if (socketThreads.get(i).closed()) {
                    socketThreads.remove(i);
                } else {
                    i++;
                }
            }
        }
    }
}

