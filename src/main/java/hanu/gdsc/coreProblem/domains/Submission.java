package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.IdentitifedDomainObject;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Submission extends IdentitifedDomainObject {
    private UUID problemId;
    private ProgrammingLanguage programmingLanguage;
    private Millisecond runTime;
    private KB memory;
    private ZonedDateTime submittedAt;
    private String code;
    private Status status;
    private TestCase failedTestCase;
    private Accessibility accessibility;

    public Submission(UUID id, long version, UUID problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory, ZonedDateTime submittedAt, String code, Status status, TestCase failedTestCase, Accessibility accessibility) {
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

    public static Submission createSystemAccessSubmission(UUID problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory, String code, Status status, TestCase failedTestCase) {
        return new Submission(
                UUID.randomUUID(),
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

    public static Submission createCoderAccessSubmission(UUID problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory, String code, Status status, TestCase failedTestCase) {
        return new Submission(
                UUID.randomUUID(),
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

    public UUID getProblemId() {
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

    public Accessibility getAccessibility() {
        return accessibility;
    }
}
