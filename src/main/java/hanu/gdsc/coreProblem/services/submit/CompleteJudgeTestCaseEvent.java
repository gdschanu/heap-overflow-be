package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;

public class CompleteJudgeTestCaseEvent {
    private Long eventId;
    public final String judgeSubmissionId;
    public final Id problemId;
    public final String serviceToCreate;
    public final ProgrammingLanguage programmingLanguage;
    public final int testCaseIndex;
    public final Id submissionId;
    public final String code;
    public final Id coderId;
    public final KB totalMemory;
    public final Millisecond totalRuntime;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        if (this.eventId == null)
            this.eventId = eventId;
        else
            throw new Error("Can only set eventId once");
    }

    public CompleteJudgeTestCaseEvent(String judgeSubmissionId, Id problemId, String serviceToCreate, ProgrammingLanguage programmingLanguage, int testCaseIndex, Id submissionId, String code, Id coderId, KB totalMemory, Millisecond totalRuntime) {
        this.judgeSubmissionId = judgeSubmissionId;
        this.problemId = problemId;
        this.serviceToCreate = serviceToCreate;
        this.programmingLanguage = programmingLanguage;
        this.testCaseIndex = testCaseIndex;
        this.submissionId = submissionId;
        this.code = code;
        this.coderId = coderId;
        this.totalMemory = totalMemory;
        this.totalRuntime = totalRuntime;
    }
}
