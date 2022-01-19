package hanu.gdsc.usecases.endUser.exercise;

import hanu.gdsc.domains.exercise.ExerciseAggregate;
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
        ExerciseAggregate exerciseAggregate = null; // TODO: get from repo
        // Validate programming language
        ProgrammingLanguage programmingLanguage = ProgrammingLanguage.valueOf(input.programmingLanguage);
        exerciseAggregate.checkAllowedProgrammingLanguage(programmingLanguage);
        // Run code
        RunCodeUseCase.Output runCodeOutput = runCodeUseCase.execute(RunCodeUseCase.Input.builder()
                .code(input.code)
                .inputs(exerciseAggregate.getTestCases().extractOrderedInputList())
                .build());
        exerciseAggregate.checkLimits(programmingLanguage, runCodeOutput.runTimeInMillis, runCodeOutput.memoryConsumedInKB);
        // Check real outputs with all test cases
        exerciseAggregate.checkAllTestCases(runCodeOutput.outputs);
        // TODO: save end user submission
        return null;
    }
}
