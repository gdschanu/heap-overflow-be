package hanu.gdsc.usecases.system;

import hanu.gdsc.domains.KB;
import hanu.gdsc.domains.Millisecond;
import hanu.gdsc.domains.exercise.ProgrammingLanguage;
import lombok.Builder;

import java.util.List;

public interface RunCodeUseCase {
    @Builder
    public static class Input {
        public String code;
        public ProgrammingLanguage programmingLanguage;
        public List<String> inputs;
    }

    public static class Output {
        public List<String> outputs;
        public Millisecond runTime;
        public KB memoryConsumed;
    }

    public Output execute(Input input);
}
