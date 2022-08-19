package hanu.gdsc.contest_contest.repositories.participant;

import hanu.gdsc.contest_contest.domains.Participant;

import java.util.List;

public interface ParticipantRepository {
    public void create  (Participant participant);
    public List<Participant> getAll();
}
