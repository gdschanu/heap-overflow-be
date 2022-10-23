package hanu.gdsc.domain.contest.repositories;

import hanu.gdsc.domain.contest.models.ParticipantCount;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface ParticipantCountRepository {
    public void save(ParticipantCount pCount);

    public ParticipantCount getByContestId(Id contestId);

    public List<ParticipantCount> getByContestIds(List<Id> contestIds);
}
