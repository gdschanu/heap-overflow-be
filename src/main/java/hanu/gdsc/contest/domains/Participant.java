package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

import java.util.List;

public class Participant extends IdentitifedDomainObject {
    private Id coderId;
    private int rank;
    private List<ProblemScore> problemScores;

    public Participant(Id id, long version) {
        super(id, version);
    }
}
