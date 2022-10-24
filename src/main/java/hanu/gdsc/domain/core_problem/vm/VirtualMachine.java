package hanu.gdsc.domain.core_problem.vm;

import hanu.gdsc.domain.core_problem.models.KB;
import hanu.gdsc.domain.core_problem.models.Millisecond;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;

import java.io.IOException;

public interface VirtualMachine {
    public static interface Submission {
        public boolean compilationError();
        public String compilationMessage();
        public boolean stdError();
        public String stdMessage();
        public KB memory();
        public Millisecond runTime();
        public String output();
    }

    public Submission createSubmission(String code, String input, ProgrammingLanguage programmingLanguage) throws IOException, InterruptedException;
}
