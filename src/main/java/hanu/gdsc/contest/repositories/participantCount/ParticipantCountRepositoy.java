package hanu.gdsc.contest.repositories.participantCount;

import hanu.gdsc.contest.domains.ParticipantCount;
import hanu.gdsc.share.domains.Id;

public interface ParticipantCountRepositoy {
    public void save(ParticipantCount pCount);
    public ParticipantCount getByContestId(Id contestId);
}
