package hanu.gdsc.coreProblem.services.submissionEvent;

import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface CreateSubmissionEventService {
    @Builder
    public static class Input {
        public Id problemId;
        public Status status;
    }
    public void create(Input input);
}
