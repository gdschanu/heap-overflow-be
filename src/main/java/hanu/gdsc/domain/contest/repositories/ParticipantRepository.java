package hanu.gdsc.domain.contest.repositories;

import hanu.gdsc.domain.contest.models.Participant;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface ParticipantRepository {
    public void save(Participant participant);
    List<Participant> getByCoderId(Id coderId);
    List<Participant> get(Id contestId, int page, int perPage);
    Participant getById(String id);
    public long countByContestId(Id contestId);
    Participant getByCoderIdAndContestId(Id coderId, Id contestId);
}
