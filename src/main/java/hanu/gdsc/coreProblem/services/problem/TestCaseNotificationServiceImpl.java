package hanu.gdsc.coreProblem.services.problem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.coreProblem.config.TestCaseNotificationSocketConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

@Service
public class TestCaseNotificationServiceImpl implements TestCaseNotificationService {
    private Queue<Input> runningTestCasesQueue;

    public TestCaseNotificationServiceImpl() throws IOException {
        Thread socketHandlerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(TestCaseNotificationSocketConfig.PORT);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    ObjectMapper objectMapper = new ObjectMapper();
                    while (true) {
                        Input runningTC = runningTestCasesQueue.poll();
                        if (runningTC != null) {
                            try {
                                String js = objectMapper.writeValueAsString(runningTC);
                                out.write(js);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
        socketHandlerThread.start();
    }

    @Override
    public void notifyRunningTestCase(Input input) throws Exception {
        runningTestCasesQueue.add(input);
    }
}
