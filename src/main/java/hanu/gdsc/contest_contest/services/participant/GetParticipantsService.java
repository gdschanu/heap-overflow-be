package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface GetParticipantsService {
    public List<Participant> getParticipants(Id contestId, int page, int perPage);
}
