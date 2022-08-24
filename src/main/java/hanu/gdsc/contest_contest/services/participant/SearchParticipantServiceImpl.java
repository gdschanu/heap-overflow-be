package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchParticipantServiceImpl implements SearchParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Participant getById(Id participantId) {
        return null;
    }

    @Override
    public List<Participant> search(int page, int perPage) {
        return null;
    }

    @Override
    public Participant getByCoderId(Id coderId) {
        return participantRepository.findByCoderId(coderId);
    }
}
