package hanu.gdsc.infrastructure.contest.repositories.participant;

import hanu.gdsc.domain.contest.models.Participant;
import hanu.gdsc.domain.contest.repositories.ParticipantRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ParticipantRepositoryImpl implements ParticipantRepository {
    @Autowired
    private ParticipantJPARepository participantJPARepository;

    @Override
    public void save(Participant participant) {
        participantJPARepository.save(ParticipantEntity.fromDomains(participant));
    }

    @Override
    public List<Participant> getByCoderId(Id coderId) {
        List<ParticipantEntity> entity = participantJPARepository.findByCoderId(coderId.toString());
        return entity == null ? null : entity.stream().map(x -> x.toDomain()).collect(Collectors.toList());
    }

    @Override
    public List<Participant> get(Id contestId, int page, int perPage) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by("createdAtMillis").descending());
        List<ParticipantEntity> entities = participantJPARepository.findByContestId(contestId.toString(),
                pageable);
        return entities
                .stream()
                .map(x -> x.toDomain())
                .collect(Collectors.toList());
    }

    @Override
    public Participant getById(String id) {
        Optional<ParticipantEntity> entity = participantJPARepository.findById(id.toString());
        if(entity.isEmpty()) {
            return null;
        } else {
            return entity.get().toDomain();
        }
    }

    @Override
    public long countByContestId(Id contestId) {
        return participantJPARepository.counByContestId(contestId.toString());
    }

    @Override
    public Participant getByCoderIdAndContestId(Id coderId, Id contestId) {
        Optional<ParticipantEntity> entity = participantJPARepository.findById(coderId.toString() + "#" + contestId);
        if (entity.isEmpty()) return null;
        return entity.get().toDomain();
    }
}
