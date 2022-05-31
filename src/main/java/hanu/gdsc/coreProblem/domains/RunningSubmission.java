package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class RunningSubmission extends IdentitifedVersioningDomainObject {
    private Id coderId;
    private Id problemId;
    private String serviceToCreate;
    private String code;
    private ProgrammingLanguage programmingLanguage;
    private DateTime submittedAt;

    private int judgingTestCase;
    private int totalTestCases;

    public RunningSubmission(Id id,
                             long version,
                             Id coderId,
                             Id problemId,
                             String serviceToCreate,
                             String code,
                             ProgrammingLanguage programmingLanguage,
                             DateTime submittedAt,
                             int judgingTestCase,
                             int totalTestCases) {
        super(id, version);
        this.coderId = coderId;
        this.problemId = problemId;
        this.serviceToCreate = serviceToCreate;
        this.code = code;
        this.programmingLanguage = programmingLanguage;
        this.submittedAt = submittedAt;
        this.judgingTestCase = judgingTestCase;
        this.totalTestCases = totalTestCases;
    }

    public static RunningSubmission create(Id coderId,
                                           Id problemId,
                                           String serviceToCreate,
                                           String code,
                                           ProgrammingLanguage programmingLanguage) {
        return new RunningSubmission(
                Id.generateRandom(),
                0L,
                coderId,
                problemId,
                serviceToCreate,
                code,
                programmingLanguage,
                DateTime.now(),
                0,
                0
        );
    }

    public void setJudgingTestCase(int judgingTestCase) {
        this.judgingTestCase = judgingTestCase;
    }

    public void setTotalTestCases(int totalTestCases) {
        this.totalTestCases = totalTestCases;
    }

    public Id getCoderId() {
        return coderId;
    }

    public Id getProblemId() {
        return problemId;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }

    public String getCode() {
        return code;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public DateTime getSubmittedAt() {
        return submittedAt;
    }

    public int getJudgingTestCase() {
        return judgingTestCase;
    }

    public int getTotalTestCases() {
        return totalTestCases;
    }


}
