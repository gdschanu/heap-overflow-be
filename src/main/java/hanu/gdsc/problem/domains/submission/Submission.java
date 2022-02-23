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
}
