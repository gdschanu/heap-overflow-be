package hanu.gdsc.problem.domains.submission;



import java.util.Date;

import hanu.gdsc.problem.domains.ID;
import hanu.gdsc.problem.domains.KB;
import hanu.gdsc.problem.domains.Millisecond;
import hanu.gdsc.problem.domains.ProgrammingLanguage;
import hanu.gdsc.problem.domains.TestCase;

public class Submission {
    private ID id;
    private ID problemId;
    private ProgrammingLanguage programmingLanguage;
    private Millisecond runTime;
    private KB memory;
    private Date submittedAt;
    private String code;
    private Status status;
    private TestCase failedTestCase;

    public ID getId() {
        return id;
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

    public Date getSubmittedAt() {
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
