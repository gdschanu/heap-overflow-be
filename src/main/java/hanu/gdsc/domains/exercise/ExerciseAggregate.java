package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.ActiveRecord;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExerciseAggregate extends ActiveRecord {
    private Exercise exercise;
    private MemoryLimits memoryLimits;
    private TimeLimits timeLimits;
    private TestCases testCases;
    private AllowedProgrammingLanguages allowedProgrammingLanguages;

    public ExerciseAggregate(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public void checkAllowedProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        for (AllowedProgrammingLanguage allowedProgrammingLanguage : allowedProgrammingLanguages.getData()) {
            if (allowedProgrammingLanguage.equals(programmingLanguage)) {
                return;
            }
        }
        throw new Error("Programming language " + programmingLanguage + " is not allowed for this exercise.");

    }

    public void checkLimits() {

    }

    public void checkAllTestCases(List<String> actualOutputs) {
        testCases.sortTestCasesByOrdinal();
        for (int i = 0; i < actualOutputs.size(); i++) {
            String actualOutput = actualOutputs.get(i);
            TestCase tc = testCases.getData().get(i);
            if (!tc.getOutput().equals(actualOutput)) {
                throw new Error("Wrong answer at test case " + tc.getOrdinal());
            }
        }
    }


    @Override
    protected String makeInsertQuery() {
        return null;
    }

    @Override
    protected void prepareInsertStmt(PreparedStatement stmt) throws SQLException {

    }

    @Override
    protected String makeGetByIdQuery() {
        return null;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public MemoryLimits getMemoryLimits() {
        return memoryLimits;
    }

    public TimeLimits getTimeLimits() {
        return timeLimits;
    }

    public TestCases getTestCases() {
        return testCases;
    }

    public AllowedProgrammingLanguages getAllowedProgrammingLanguages() {
        return allowedProgrammingLanguages;
    }
}
