package hanu.gdsc.usecases.endUser.exercise;

import hanu.gdsc.domains.exercise.ExerciseAggregate;
import hanu.gdsc.domains.exercise.ProgrammingLanguage;
import hanu.gdsc.domains.exercise.TestCase;
import hanu.gdsc.usecases.system.RunCodeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class DoExerciseUseCaseImpl implements DoExerciseUseCase {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RunCodeUseCase runCodeUseCase;

    @Override
    public Output execute(Input input) {
        // Get exercise aggregate
        ExerciseAggregate exerciseAggregate = (ExerciseAggregate) new ExerciseAggregate(jdbcTemplate).getById(input.exerciseId);
        // Validate programming language
        ProgrammingLanguage programmingLanguage = ProgrammingLanguage.valueOf(input.programmingLanguage);
        exerciseAggregate.checkAllowedProgrammingLanguage(programmingLanguage);
        // Sort test cases
        exerciseAggregate.getTestCases().sortTestCasesByOrdinal();
        // Get input from test cases
        List<String> inputs = new ArrayList<>();
        for (TestCase tc : exerciseAggregate.getTestCases().getData()) {
            inputs.add(tc.getInput());
        }
        // Run code
        RunCodeUseCase.Output runCodeOutput = runCodeUseCase.execute(RunCodeUseCase.Input.builder()
                .code(input.code)
                .inputs(inputs)
                .build());
        // Check real outputs with all test cases
        exerciseAggregate.checkAllTestCases(runCodeOutput.outputs);
        return null;
    }
}
