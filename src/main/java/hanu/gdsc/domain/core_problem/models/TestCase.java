package hanu.gdsc.domain.core_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestCase {
    private Id problemId;
    private String input;
    private String expectedOutput;
    private int ordinal;
    private boolean isSample;
    private String description;
    private String serviceToCreate;

    private TestCase(Id problemId, String input, String expectedOutput, int ordinal, boolean isSample,
                    String description, String serviceToCreate) {
        this.problemId = problemId;
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.ordinal = ordinal;
        this.isSample = isSample;
        this.description = description;
        this.serviceToCreate = serviceToCreate;
    }

    public static TestCase create(Id problemId,
                                  String input,
                                  String expectedOutput,
                                  int ordinal,
                                  boolean isSample,
                                  String description,
                                  String serviceToCreate) throws InvalidInputException {
        final int maxBytes = 1000000;
        final byte[] inputBytes = input.getBytes();
        if (inputBytes.length > maxBytes)
            throw new InvalidInputException("Input is too big, max bytes is " + maxBytes +
                    ", actual bytes is " + inputBytes.length);
        final byte[] outputBytes = expectedOutput.getBytes();
        if (outputBytes.length > maxBytes)
            throw new InvalidInputException("Output is too big, max bytes is " + maxBytes +
                    ", actual bytes is " + outputBytes.length);
        return new TestCase(
                problemId,
                input,
                expectedOutput,
                ordinal,
                isSample,
                description,
                serviceToCreate
        );
    }
 
    public static List<TestCase> sortByOrdinal(List<TestCase> testCases) {
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

    public String getShortenedInput() {
        return shorten(input);
    }

    public String getShortenedExpectedOutput() {
        return shorten(expectedOutput);
    }

    public String shorten(String s) {
        final int maxChars = 7000;
        if (s.length() > maxChars)
            return s.substring(0, maxChars - 1) + "\n...";
        return s;
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

    public void setInput(String input) {
        this.input = input;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public void setSample(boolean isSample) {
        this.isSample = isSample;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
