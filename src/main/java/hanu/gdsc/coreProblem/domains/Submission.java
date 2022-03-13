package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

public class Submission extends IdentitifedDomainObject {
    private Id problemId;
    private ProgrammingLanguage programmingLanguage;
    private Millisecond runTime;
    private KB memory;
    private DateTime submittedAt;
    private String code;
    private Status status;
    private TestCase failedTestCase;
    public Submission(Id id, long version, Id problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory, DateTime submittedAt, String code, Status status, TestCase failedTestCase) {
        super(id, version);
        this.problemId = problemId;
        this.programmingLanguage = programmingLanguage;
        this.runTime = runTime;
        this.memory = memory;
        this.submittedAt = submittedAt;
        this.code = code;
        this.status = status;
        this.failedTestCase = failedTestCase;
    }

    public static Submission create(Id problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory,
                    DateTime submittedAt, String code, Status status, TestCase failedTestCases) {
        return new Submission(
            Id.generateRandom(),
            0,
            problemId,
            programmingLanguage,
            runTime,
            memory,
            submittedAt,
            code,
            status,
            failedTestCases
        );
    }

    public Id getProblemId() {
        return problemId;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Millisecond getRunTime() {
        return runTime;
    }

    public KB getMemory() {
        return memory;
    }

    public DateTime getSubmittedAt() {
        return submittedAt;
    }

    public String getCode() {
        return code;
    }

    public Status getStatus() {
        return status;
    }

    public TestCase getFailedTestCase() {
        return failedTestCase;
    }
}
