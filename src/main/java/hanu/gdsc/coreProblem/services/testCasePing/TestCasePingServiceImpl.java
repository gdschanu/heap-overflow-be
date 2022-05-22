package hanu.gdsc.coreProblem.services.testCasePing;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.InvalidInputError;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TestCasePingServiceImpl implements TestCasePingService {
    @Override
    public void ping(Id coderId, Payload payload) {
        if (coderId == null) {
            throw new InvalidInputError("coderId must not be null");
        }
        synchronized (this) {
            try {
                for (SocketThread thread : SocketThreads.get()) {
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
    }
}
