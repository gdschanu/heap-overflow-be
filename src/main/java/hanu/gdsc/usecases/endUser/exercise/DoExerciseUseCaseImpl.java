package hanu.gdsc.usecases.endUser.exercise;

import hanu.gdsc.domains.exercise.Exercise;
import hanu.gdsc.domains.exercise.ProgrammingLanguage;
import hanu.gdsc.usecases.system.RunCodeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DoExerciseUseCaseImpl implements DoExerciseUseCase {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RunCodeUseCase runCodeUseCase;

    @Override
    public Output execute(Input input) {
        // Get exercise aggregate
        Exercise exercise = null; // TODO: get from repo
        // Validate programming language
        ProgrammingLanguage programmingLanguage = ProgrammingLanguage.valueOf(input.programmingLanguage);
        exercise.checkAllowedProgrammingLanguage(programmingLanguage);
        // Run code
        RunCodeUseCase.Output runCodeOutput = runCodeUseCase.execute(RunCodeUseCase.Input.builder()
                .code(input.code)
                .programmingLanguage(programmingLanguage)
                .inputs(exercise.getSortedTestCasesInputs())
                .build());
        // Check if time or memory limit exceeded
        exercise.checkTimeAndMemoryLimit(programmingLanguage, runCodeOutput.runTime, runCodeOutput.memoryConsumed);
        // Check real outputs with all test cases
        exercise.checkAllTestCases(runCodeOutput.outputs);
        // TODO: save end user submission
        return null;
    }
}
