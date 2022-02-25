package hanu.gdsc.problem.domains;


import hanu.gdsc.share.domains.ID;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

import java.time.ZonedDateTime;

public class Submission extends IdentitifedDomainObject {
    private ID problemId;
    private ProgrammingLanguage programmingLanguage;
    private Millisecond runTime;
    private KB memory;
    private ZonedDateTime submittedAt;
    private String code;
    private Status status;
    private TestCase failedTestCase;
    private Accessibility accessibility;

    public Submission(ID id, long version, ID problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory, ZonedDateTime submittedAt, String code, Status status, TestCase failedTestCase, Accessibility accessibility) {
        super(id, version);
        this.problemId = problemId;
        this.programmingLanguage = programmingLanguage;
        this.runTime = runTime;
        this.memory = memory;
        this.submittedAt = submittedAt;
        this.code = code;
        this.status = status;
        this.failedTestCase = failedTestCase;
        this.accessibility = accessibility;
    }

    public static Submission createSystemAccessSubmission(ID problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory, String code, Status status, TestCase failedTestCase) {
        return new Submission(
                ID.generate(),
                0,
                problemId,
                programmingLanguage,
                runTime,
                memory,
                ZonedDateTime.now(),
                code,
                status,
                failedTestCase,
                Accessibility.SYSTEM
        );
    }

    public static Submission createCoderAccessSubmission(ID problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory, String code, Status status, TestCase failedTestCase) {
        return new Submission(
                ID.generate(),
                0,
                problemId,
                programmingLanguage,
                runTime,
                memory,
                ZonedDateTime.now(),
                code,
                status,
                failedTestCase,
                Accessibility.CODER
        );
    }

    public ID getProblemId() {
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

    public ZonedDateTime getSubmittedAt() {
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
