package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetParticipantsServiceImpl implements GetParticipantsService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public List<Participant> getParticipants(Id contestId) {
        return participantRepository.findByContestId(contestId);
    }
}
