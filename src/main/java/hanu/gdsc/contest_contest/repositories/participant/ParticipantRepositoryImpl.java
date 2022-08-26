package hanu.gdsc.contest_contest.repositories.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Participant findByCoderId(Id coderId) {
        return participantJPARepository.findByCoderId(coderId.toString()).toDomain();
    }

    @Override
    public List<Participant> get(Id contestId, int page, int perPage) {
        List<ParticipantEntity> entities = participantJPARepository.findByContestId(contestId.toString(),
                Pageable.ofSize(perPage).withPage(page));
        return entities
                .stream()
                .map(x -> x.toDomain())
                .collect(Collectors.toList());
    }

}
