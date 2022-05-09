package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

public class FailedTestCaseDetail extends IdentifiedDomainObject {
    private Integer failedAtLine;
    private String input;
    private String actualOutput;
    private String expectedOutput;
    private String description;

    private FailedTestCaseDetail(Id id, Integer failedAtLine, String input, String actualOutput, String expectedOutput, String description) {
        super(id);
        this.failedAtLine = failedAtLine;
        this.input = input;
        this.actualOutput = actualOutput;
        this.expectedOutput = expectedOutput;
        this.description = description;
    }

    public static FailedTestCaseDetail fromTestCase(Integer failedAtLine, String actualOutput, TestCase testCase) {
        return new FailedTestCaseDetail(
                Id.generateRandom(),
                failedAtLine,
                testCase.getInput(),
                actualOutput,
                testCase.getExpectedOutput(),
                testCase.getDescription()
        );
    }

    public static FailedTestCaseDetail create(Integer failedAtLine,
                                              String input,
                                              String actualOutput,
                                              String expectedOutput,
                                              String description) {
        return new FailedTestCaseDetail(
                Id.generateRandom(),
                failedAtLine,
                input,
                actualOutput,
                expectedOutput,
                description
        );
    }

    public int getFailedAtLine() {
        return failedAtLine;
    }

    public String getInput() {
        return input;
    }

    public String getActualOutput() {
        return actualOutput;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public String getDescription() {
        return description;
    }
}
