package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public Participant getById(Id participantId) {
        return null;
    }

    public List<Participant> search(int page, int perPage) {
        return null;
    }

    public Participant getByCoderId(Id coderId) {
        return participantRepository.getByCoderId(coderId);
    }
}
