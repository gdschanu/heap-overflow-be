package hanu.gdsc.contest.repositories.participantCount;

import hanu.gdsc.contest.domains.ParticipantCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface ParticipantCountRepository {
    public void save(ParticipantCount pCount);

    public ParticipantCount getByContestId(Id contestId);

    public List<ParticipantCount> getByContestIds(List<Id> contestIds);
}
