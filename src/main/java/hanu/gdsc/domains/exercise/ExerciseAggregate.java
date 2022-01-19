package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.ActiveRecord;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class ExerciseAggregate extends ActiveRecord {
    private Exercise exercise;
    private MemoryLimits memoryLimits;
    private TimeLimits timeLimits;
    private TestCases testCases;

    public void assertAllTestCases(List<String> actualOutputs) {
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
}
