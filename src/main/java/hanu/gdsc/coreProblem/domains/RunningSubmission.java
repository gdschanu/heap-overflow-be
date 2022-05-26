package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

public class RunningSubmission extends IdentifiedDomainObject {
    private Id coderId;
    private Id problemId;
    private String serviceToCreate;
    private String code;
    private ProgrammingLanguage programmingLanguage;
    private DateTime submittedAt;

    private RunningSubmission(Id id,
                              Id coderId,
                              Id problemId,
                              String serviceToCreate,
                              String code,
                              ProgrammingLanguage programmingLanguage,
                              DateTime submittedAt) {
        super(id);
        this.coderId = coderId;
        this.problemId = problemId;
        this.serviceToCreate = serviceToCreate;
        this.code = code;
        this.programmingLanguage = programmingLanguage;
        this.submittedAt = submittedAt;
    }

    public static RunningSubmission create(Id coderId,
                                           Id problemId,
                                           String serviceToCreate,
                                           String code,
                                           ProgrammingLanguage programmingLanguage) {
        return new RunningSubmission(
                Id.generateRandom(),
                coderId,
                problemId,
                serviceToCreate,
                code,
                programmingLanguage,
                DateTime.now()
        );
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
}
