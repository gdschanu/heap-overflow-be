package hanu.gdsc.coreProblem.services.submit;

import java.io.IOException;

public interface CompleteJudgeTestCaseEventQueue {
    public void publish(CompleteJudgeTestCaseEvent event) throws IOException;

    public void ack(CompleteJudgeTestCaseEvent event) throws IOException;
}
