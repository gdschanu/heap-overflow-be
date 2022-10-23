package hanu.gdsc.domain.core_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentifiedDomainObject;

public class FailedTestCaseDetail extends IdentifiedDomainObject {
    private int failedAtLine;
    private String input;
    private String actualOutput;
    private String expectedOutput;
    private String description;

    private FailedTestCaseDetail(Id id,
                                 int failedAtLine,
                                 String input,
                                 String actualOutput,
                                 String expectedOutput,
                                 String description) {
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
                testCase.getShortenedInput(),
                shorten(actualOutput),
                testCase.getShortenedExpectedOutput(),
                testCase.getDescription()
        );
    }

    public static String shorten(String s) {
        final int maxChars = 7000;
        if (s.length() > maxChars)
            return s.substring(0, maxChars - 1) + "\n...";
        return s;
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
