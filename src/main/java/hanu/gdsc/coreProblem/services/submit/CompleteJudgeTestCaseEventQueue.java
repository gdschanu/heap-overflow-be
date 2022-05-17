package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.services.submit.CompleteJudgeTestCaseEvent;

import java.io.IOException;

public interface CompleteJudgeTestCaseEventQueue {
    public CompleteJudgeTestCaseEvent get();
    public void ack(CompleteJudgeTestCaseEvent event) throws IOException;

    public void publish(CompleteJudgeTestCaseEvent event) throws IOException;
}
