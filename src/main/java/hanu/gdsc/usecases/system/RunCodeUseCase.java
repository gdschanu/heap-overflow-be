package hanu.gdsc.usecases.system;

import lombok.Builder;

import java.util.List;

public interface RunCodeUseCase {
    @Builder
    public static class Input {
        public String code;
        public List<String> inputs;
    }

    public static class Output {
        public List<String> outputs;
    }

    public Output execute(Input input);
}
