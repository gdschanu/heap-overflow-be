package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;
import lombok.Builder;

public class TestCase extends IdentitifedDomainObject {
    private String input;
    private String expectedOutput;
    private int ordinal;
    private boolean isSample;
    private String description;

    public TestCase(Id id, long version, String input, String expectedOutput, int ordinal, boolean isSample,
                    String description) {
        super(id, version);
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.ordinal = ordinal;
        this.isSample = isSample;
        this.description = description;
    }

    @Builder
    public static class CreateInput {
        private String input;
        private String expectedOutput;
        private int ordinal;
        private boolean isSample;
        private String description;
    }

    public static TestCase create(CreateInput input) {
        return new TestCase(
                Id.generateRandom(),
                0,
                input.input,
                input.expectedOutput,
                input.ordinal,
                input.isSample,
                input.description
        );
    }

    public TestCase(Id id, long version) {
        super(id, version);
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
}
