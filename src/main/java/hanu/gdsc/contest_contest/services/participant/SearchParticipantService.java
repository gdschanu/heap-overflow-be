package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchParticipantService {
    public Participant getById(Id participantId);

    public List<Participant> search(int page, int perPage);
}
