package hanu.gdsc.domain.core_problem.models;

import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import lombok.Getter;

@Getter
public class SubmissionEvent {
    private Id problemId;
    private Status status;
    private Id coderId;
    private DateTime submittedAt;
    private int passedTestCasesCount;
    private int totalTestCasesCount;
    private String serviceToCreate;

    private SubmissionEvent() {
    }
}
