package hanu.gdsc.Problem.domains.submission;



import java.util.Date;

import hanu.gdsc.Problem.domains.ID;
import hanu.gdsc.Problem.domains.KB;
import hanu.gdsc.Problem.domains.Millisecond;
import hanu.gdsc.Problem.domains.ProgrammingLanguage;
import hanu.gdsc.Problem.domains.TestCase;

public class Submission {
    
    private ID id; 
    private ProgrammingLanguage programmingLanguage;
    private Millisecond runTime;
    private KB memory;
    private Date submittedAt;
    private String code;
    private Status status;
    private TestCase failedTestCase;
}
