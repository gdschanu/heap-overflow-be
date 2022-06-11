package hanu.gdsc.coreProblem.services.runningSubmission;

import hanu.gdsc.coreProblem.config.RunningSubmissionConfig;
import hanu.gdsc.coreProblem.domains.RunningSubmission;
import hanu.gdsc.coreProblem.repositories.runningSubmission.RunningSubmissionRepository;
import hanu.gdsc.share.scheduling.Scheduler;
import org.springframework.stereotype.Service;

@Service
public class UpdateRunningSubmissionService {
    private final UpdateRunningSubmissionQueue updateRunningSubmissionQueue;
    private final RunningSubmissionRepository runningSubmissionRepository;

    public UpdateRunningSubmissionService(UpdateRunningSubmissionQueue updateRunningSubmissionQueue, RunningSubmissionRepository runningSubmissionRepository) {
        this.updateRunningSubmissionQueue = updateRunningSubmissionQueue;
        this.runningSubmissionRepository = runningSubmissionRepository;
        new Scheduler(RunningSubmissionConfig.UPDATE_RUNNING_SUBMISSION_RATE_MILLIS, new Scheduler.Runner() {
            @Override
            protected void run() throws Throwable {
                update();
            }
        }).start();
    }

    public void update() {
        RunningSubmission runningSubmission = updateRunningSubmissionQueue.poll();
        if (runningSubmission == null) {
            return;
        }
        try {
            runningSubmissionRepository.updateClaimed(runningSubmission);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
