package hanu.gdsc.coreProblem.services.submit;

import java.io.IOException;

public interface StartJudgeTestCaseEventQueue {
    public void publish(StartJudgeTestCaseEvent event) throws IOException;

    public void ack(StartJudgeTestCaseEvent event) throws IOException;
}
