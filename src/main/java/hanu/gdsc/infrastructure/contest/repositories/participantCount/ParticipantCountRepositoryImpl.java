package hanu.gdsc.infrastructure.contest.repositories.participantCount;

import hanu.gdsc.domain.contest.models.ParticipantCount;
import hanu.gdsc.domain.contest.repositories.ParticipantCountRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantCountRepositoryImpl implements ParticipantCountRepository {

    @Autowired
    private ParticipantCountJPARepository participantCountJPARepository;

    @Override
    public void save(ParticipantCount participantCount) {
        participantCountJPARepository.save(ParticipantCountEntity.toEntity(participantCount));
    }

    @Override
    public ParticipantCount getByContestId(Id contestId) {
        ParticipantCountEntity participantCountEntity = participantCountJPARepository.getById(contestId.toString());
        return participantCountEntity == null ? null : participantCountEntity.toDomain();
    }

    @Override
    public List<ParticipantCount> getByContestIds(List<Id> contestIds) {
        List<ParticipantCountEntity> entities = participantCountJPARepository.findAllById(
                contestIds.stream().map(id -> id.toString())
                        .collect(Collectors.toList())
        );
        return entities.stream().map(e -> e.toDomain())
                .collect(Collectors.toList());
    }
}
