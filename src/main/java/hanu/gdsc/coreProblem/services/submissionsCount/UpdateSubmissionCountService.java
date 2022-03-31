package hanu.gdsc.coreProblem.services.submissionsCount;

import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface UpdateSubmissionCountService {
    @Builder
    public static class Input {
        public Id problemId;
        public Status status;
    }
    public void update(Input input);
}
