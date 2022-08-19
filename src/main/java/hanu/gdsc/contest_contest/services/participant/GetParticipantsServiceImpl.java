package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetParticipantsServiceImpl implements GetParticipantsService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public List<Participant> getParticipants() {
        return participantRepository.getAll();
    }
}
