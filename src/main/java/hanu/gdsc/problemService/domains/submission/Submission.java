package hanu.gdsc.problemService.domains.submission;



import java.util.Date;

import hanu.gdsc.problemService.domains.ID;
import hanu.gdsc.problemService.domains.KB;
import hanu.gdsc.problemService.domains.Millisecond;
import hanu.gdsc.problemService.domains.ProgrammingLanguage;
import hanu.gdsc.problemService.domains.TestCase;

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
