package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.services.submit.StartJudgeTestCaseEvent;

import java.io.IOException;

public interface StartJudgeTestCaseEventQueue {
    public StartJudgeTestCaseEvent get();
    public void ack(StartJudgeTestCaseEvent event) throws IOException;

    public void publish(StartJudgeTestCaseEvent event) throws IOException;
}
