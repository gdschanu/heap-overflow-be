package hanu.gdsc.coreProblem.services.problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.coreProblem.config.TestCaseNotificationSocketConfig;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.net.Socket;

@Service
public class TestCaseNotificationServiceImpl implements TestCaseNotificationService {
    private Socket socket;
    private DataOutputStream socketOutputStream;

    @Override
    public void notifyRunningTestCase(Input input) throws Exception {
        if (socket == null) {
            synchronized (this) {
                socket = new Socket(TestCaseNotificationSocketConfig.HOST, TestCaseNotificationSocketConfig.PORT);
                socketOutputStream = new DataOutputStream(socket.getOutputStream());
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        socketOutputStream.writeUTF(objectMapper.writeValueAsString(input));
    }
}
