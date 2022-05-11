package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface TestCaseNotificationService {
    @Builder
    public static class Input {
        public int runningTestCase;
        public int totalTestCase;
        public Id coderId;
    }

    public void notifyRunningTestCase(Input input) throws Exception;
}
