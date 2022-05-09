package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class Submission extends IdentitifedVersioningDomainObject {
    private Id problemId;
    private ProgrammingLanguage programmingLanguage;
    private Millisecond runTime;
    private KB memory;
    private DateTime submittedAt;
    private String code;
    private Status status;
    private FailedTestCaseDetail failedTestCaseDetail;
    private String serviceToCreate;
    private Id coderId;

    private Submission(Id id, long version, Id problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory, DateTime submittedAt, String code, Status status, FailedTestCaseDetail failedTestCaseDetail, String serviceToCreate, Id coderId) {
        super(id, version);
        this.problemId = problemId;
        this.programmingLanguage = programmingLanguage;
        this.runTime = runTime;
        this.memory = memory;
        this.submittedAt = submittedAt;
        this.code = code;
        this.status = status;
        this.failedTestCaseDetail = failedTestCaseDetail;
        this.serviceToCreate = serviceToCreate;
        this.coderId = coderId;
    }

    public static Submission create(Id problemId, ProgrammingLanguage programmingLanguage, Millisecond runTime, KB memory,
                                    String code, Status status, FailedTestCaseDetail failedTestCaseDetail,
                                    String serviceToCreate, Id coderId) {
        return new Submission(
                Id.generateRandom(),
                0,
                problemId,
                programmingLanguage,
                runTime,
                memory,
                DateTime.now(),
                code,
                status,
                failedTestCaseDetail,
                serviceToCreate,
                coderId
        );
    }

    public Id getCoderId() {
        return coderId;
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

    public FailedTestCaseDetail getFailedTestCaseDetail() {
        return failedTestCaseDetail;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }
}
