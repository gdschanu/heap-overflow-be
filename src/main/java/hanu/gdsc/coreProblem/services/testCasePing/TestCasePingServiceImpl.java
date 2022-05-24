package hanu.gdsc.coreProblem.services.testCasePing;

import hanu.gdsc.coreProblem.config.TestCasePingConfig;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.InvalidInputError;
import hanu.gdsc.share.scheduling.Scheduler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestCasePingServiceImpl implements TestCasePingService {
    private ServerSocket serverSocket;
    private List<SocketThread> threadList;

    public TestCasePingServiceImpl() throws IOException {
        serverSocket = new ServerSocket(TestCasePingConfig.PORT);
        threadList = new ArrayList<>();
        new Scheduler(5000, new Scheduler.Runner() {
            @Override
            public void run() throws IOException, InterruptedException {
                getNewSocketThread();
            }
        }).start();
        new Scheduler(15000, new Scheduler.Runner() {
            @Override
            public void run() throws IOException, InterruptedException {
                removeClosedSockets();
            }
        }).start();
    }

    @Override
    public void ping(Id coderId, Payload payload) {
        if (coderId == null) {
            throw new InvalidInputError("coderId must not be null");
        }
        try {
            for (int i = 0; i < threadList.size(); i++) {
                SocketThread thread = threadList.get(i);
                // TODO: check coderId before sending
                try {
                    thread.send(payload);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getNewSocketThread() throws InterruptedException {
        try {
            SocketThread socketThread = new SocketThread(serverSocket);
            threadList.add(socketThread);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void removeClosedSockets() throws InterruptedException {
        synchronized (this) {
            int i = 0;
            while (i < threadList.size()) {
                if (threadList.get(i).closed()) {
                    threadList.remove(i);
                } else {
                    i++;
                }
            }
        }
    }
}
