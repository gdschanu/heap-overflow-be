package hanu.gdsc.coreProblem.services.runningSubmission;

import hanu.gdsc.coreProblem.domains.RunningSubmission;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class UpdateRunningSubmissionQueue {

    private LinkedList<RunningSubmission> list;

    public UpdateRunningSubmissionQueue() {
        this.list = new LinkedList<>();
    }

    public void add(RunningSubmission runningSubmission) {
        synchronized (this) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(runningSubmission.getId())) {
                    list.set(i, runningSubmission);
                    return;
                }
            }
            list.add(runningSubmission);
        }
    }

    public RunningSubmission poll() {
        synchronized (this) {
            return list.pollFirst();
        }
    }
}
