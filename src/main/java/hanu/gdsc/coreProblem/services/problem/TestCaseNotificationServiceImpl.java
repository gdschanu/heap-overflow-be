package hanu.gdsc.coreProblem.services.problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.coreProblem.config.TestCaseNotificationSocketConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class TestCaseNotificationServiceImpl implements TestCaseNotificationService {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private ObjectMapper objectMapper;

    public TestCaseNotificationServiceImpl() throws IOException {
        serverSocket = new ServerSocket(TestCaseNotificationSocketConfig.PORT);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        objectMapper = new ObjectMapper();
    }

    private void writeToSocket(String message) {
        out.println(message);
    }

    @Override
    public void notifyRunningTestCase(Input input) throws Exception {
        writeToSocket(objectMapper.writeValueAsString(input));
    }
}
