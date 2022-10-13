package hanu.gdsc.contest_contest.repositories.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface ParticipantRepository {
    public void save(Participant participant);
    List<Participant> getByCoderId(Id coderId);
    List<Participant> get(Id contestId, int page, int perPage);
    Participant getById(String id);
    public long countByContestId(Id contestId);
}
