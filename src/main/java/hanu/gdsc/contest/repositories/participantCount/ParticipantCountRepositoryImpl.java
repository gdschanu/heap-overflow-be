package hanu.gdsc.contest.repositories.participantCount;

import hanu.gdsc.contest.domains.ParticipantCount;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantCountRepositoryImpl implements ParticipantCountRepositoy{

    @Autowired
    private ParticipantCountJPARepository participantCountJPARepository;

    @Override
    public void save(ParticipantCount participantCount) {
        participantCountJPARepository.save(ParticipantCountEntity.toEntity(participantCount));
    }

    @Override
    public ParticipantCount getByContestId(Id contestId) {
        ParticipantCountEntity participantCountEntity = participantCountJPARepository.getById(contestId.toString());
        return participantCountEntity == null ? null :  participantCountEntity.toDomain();
    }
}
