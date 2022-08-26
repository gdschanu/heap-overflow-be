package hanu.gdsc.contest_contest.repositories.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ParticipantRepositoryImpl implements ParticipantRepository {
    @Autowired
    private ParticipantJPARepository participantJPARepository;

    @Override
    public void create(Participant participant) {
        participantJPARepository.save(ParticipantEntity.fromDomains(participant));
    }
    public List<Participant> findByContestId(Id contestId) {
        return participantJPARepository.findByContestId(contestId.toString()).stream().map(x -> x.toDomain()).collect(Collectors.toList());
    }

    @Override
    public Participant findByCoderId(Id coderId) {
        return participantJPARepository.findByCoderId(coderId.toString()).toDomain();
    }

}
