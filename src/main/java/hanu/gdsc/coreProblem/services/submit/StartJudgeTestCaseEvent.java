package hanu.gdsc.coreProblem.services.submit;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;

public class StartJudgeTestCaseEvent {
    private Long eventId;
    public final Id submissionId;
    public final int testCaseIndex;
    public final Id problemId;
    public final String code;
    public final String serviceToCreate;
    public final ProgrammingLanguage programmingLanguage;
    public final Millisecond totalRuntime;
    public final KB totalMemory;
    public final Id coderId;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        if (this.eventId == null)
            this.eventId = eventId;
        else
            throw new Error("Can only set eventId once");
    }

    public StartJudgeTestCaseEvent(Id submissionId, int testCaseIndex, Id problemId, String code, String serviceToCreate, ProgrammingLanguage programmingLanguage, Millisecond totalRuntime, KB totalMemory, Id coderId) {
        this.eventId = null;
        this.submissionId = submissionId;
        this.testCaseIndex = testCaseIndex;
        this.problemId = problemId;
        this.code = code;
        this.serviceToCreate = serviceToCreate;
        this.programmingLanguage = programmingLanguage;
        this.totalRuntime = totalRuntime;
        this.totalMemory = totalMemory;
        this.coderId = coderId;
    }
}
