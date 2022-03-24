package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class TestCase extends IdentifiedDomainObject {
    private String input;
    private String expectedOutput;
    private int ordinal;
    private boolean isSample;
    private String description;

    public TestCase(Id id, String input, String expectedOutput, int ordinal, boolean isSample,
                    String description) {
        super(id);
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.ordinal = ordinal;
        this.isSample = isSample;
        this.description = description;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateInput {
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    public static TestCase create(CreateInput input) {
        return new TestCase(
                Id.generateRandom(),
                input.input,
                input.expectedOutput,
                input.ordinal,
                input.isSample,
                input.description
        );
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
