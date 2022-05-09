package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestCase extends VersioningDomainObject {
    private Id problemId;
    private String input;
    private String expectedOutput;
    private int ordinal;
    private boolean isSample;
    private String description;
    private String serviceToCreate;

    private TestCase(long version, Id problemId, String input, String expectedOutput, int ordinal, boolean isSample,
                    String description, String serviceToCreate) {
        super(version);
        this.problemId = problemId;
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.ordinal = ordinal;
        this.isSample = isSample;
        this.description = description;
        this.serviceToCreate = serviceToCreate;
    }

    public static TestCase create(Id problemId, String input, String expectedOutput, int ordinal, boolean isSample,
            String description, String serviceToCreate) {
        return new TestCase(
                0,
                problemId,
                input,
                expectedOutput,
                ordinal,
                isSample,
                description,
                serviceToCreate
        );
    }
 
    public static List<TestCase> getSortedByOrdinalTestCases(List<TestCase> testCases) {
        List<TestCase> res = new ArrayList<>(testCases);
        res.sort(Comparator.comparingInt(tc -> tc.getOrdinal()));
        return res;
    }

    public Id getProblemId() {
        return problemId;
    }

    public String getInput() {
        return input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public boolean isSample() {
        return isSample;
    }

    public String getDescription() {
        return description;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }

}
