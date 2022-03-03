package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

public class Problem extends IdentitifedDomainObject {
    private int ordinal;
    private Id coreProblemId;
    private int score;

    public Problem(Id id, long version) {
        super(id, version);
    }
}
