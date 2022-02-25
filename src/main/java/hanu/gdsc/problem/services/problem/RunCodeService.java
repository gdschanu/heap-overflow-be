package hanu.gdsc.problem.services.problem;

import hanu.gdsc.problem.domains.KB;
import hanu.gdsc.problem.domains.Millisecond;
import hanu.gdsc.problem.domains.ProgrammingLanguage;
import hanu.gdsc.problem.domains.RunCodeOutput;
import lombok.Builder;

public interface RunCodeService {
    @Builder
    public static class Output {
        public Millisecond runTime;
        public KB memory;
        public RunCodeOutput output;
    }

    public Output execute(String code, String input, ProgrammingLanguage programmingLanguage);
}
