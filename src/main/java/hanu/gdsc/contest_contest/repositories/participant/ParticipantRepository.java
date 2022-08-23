package hanu.gdsc.contest_contest.repositories.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface ParticipantRepository {
    public void create  (Participant participant);
    public List<Participant> findByContestId(Id contestId);
}
