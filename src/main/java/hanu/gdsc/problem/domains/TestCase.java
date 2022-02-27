package hanu.gdsc.problem.domains;

import hanu.gdsc.share.domains.ID;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

public class TestCase extends IdentitifedDomainObject {
    private String input;
    private String expectedOutput;
    private int ordinal;
    private boolean isSample;
    private String description;

    public TestCase(ID id, long version) {
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
