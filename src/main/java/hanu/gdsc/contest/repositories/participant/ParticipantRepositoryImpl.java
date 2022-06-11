package hanu.gdsc.contest.repositories.participant;

import hanu.gdsc.contest.domains.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantRepositoryImpl implements ParticipantRepository {
    @Autowired
    private ParticipantJPARepository participantJPARepository;

    @Override
    public void create(Participant participant) {
        participantJPARepository.save(ParticipantEntity.fromDomains(participant));
    }
}
