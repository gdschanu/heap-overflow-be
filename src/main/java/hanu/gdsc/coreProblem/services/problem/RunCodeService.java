package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.RunCodeOutput;
import lombok.Builder;

public interface RunCodeService {
    @Builder
    public static class Output {
        public Millisecond runTime;
        public KB memory;
        public RunCodeOutput output;

        public boolean compilationError;
        public String compilationMessage;

        public boolean stdError;
        public String stdMessage;
    }

    public Output execute(String code, String input, ProgrammingLanguage programmingLanguage);
}
