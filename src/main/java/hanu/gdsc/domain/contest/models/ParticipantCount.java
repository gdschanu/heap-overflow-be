package hanu.gdsc.domain.contest.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentifiedDomainObject;

public class ParticipantCount extends IdentifiedDomainObject {
    private long num;

    private ParticipantCount(Id contestId, long num) {
        super(contestId);
        this.num = num;
    }
    public static ParticipantCount create(Id contestId) {
        return new ParticipantCount(contestId, 0);
    }

    public long getNum() {
        return num;
    }
    public void increaseNum() {
        this.num = num + 1;
    }

    public Id getContestId() {
        return getId();
    }
}
