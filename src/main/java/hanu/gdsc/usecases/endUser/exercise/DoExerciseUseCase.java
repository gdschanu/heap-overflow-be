package hanu.gdsc.usecases.endUser.exercise;

public interface DoExerciseUseCase {
    public static class Input {
        public String code;
        public String programmingLanguage;
        public int exerciseId;
        public int endUserId;
    }

    public static class Output {
    }

    public Output execute(Input input);
}
